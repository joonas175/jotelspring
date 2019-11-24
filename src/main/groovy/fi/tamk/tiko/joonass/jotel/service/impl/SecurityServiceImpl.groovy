package fi.tamk.tiko.joonass.jotel.service.impl

import fi.tamk.tiko.joonass.jotel.entity.dao.User
import fi.tamk.tiko.joonass.jotel.service.SecurityService
import fi.tamk.tiko.joonass.jotel.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class SecurityServiceImpl implements SecurityService{

    @Autowired
    UserService userService

    @Override
    User checkIdentity(HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = request.getCookies()?.find { cookie -> cookie.name == "Jotel-Identity"}

        if(cookie == null){
            return createNewIdentity(response)
        }
        String identity = cookie.value
        User existing = userService.findUserByIdentifier(identity)
        if(existing == null) {
            return createNewIdentity(response)
        } else {
            return existing
        }
    }

    @Override
    User createNewIdentity(HttpServletResponse response) {

        String identityToken = generateNewToken(32)

        User newUser = new User(identifier: identityToken)
        newUser = userService.save(newUser)

        Cookie identityCookie = new Cookie("Jotel-Identity", identityToken)
        identityCookie.setHttpOnly(true)
        identityCookie.setMaxAge(10 * 365 * 24 * 60 * 60)
        identityCookie.setDomain("localhost")

        response.addCookie(identityCookie)

        return newUser
    }

    def static generateNewToken(int count) {
        String ALPHA_NUMERIC_STRING = "abcdefghjiklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
