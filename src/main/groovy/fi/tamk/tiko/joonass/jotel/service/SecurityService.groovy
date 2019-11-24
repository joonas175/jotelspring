package fi.tamk.tiko.joonass.jotel.service

import fi.tamk.tiko.joonass.jotel.entity.dao.User

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface SecurityService {
    User checkIdentity(HttpServletRequest request, HttpServletResponse response)

    def createNewIdentity(HttpServletResponse response)
}