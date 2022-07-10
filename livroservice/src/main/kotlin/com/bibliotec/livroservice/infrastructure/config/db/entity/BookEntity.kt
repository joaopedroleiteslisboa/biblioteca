package com.bibliotec.livroservice.infrastructure.config.db.entity

import com.bibliotec.livroservice.infrastructure.book.controller.models.Book
import org.hibernate.Hibernate
import org.hibernate.validator.constraints.Range
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_book")
data class BookEntity(

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

        @Column(name = "barCode", length = 13)
        val barCode: String? = null,

        @Size(max = 256)
        @Column(name = "imageUrl", length = 256)
        val imageUrl: String? = null,

        @NotBlank(message = "Digite um nome de livro")
        @Size(max = 200, message = "Nome de livro muito grande")
        @Column(name = "name")
        val name: String? = null,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "livro_has_authors",
                joinColumns = [JoinColumn(name = "id_book")],
                inverseJoinColumns = [JoinColumn(name = "id_author")]
        )
        val authorsEntity: Set<AuthorEntity> = HashSet<AuthorEntity>(),

        @JoinColumn(name = "idPublisher")
        @ManyToOne(optional = false, fetch = FetchType.EAGER)
        val publisher: PublisherEntity? = null,

        @NotNull(message = "Este campo é obrigatorio")
        @Column(name = "idEdition")
        val edition: String? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "languageEnum")
        val languageEnum: LanguageEnum? = null,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "livro_has_categories",
                joinColumns = [JoinColumn(name = "id_book")],
                inverseJoinColumns = [JoinColumn(name = "id_category")]
        )
        val categorys: Set<CategoryEntity> = HashSet<CategoryEntity>(),

        @Size(max = 8388607, min = 3, message = "Descrição do livro muito grande")
        @Column(name = "description")
        val description: String? = null,

        @NotNull(message = "Insira o ISBN do livro")
        @Column(name = "isbn13")
        val isbn13: String? = null,

        @NotNull(message = "O livro deve ter o número de paginas")
        @Min(value = 1, message = "O livro deve ter no minimo 1 pagina")
        @Column(name = "pageNumber")
        val pageNumber: Int? = null,

        @NotNull(message = "Insira uma data de publicação")
        @Past(message = "A data deve ser inferior a atual")
        @Column(name = "publicationDate")
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        val publicationDate: LocalDate? = null,

        @DecimalMin(value = "1.00", message = "O livro deve ter um preço valido")
        @NotNull(message = "Insira um preço")
        @Column(name = "unitaryValue", precision = 10, scale = 2)
        val unitaryValue: BigDecimal? = BigDecimal.ZERO,

        @NotNull(message = "Informe uma quantidade adicionada em seu Estoque de Livros")
        @Column(name = "quantity")
        @Range(
                min = 1,
                max = 100,
                message = "Informe pelo menos {min} livro para Salvar no Estoque ou um valor maximo de {max}"
        )
        val quantity: Int? = null,

        ) : AbstractAuditingEntity() {

    companion object {
        /**
         *
         */
        private const val serialVersionUID = -7879526825677312026L

        fun createFromBook(dto: Book?): BookEntity {

            if (dto != null) {
                return BookEntity(
                        id = dto.id,
                        barCode = dto.barCode,
                        createdBy = dto.createdBy,
                        createdDate = dto.createdDate,
                        lastModifiedBy = dto.lastModifiedBy,
                        lastModifiedDate = dto.lastModifiedDate,
                        imageUrl = dto.imageUrl,
                        name = dto.name,
                        authorsEntity = dto.authorsEntity.map {
                            AuthorEntity(
                                    id = it.id,
                                    createdBy = it.createdBy,
                                    createdDate = it.createdDate,
                                    lastModifiedBy = it.lastModifiedBy,
                                    lastModifiedDate = it.lastModifiedDate,
                                    name = it.name,
                                    description = it.description
                            )
                        }.toSet(),
                        publisher = PublisherEntity(
                                id = dto.publisher!!.id,
                                createdBy = dto.publisher!!.createdBy,
                                createdDate = dto.publisher!!.createdDate,
                                lastModifiedBy = dto.publisher!!.lastModifiedBy,
                                lastModifiedDate = dto.publisher!!.lastModifiedDate,
                                name = dto.publisher!!.nome,
                                description = dto.publisher!!.descricao
                        ),
                        edition = dto.edition,
                        languageEnum = dto.languageEnum,
                        categorys = dto.categorys.map {
                            CategoryEntity(
                                    id = it.id,
                                    createdBy = it.createdBy,
                                    createdDate = it.createdDate,
                                    lastModifiedBy = it.lastModifiedBy,
                                    lastModifiedDate = it.lastModifiedDate,
                                    name = it.nome
                            )
                        }.toSet(),
                        description = dto.description,
                        isbn13 = dto.isbn13,
                        pageNumber = dto.pageNumber,
                        publicationDate = dto.publicationDate,
                        unitaryValue = dto.unitaryValue,
                        quantity = dto.quantity
                )
            }
            return BookEntity()

        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as BookEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()


}