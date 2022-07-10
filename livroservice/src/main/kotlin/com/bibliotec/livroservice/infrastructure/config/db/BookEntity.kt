package com.bibliotec.livroservice.infrastructure.config.db

import com.bibliotec.livroservice.infrastructure.book.controller.dto.Book
import org.hibernate.Hibernate
import org.hibernate.validator.constraints.Range
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
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
@Table(name = "tb_livro")
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

        @Column(name = "codBarras", length = 13)
        val codBarras: String? = null,

        @Size(max = 256)
        @Column(name = "imagenUrl", length = 256)
        val imagenUrl: String? = null,

        @NotBlank(message = "Digite um nome de livro")
        @Size(max = 200, message = "Nome de livro muito grande")
        @Column(name = "nome")
        val nome: String? = null,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "livro_has_autores",
                joinColumns = [JoinColumn(name = "id_livro")],
                inverseJoinColumns = [JoinColumn(name = "id_autor")]
        )
        val autoresEntity: Set<AuthorEntity> = HashSet<AuthorEntity>(),


        @JoinColumn(name = "idEditora")
        @ManyToOne(optional = false, fetch = FetchType.EAGER)
        val editora: PublisherEntity? = null,

        @NotNull(message = "Este campo é obrigatorio")
        @Column(name = "edicao")
        val edicao: String? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "idioma")
        val languageEnum: LanguageEnum? = null,


        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "livro_has_categorias",
                joinColumns = [JoinColumn(name = "id_livro")],
                inverseJoinColumns = [JoinColumn(name = "id_categoria")]
        )
        val categorias: Set<CategoryEntity> = HashSet<CategoryEntity>(),

        @Size(max = 8388607, min = 3, message = "Descrição do livro muito grande")
        @Column(name = "descricao")
        val descricao: String? = null,

        @NotNull(message = "Insira o ISBN do livro")
        @Column(name = "isbn13")
        val isbn13: String? = null,

        @NotNull(message = "O livro deve ter o número de paginas")
        @Min(value = 1, message = "O livro deve ter no minimo 1 pagina")
        @Column(name = "numeroPaginas")
        val numeroPaginas: Int? = null,

        @NotNull(message = "Insira uma data de publicação")
        @Past(message = "A data deve ser inferior a atual")
        @Column(name = "dataPublicacao")
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        val dataPublicacao: LocalDate? = null,

        @DecimalMin(value = "1.00", message = "O livro deve ter um preço valido")
        @NotNull(message = "Insira um preço")
        @Column(name = "valorUnitario", precision = 10, scale = 2)
        val valorUnitario: BigDecimal? = BigDecimal.ZERO,


        @NotNull(message = "Informe uma quantidade adicionada em seu Estoque de Livros")
        @Column(name = "quantidade")
        @Range(
                min = 1,
                max = 100,
                message = "Informe pelo menos {min} livro para Salvar no Estoque ou um valor maximo de {max}"
        )
        val quantidade: Int? = null,

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
                        codBarras = dto.codBarras,
                        createdBy = dto.createdBy,
                        createdDate = dto.createdDate,
                        lastModifiedBy = dto.lastModifiedBy,
                        lastModifiedDate = dto.lastModifiedDate,
                        imagenUrl = dto.imagenUrl,
                        nome = dto.nome,
                        autoresEntity = dto.autoresEntity.map {
                            AuthorEntity(
                                    id = it.id,
                                    createdBy = it.createdBy,
                                    createdDate = it.createdDate,
                                    lastModifiedBy = it.lastModifiedBy,
                                    lastModifiedDate = it.lastModifiedDate,
                                    nome = it.nome,
                                    descricao = it.descricao
                            )
                        }.toSet(),
                        editora = PublisherEntity(
                                id = dto.editora!!.id,
                                createdBy = dto.editora!!.createdBy,
                                createdDate = dto.editora!!.createdDate,
                                lastModifiedBy = dto.editora!!.lastModifiedBy,
                                lastModifiedDate = dto.editora!!.lastModifiedDate,
                                nome = dto.editora!!.nome,
                                descricao = dto.editora!!.descricao
                        ),
                        edicao = dto.edicao,
                        languageEnum = dto.languageEnum,
                        categorias = dto.categorias.map {
                            CategoryEntity(
                                    id = it.id,
                                    createdBy = it.createdBy,
                                    createdDate = it.createdDate,
                                    lastModifiedBy = it.lastModifiedBy,
                                    lastModifiedDate = it.lastModifiedDate,
                                    nome = it.nome
                            )
                        }.toSet(),
                        descricao = dto.descricao,
                        isbn13 = dto.isbn13,
                        numeroPaginas = dto.numeroPaginas,
                        dataPublicacao = dto.dataPublicacao,
                        valorUnitario = dto.valorUnitario,
                        quantidade = dto.quantidade
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