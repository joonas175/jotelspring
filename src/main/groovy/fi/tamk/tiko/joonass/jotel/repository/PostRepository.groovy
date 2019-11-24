package fi.tamk.tiko.joonass.jotel.repository

import fi.tamk.tiko.joonass.jotel.entity.dao.PostDAO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository extends JpaRepository<PostDAO, Integer>{

    List<PostDAO> findAllByOrderByTimestampDesc()

    List<PostDAO> findAllById(Integer id)

    PostDAO save(PostDAO post)

}
