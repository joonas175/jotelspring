package fi.tamk.tiko.joonass.jotel.service

import fi.tamk.tiko.joonass.jotel.entity.dao.User

interface UserService {

    User findUserById(int id)

    User findUserByIdentifier(String identifier)

    User save(User user)
}