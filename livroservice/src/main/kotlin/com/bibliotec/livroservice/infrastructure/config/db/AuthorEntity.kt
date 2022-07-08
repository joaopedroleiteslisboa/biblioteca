package com.bibliotec.livroservice.infrastructure.config.db

import com.fasterxml.jackson.annotation.JsonBackReference
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_autor")
class AuthorEntity(

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

        @NotBlank(message = "O nome é Obrigatório")
    @Column(name = "nome")
    @Size(min = 3, max = 60)
    val nome: String? = null,

        @NotBlank(message = "Uma descricão seria ideal")
    @Size(max = 5000)
    @Column(name = "descricao")
    val descricao: String? = null,

        @JsonBackReference
    @ManyToMany(mappedBy = "autoresEntity", fetch = FetchType.EAGER)
    val livros: List<BookEntity>? = null,

        ) : AbstractAuditingEntity() {

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 2294635258767344611L
    }


}
