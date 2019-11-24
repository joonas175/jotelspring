package fi.tamk.tiko.joonass.jotel.repository

import fi.tamk.tiko.joonass.jotel.entity.dao.VoteDAO
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository extends JpaRepository<VoteDAO, Integer> {

    Optional<VoteDAO> findByPostIdAndUserId(int postId, int userId)

    List<VoteDAO> findAllByPostId(int postId)

}