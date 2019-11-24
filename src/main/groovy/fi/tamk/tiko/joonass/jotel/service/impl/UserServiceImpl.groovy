package fi.tamk.tiko.joonass.jotel.service.impl

import fi.tamk.tiko.joonass.jotel.entity.dao.User
import fi.tamk.tiko.joonass.jotel.repository.UserRepository
import fi.tamk.tiko.joonass.jotel.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository

    @Override
    User findUserById(int id) {
        Optional<User> user = userRepository.findById(id)

        if(user.isPresent()){
            return user.get()
        }

        null
    }

    @Override
    User findUserByIdentifier(String identifier) {
        Optional<User> user = userRepository.findByIdentifier(identifier)

        if(user.isPresent()){
            return user.get()
        }

        null
    }

    @Override
    User save(User user) {
        return userRepository.save(user)
    }
}
