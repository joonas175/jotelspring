package fi.tamk.tiko.joonass.jotel.entity.dao

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "vote")
class VoteDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id

    @Column(name = "post_id")
    Integer postId

    @Column(name = "user_id")
    Integer userId

    @Column(name = "vote_value")
    Integer voteValue
}
