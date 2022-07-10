package com.bibliotec.livroservice.infrastructure.book.controller.models

import com.bibliotec.livroservice.infrastructure.config.db.entity.PublisherEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.Instant

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class Publisher(

    var id: Long? = null,

    var createdBy: String? = null,

    var createdDate: Instant? = Instant.now(),

    var lastModifiedBy: String? = null,

    var lastModifiedDate: Instant? = Instant.now(),

    @NotBlank(message = "O nome é Obrigatório")
    @Size(min = 3, max = 60)
    var name: String? = null,

    @NotBlank(message = "Uma descricao seria ideal")
    @Size(max = 5000)
    var description: String? = null,

    @JsonBackReference
    var books: List<Book>? = null

){

    companion object {
        /**
         *
         */
        private const val serialVersionUID = -7879526821867312026L

        fun createFromPublisherEntity(p: PublisherEntity?): Publisher {

            if (p != null) {
                return Publisher(

                        id = p.id,
                        createdBy = p.createdBy,
                        createdDate = p.createdDate,
                        lastModifiedBy = p.lastModifiedBy,
                        lastModifiedDate = p.lastModifiedDate,
                        name = p.name,
                        description = p.description
                )
            }

            return Publisher()

        }
    }
}