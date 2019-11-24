package fi.tamk.tiko.joonass.jotel.entity

import fi.tamk.tiko.joonass.jotel.entity.dao.PostDAO

class PostDTO extends PostDAO{

    Integer votes

    Boolean owned

    Boolean voted

    PostDAO toDAO() {
        return new PostDAO(
                id: this.id,
                message: this.message,
                color: this.color
        )
    }
}
