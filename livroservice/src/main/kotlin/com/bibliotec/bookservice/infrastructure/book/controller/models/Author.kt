package com.bibliotec.bookservice.infrastructure.book.controller.models

import com.bibliotec.bookservice.infrastructure.config.db.entity.AuthorEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.Instant
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class Author(

    var id: Long? = null,

    val createdBy: String? = null,

    val createdDate: Instant? = Instant.now(),

    val lastModifiedBy: String? = null,

    val lastModifiedDate: Instant? = Instant.now(),

    @NotBlank(message = "O nome é Obrigatório")
    @Size(min = 3, max = 60)
    val name: String? = null,

    @NotBlank(message = "Uma descricão seria ideal")
    @Size(max = 5000)
    val description: String? = null,

    @JsonBackReference
    val books: List<Book>? = null

){

    companion object {

        fun createFromAuthorEntity(a: AuthorEntity?): Author {

            if (a != null) {
                return Author(

                        id = a.id,
                        createdBy = a.createdBy,
                        createdDate = a.createdDate,
                        lastModifiedBy = a.lastModifiedBy,
                        lastModifiedDate = a.lastModifiedDate,
                        name = a.name,
                        description = a.description
                )
            }

            return Author()


        }
    }
}