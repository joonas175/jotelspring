package fi.tamk.tiko.joonass.jotel.repository

import fi.tamk.tiko.joonass.jotel.entity.dao.PostDAO
import fi.tamk.tiko.joonass.jotel.entity.dao.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer id)

    Optional<User> findByIdentifier(String identifier)

    User save(User user)

}