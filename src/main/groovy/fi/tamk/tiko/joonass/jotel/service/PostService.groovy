package fi.tamk.tiko.joonass.jotel.service

import fi.tamk.tiko.joonass.jotel.entity.PostDTO
import fi.tamk.tiko.joonass.jotel.entity.dao.PostDAO
import fi.tamk.tiko.joonass.jotel.entity.dao.User


interface PostService {

    List<PostDTO> getAllPosts(User user)

    PostDTO saveNewPost(PostDTO post, User user)

    int deletePost(int i, User user)
}
