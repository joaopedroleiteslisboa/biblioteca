package com.bibliotec.bookservice.infrastructure.config.db.entity

import com.bibliotec.bookservice.infrastructure.book.controller.models.Author
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
@Table(name = "tb_author")
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
        @Column(name = "name")
        @Size(min = 3, max = 60)
        val name: String? = null,

        @NotBlank(message = "Uma descricão seria ideal")
        @Size(max = 5000)
        @Column(name = "description")
        val description: String? = null,

        @JsonBackReference
        @ManyToMany(mappedBy = "authorsEntity", fetch = FetchType.EAGER)
        val books: List<BookEntity>? = null,

        ) : AbstractAuditingEntity() {

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 2294635258767344611L

        fun createFromAuthor(dto: Author?): AuthorEntity {

            if (dto != null) {
                return AuthorEntity(

                        id = dto.id,
                        createdBy = dto.createdBy,
                        createdDate = dto.createdDate,
                        lastModifiedBy = dto.lastModifiedBy,
                        lastModifiedDate = dto.lastModifiedDate,
                        name = dto.name,
                        description = dto.description
                )
            }

            return AuthorEntity()


        }
    }
}