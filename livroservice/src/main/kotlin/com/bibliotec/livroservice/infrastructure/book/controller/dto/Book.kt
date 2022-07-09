package com.bibliotec.livroservice.infrastructure.book.controller.dto

import com.bibliotec.livroservice.infrastructure.config.db.*
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

        val createdBy: String? = null,

        val createdDate: Instant? = Instant.now(),

        val lastModifiedBy: String? = null,

        val lastModifiedDate: Instant? = Instant.now(),

        var codBarras: String? = UUID.randomUUID().toString(),

        var imagenUrl: String? = null,

        @NotBlank(message = "Digite um nome de livro")
        @Size(max = 200, message = "Nome de livro muito grande")
        var nome: String? = null,

        var autoresEntity: Set<Author> = HashSet<Author>(),

        var editora: Publisher? = null,

        @NotNull(message = "Este campo é obrigatorio")
        var edicao: String? = null,

        @NotNull(message = "O campo indioma é obrigatorio")
        var languageEnum: LanguageEnum? = null,

        var categorias: Set<Category> = HashSet<Category>(),

        @Size(max = 8388607, min = 3, message = "Descrição do livro muito grande")
        var descricao: String? = null,

        @NotNull(message = "Insira o ISBN do livro")
        var isbn13: String? = null,

        @NotNull(message = "O livro deve ter o número de paginas")
        @Min(value = 1, message = "O livro deve ter no minimo 1 pagina")
        var numeroPaginas: Int? = null,

        @NotNull(message = "Insira uma data de publicação")
        @Past(message = "A data deve ser inferior a atual")
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        var dataPublicacao: LocalDate? = null,

        @DecimalMin(value = "1.00", message = "O livro deve ter um preço valido")
        @NotNull(message = "Insira um preço")
        var valorUnitario: BigDecimal? = BigDecimal.ZERO,

        @NotNull(message = "Informe uma quantidade adicionada em seu Estoque de Livros")
        @Range(
                min = 1,
                max = 100,
                message = "Informe pelo menos {min} livro para Salvar no Estoque ou um valor maximo de {max}"
        )
        var quantidade: Int? = null
) {

    companion object {

        fun createFromBookEntity(entity: BookEntity) = Book(
                id = entity.id,
                codBarras = entity.codBarras,
                createdBy = entity.createdBy,
                createdDate = entity.createdDate,
                lastModifiedBy = entity.lastModifiedBy,
                lastModifiedDate = entity.lastModifiedDate,
                imagenUrl = entity.imagenUrl,
                nome = entity.nome,
                autoresEntity = entity.autoresEntity.map {
                    Author(
                            id = it.id,
                            createdBy = it.createdBy,
                            createdDate = it.createdDate,
                            lastModifiedBy = it.lastModifiedBy,
                            lastModifiedDate = it.lastModifiedDate,
                            nome = it.nome,
                            descricao = it.descricao
                    )
                }.toSet(),
                editora = Publisher(
                        id = entity.editora!!.id,
                        createdBy = entity.editora!!.createdBy,
                        createdDate = entity.editora!!.createdDate,
                        lastModifiedBy = entity.editora!!.lastModifiedBy,
                        lastModifiedDate = entity.editora!!.lastModifiedDate,
                        nome = entity.editora!!.nome,
                        descricao = entity.editora!!.descricao
                ),
                edicao = entity.edicao,
                languageEnum = entity.languageEnum,
                categorias = entity.categorias.map {
                    Category(
                            id = it.id,
                            createdBy = it.createdBy,
                            createdDate = it.createdDate,
                            lastModifiedBy = it.lastModifiedBy,
                            lastModifiedDate = it.lastModifiedDate,
                            nome = it.nome
                    )
                }.toSet(),
                descricao = entity.descricao,
                isbn13 = entity.isbn13,
                numeroPaginas = entity.numeroPaginas,
                dataPublicacao = entity.dataPublicacao,
                valorUnitario = entity.valorUnitario,
                quantidade = entity.quantidade
        )
    }


}