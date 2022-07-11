package com.bibliotec.bookservice.infrastructure.config.db.entity

import com.bibliotec.bookservice.infrastructure.book.controller.models.Category
import com.fasterxml.jackson.annotation.JsonBackReference
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "tb_category")
class CategoryEntity(

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
    @Column(name = "name", unique = true, length = 40)
    val name: String? = null,

    @JsonBackReference
    @ManyToMany(mappedBy = "categorys", fetch = FetchType.EAGER)
    val books: List<BookEntity>? = null

): AbstractAuditingEntity() {

    companion object {
        /**
         *
         */
        private const val serialVersionUID = -7879582121867312026L

        fun createFromCategoryEntity(a: Category?): CategoryEntity {

            if (a != null) {
                return CategoryEntity(

                        id = a.id,
                        createdBy = a.createdBy,
                        createdDate = a.createdDate,
                        lastModifiedBy = a.lastModifiedBy,
                        lastModifiedDate = a.lastModifiedDate,
                        name = a.name
                )
            }

            return CategoryEntity()


        }
    }

}
