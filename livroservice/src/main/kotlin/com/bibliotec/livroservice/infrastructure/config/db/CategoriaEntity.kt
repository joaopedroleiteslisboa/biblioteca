package com.bibliotec.livroservice.infrastructure.config.db

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "tb_categoria")
class CategoriaEntity : AbstractAuditingEntity() {

    companion object {
        /**
         *
         */
        private const val serialVersionUID = -7879582121867312026L
    }

    @NotBlank(message = "Este campo não pode ficar em branco")
    @Column(name = "nome", unique = true, length = 40)
    private val nome: String? = null

    @JsonBackReference
    @ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
    private val livros: List<LivroEntity>? = null
}
