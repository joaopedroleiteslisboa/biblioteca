package com.bibliotec.livroservice.infrastructure.book.controller.models

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
    val nome: String? = null,

    @JsonBackReference
    val livros: List<Book>? = null

)