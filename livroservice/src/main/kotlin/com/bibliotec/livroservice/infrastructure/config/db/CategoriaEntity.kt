package com.bibliotec.livroservice.infrastructure.config.db

import com.fasterxml.jackson.annotation.JsonBackReference
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "tb_categoria")
class CategoriaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 70, updatable = false)
    val createdBy: String? = null,

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    val createdDate: Instant? = Instant.now(),

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 70)
    val lastModifiedBy: String? = null,

    @LastModifiedDate
    @Column(name = "last_modified_date")
    val lastModifiedDate: Instant? = Instant.now(),

    @NotBlank(message = "Este campo não pode ficar em branco")
    @Column(name = "nome", unique = true, length = 40)
    private val nome: String? = null,

    @JsonBackReference
    @ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
    private val livros: List<LivroEntity>? = null

): AbstractAuditingEntity() {

    companion object {
        /**
         *
         */
        private const val serialVersionUID = -7879582121867312026L
    }

}
