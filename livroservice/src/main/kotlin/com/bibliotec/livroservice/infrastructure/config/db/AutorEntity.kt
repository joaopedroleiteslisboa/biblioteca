package com.bibliotec.livroservice.infrastructure.config.db

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.ManyToMany
import javax.persistence.FetchType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_autor")
class AutorEntity : AbstractAuditingEntity() {

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 2294635258767344611L
    }

    @NotBlank(message = "O nome é Obrigatório")
    @Column(name = "nome")
    @Size(min = 3, max = 60)
    private val nome: String? = null

    @NotBlank(message = "Uma descricão seria ideal")
    @Size(max = 5000)
    @Column(name = "descricao")
    private val descricao: String? = null

    @JsonBackReference
    @ManyToMany(mappedBy = "autoresEntity", fetch = FetchType.EAGER)
    private val livros: List<LivroEntity>? = null


}
