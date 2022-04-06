package com.bibliotec.livroservice.infrastructure.config.db

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Column
import javax.persistence.OneToMany
import javax.persistence.FetchType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_editora")
class EditoraEntity : AbstractAuditingEntity() {

    companion object {
        /**
         *
         */
        private const val serialVersionUID = -7879526821867312026L
    }

    @NotBlank(message = "O nome é Obrigatório")
    @Size(min = 3, max = 60)
    @Column(name = "nome")
    private val nome: String? = null

    @NotBlank(message = "Uma descricao seria ideal")
    @Size(max = 5000)
    @Column(name = "descricao")
    private val descricao: String? = null

    @JsonBackReference
    @OneToMany(mappedBy = "editora", orphanRemoval = false, fetch = FetchType.EAGER)
    private val livros: List<LivroEntity>? = null
}
