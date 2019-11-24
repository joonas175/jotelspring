package fi.tamk.tiko.joonass.jotel.entity.dao

import com.fasterxml.jackson.annotation.JsonIgnore
import fi.tamk.tiko.joonass.jotel.entity.PostDTO

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "post")
class PostDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id

    @NotNull
    String message

    String color

    Date timestamp

    @Column(name = "user_id")
    @JsonIgnore
    int userId

    @PrePersist
    void prePersist(){
        this.timestamp = new Date()
    }

    PostDTO toDTO() {
        new PostDTO(
                id: this.id,
                message: this.message,
                color: this.color,
        )
    }
}
