package com.bibliotec.livroservice.infrastructure.livro.controller.dto

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.Instant
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class AutorDTO(

    var id: Long? = null,

    val createdBy: String? = null,

    val createdDate: Instant? = Instant.now(),

    val lastModifiedBy: String? = null,

    val lastModifiedDate: Instant? = Instant.now(),

    @NotBlank(message = "O nome é Obrigatório")
    @Size(min = 3, max = 60)
    val nome: String? = null,

    @NotBlank(message = "Uma descricão seria ideal")
    @Size(max = 5000)
    val descricao: String? = null,

    @JsonBackReference
    val livros: List<LivroDTO>? = null

)