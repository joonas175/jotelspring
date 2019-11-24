package fi.tamk.tiko.joonass.jotel.service.impl

import fi.tamk.tiko.joonass.jotel.entity.dao.VoteDAO
import fi.tamk.tiko.joonass.jotel.repository.LikeRepository
import fi.tamk.tiko.joonass.jotel.service.LikeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl implements LikeService{

    @Autowired
    LikeRepository likeRepository

    @Override
    boolean userHasVoted(int postId, int userId) {
        Optional<VoteDAO> likeDAO = likeRepository.findByPostIdAndUserId(postId, userId)

        return likeDAO.isPresent()
    }

    @Override
    int getVoteCount(int postId) {
        List<VoteDAO> likes = likeRepository.findAllByPostId(postId)
        Integer count = likes.sum({it.voteValue}) as Integer

        return count == null ? 0 : count
    }

    @Override
    int vote(int postId, int userId, boolean dislike) {
        if(!userHasVoted(postId, userId)){

            VoteDAO newVote = new VoteDAO(postId: postId, userId: userId, voteValue: (dislike ? -1 : 1))
            likeRepository.save(newVote)
        }

        return getVoteCount(postId)
    }
}
