package com.bibliotec.bookservice.infrastructure.config.db.entity

import com.bibliotec.bookservice.infrastructure.book.controller.models.Publisher
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
@Table(name = "tb_publisher")
class PublisherEntity(

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
    @Size(min = 3, max = 60)
    @Column(name = "name")
    val name: String? = null,

    @NotBlank(message = "Uma descricao seria ideal")
    @Size(max = 5000)
    @Column(name = "description")
    val description: String? = null,

    @JsonBackReference
    @OneToMany(mappedBy = "publisher", orphanRemoval = false, fetch = FetchType.EAGER)
    val books: List<BookEntity>? = null


): AbstractAuditingEntity() {
    companion object {
        /**
         *
         */
        private const val serialVersionUID = -7879526821867312026L

        fun createFromPublisher(dto: Publisher?): PublisherEntity {

            if (dto != null) {
                return PublisherEntity(

                        id = dto.id,
                        createdBy = dto.createdBy,
                        createdDate = dto.createdDate,
                        lastModifiedBy = dto.lastModifiedBy,
                        lastModifiedDate = dto.lastModifiedDate,
                        name = dto.name,
                        description = dto.description
                )
            }

            return PublisherEntity()


        }
    }
}