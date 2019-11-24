package fi.tamk.tiko.joonass.jotel.service.impl

import fi.tamk.tiko.joonass.jotel.entity.PostDTO
import fi.tamk.tiko.joonass.jotel.entity.dao.PostDAO
import fi.tamk.tiko.joonass.jotel.entity.dao.User
import fi.tamk.tiko.joonass.jotel.repository.PostRepository
import fi.tamk.tiko.joonass.jotel.service.LikeService
import fi.tamk.tiko.joonass.jotel.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.server.ResponseStatusException

@Service
class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository

    @Autowired
    LikeService likeService

    @Override
    List<PostDTO> getAllPosts(User user) {
        List<PostDTO> dtoList = new ArrayList<>()

        postRepository.findAllByOrderByTimestampDesc() each { dao ->

            PostDTO dto = dao.toDTO()

            if(dao.userId == user.id) dto.owned = true

            dto.voted = likeService.userHasVoted(dao.id, user.id)
            dto.votes = likeService.getVoteCount(dto.id)

            dtoList << dto
        }

        return dtoList

    }

    @Override
    PostDTO saveNewPost(PostDTO postDTO, User user) {
        PostDAO daoToSave = postDTO.toDAO()
        daoToSave.userId = user.id


        PostDAO posted = postRepository.save(daoToSave)
        PostDTO newDTO = posted.toDTO()
        newDTO.owned = true
        newDTO.votes = 0
        newDTO.voted = false

        return newDTO
    }

    @Override
    int deletePost(int i, User user) {
        Optional<PostDAO> postDAO = postRepository.findById(i)

        PostDAO postToDelete = postDAO.orElseThrow() {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found")
        }

        if(postToDelete.userId != user.id)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Post belongs to other user")

        postRepository.delete(postToDelete)

        return postToDelete.id


    }
}
