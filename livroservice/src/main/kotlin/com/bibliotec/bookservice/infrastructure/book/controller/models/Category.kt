package com.bibliotec.bookservice.infrastructure.book.controller.models

import com.bibliotec.bookservice.infrastructure.config.db.entity.CategoryEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.Instant
import javax.validation.constraints.NotBlank

data class Category(

        var id: Long? = null,

        val createdBy: String? = null,

        val createdDate: Instant? = Instant.now(),

        val lastModifiedBy: String? = null,

        val lastModifiedDate: Instant? = Instant.now(),

        @NotBlank(message = "Este campo não pode ficar em branco")
        val name: String? = null,

        @JsonBackReference
        val livros: List<Book>? = null

) {

    companion object {

        fun createFromCategoryEntity(a: CategoryEntity?): Category {

            if (a != null) {
                return Category(

                        id = a.id,
                        createdBy = a.createdBy,
                        createdDate = a.createdDate,
                        lastModifiedBy = a.lastModifiedBy,
                        lastModifiedDate = a.lastModifiedDate,
                        name = a.name
                )
            }

            return Category()


        }
    }
}