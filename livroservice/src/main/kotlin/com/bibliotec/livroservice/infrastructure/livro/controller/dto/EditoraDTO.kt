package com.bibliotec.livroservice.infrastructure.livro.controller.dto

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.Instant

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class EditoraDTO(

    var id: Long? = null,

    var createdBy: String? = null,

    var createdDate: Instant? = Instant.now(),

    var lastModifiedBy: String? = null,

    var lastModifiedDate: Instant? = Instant.now(),

    @NotBlank(message = "O nome é Obrigatório")
    @Size(min = 3, max = 60)
    var nome: String? = null,

    @NotBlank(message = "Uma descricao seria ideal")
    @Size(max = 5000)
    var descricao: String? = null,

    @JsonBackReference
    var livros: List<LivroDTO>? = null

)