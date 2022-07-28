package com.bibliotec.bookservice.infrastructure.book.controller.models

import com.bibliotec.bookservice.infrastructure.config.db.entity.BookEntity
import com.bibliotec.bookservice.infrastructure.config.db.entity.LanguageEnum
import org.hibernate.validator.constraints.Range
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.UUID
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotNull
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import javax.validation.constraints.Min
import javax.validation.constraints.Past
import kotlin.collections.HashSet

class Book(

        var id: Long? = null,

        var createdBy: String? = null,

        var createdDate: LocalDate? = LocalDate.now(),

        var lastModifiedBy: String? = null,

        var lastModifiedDate: LocalDate? = LocalDate.now(),

        var barCode: String? = null,

        var imageUrl: String? = null,

        @NotBlank(message = "Digite um nome de livro")
        @Size(max = 200, message = "Nome de livro muito grande")
        var name: String? = null,

        var authorsEntity: Set<Author> = HashSet<Author>(),

        var publisher: Publisher? = null,

        @NotNull(message = "Este campo é obrigatorio")
        var edition: String? = null,

        @NotNull(message = "O campo indioma é obrigatorio")
        var languageEnum: LanguageEnum? = null,

        var categorys: Set<Category> = HashSet<Category>(),

        @Size(max = 8388607, min = 3, message = "Descrição do livro muito grande")
        var description: String? = null,

        @NotNull(message = "Insira o ISBN do livro")
        var isbn13: String? = null,

        @NotNull(message = "O livro deve ter o número de paginas")
        @Min(value = 1, message = "O livro deve ter no minimo 1 pagina")
        var pageNumber: Int? = null,

        @NotNull(message = "Insira uma data de publicação")
        @Past(message = "A data deve ser inferior a atual")
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        var publicationDate: LocalDate? = null,

        @DecimalMin(value = "1.00", message = "O livro deve ter um preço valido")
        @NotNull(message = "Insira um preço")
        var unitaryValue: BigDecimal? = BigDecimal.ZERO,

        @NotNull(message = "Informe uma quantidade adicionada em seu Estoque de Livros")
        @Range(
                min = 1,
                max = 100,
                message = "Informe pelo menos {min} livro para Salvar no Estoque ou um valor maximo de {max}"
        )
        var quantity: Int? = null
) {

    companion object {

        fun createFromBookEntity(entity: BookEntity?): Book {

            if (entity != null) {
                return Book(
                        id = entity.id,
                        barCode = entity.barCode,
                        createdBy = entity.createdBy,
                        createdDate = entity.createdDate,
                        lastModifiedBy = entity.lastModifiedBy,
                        lastModifiedDate = entity.lastModifiedDate,
                        imageUrl = entity.imageUrl,
                        name = entity.name,
                        authorsEntity = entity.authorsEntity.map {
                            Author(
                                    id = it.id,
                                    createdBy = it.createdBy,
                                    createdDate = it.createdDate,
                                    lastModifiedBy = it.lastModifiedBy,
                                    lastModifiedDate = it.lastModifiedDate,
                                    name = it.name,
                                    description = it.description
                            )
                        }.toSet(),
                        publisher = Publisher(
                                id = entity.publisher!!.id,
                                createdBy = entity.publisher!!.createdBy,
                                createdDate = entity.publisher!!.createdDate,
                                lastModifiedBy = entity.publisher!!.lastModifiedBy,
                                lastModifiedDate = entity.publisher!!.lastModifiedDate,
                                name = entity.publisher!!.name,
                                description = entity.publisher!!.description
                        ),
                        edition = entity.edition,
                        languageEnum = entity.languageEnum,
                        categorys = entity.categorys.map {
                            Category(
                                    id = it.id,
                                    createdBy = it.createdBy,
                                    createdDate = it.createdDate,
                                    lastModifiedBy = it.lastModifiedBy,
                                    lastModifiedDate = it.lastModifiedDate,
                                    name = it.name
                            )
                        }.toSet(),
                        description = entity.description,
                        isbn13 = entity.isbn13,
                        pageNumber = entity.pageNumber,
                        publicationDate = entity.publicationDate,
                        unitaryValue = entity.unitaryValue,
                        quantity = entity.quantity
                )
            }
            return Book()
        }
    }


}