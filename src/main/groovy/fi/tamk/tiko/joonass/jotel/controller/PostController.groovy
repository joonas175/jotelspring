package fi.tamk.tiko.joonass.jotel.controller

import fi.tamk.tiko.joonass.jotel.entity.PostDTO
import fi.tamk.tiko.joonass.jotel.entity.dao.User
import fi.tamk.tiko.joonass.jotel.service.LikeService
import fi.tamk.tiko.joonass.jotel.service.PostService
import fi.tamk.tiko.joonass.jotel.service.SecurityService
import fi.tamk.tiko.joonass.jotel.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value = "/posts")
class PostController {

    @Autowired
    PostService postService

    @Autowired
    SecurityService securityService

    @Autowired
    UserService userService

    @Autowired
    LikeService likeService

    @Autowired
    HttpServletRequest httpServletRequest


    @RequestMapping(method = RequestMethod.GET)
    List<PostDTO> getAllPosts(HttpServletResponse response){

        User user = securityService.checkIdentity(httpServletRequest, response)

        return postService.getAllPosts(user)
    }

    @RequestMapping(method = RequestMethod.POST)
    PostDTO postPost(@RequestBody(required = true) PostDTO post, HttpServletResponse response){

        User user = securityService.checkIdentity(httpServletRequest, response)

        postService.saveNewPost(post, user)
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
    Integer deletePost (@PathVariable Integer id, HttpServletResponse response){

        User user = securityService.checkIdentity(httpServletRequest, response)

        return postService.deletePost(id, user)
    }

    @RequestMapping(value = "/{id}/like", method = RequestMethod.POST)
    Integer likePost (@PathVariable Integer id, HttpServletResponse response){

        User user = securityService.checkIdentity(httpServletRequest, response)

        return likeService.vote(id, user.id, false)

    }

    @RequestMapping(value = "/{id}/dislike", method = RequestMethod.POST)
    Integer dislikePost (@PathVariable Integer id, HttpServletResponse response){

        User user = securityService.checkIdentity(httpServletRequest, response)

        return likeService.vote(id, user.id, true)

    }

}
