package com.bibliotec.livroservice.infrastructure.book.gateway.db.repository

import com.bibliotec.livroservice.domain.livro.gateway.LivroGateway
import com.bibliotec.livroservice.infrastructure.config.db.AuthorEntity
import com.bibliotec.livroservice.infrastructure.config.db.CategoryEntity
import com.bibliotec.livroservice.infrastructure.config.db.PublisherEntity
import com.bibliotec.livroservice.infrastructure.config.db.BookEntity
import com.bibliotec.livroservice.infrastructure.book.controller.dto.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class BookPersistenceGateway(private val bookRepository: BookRepository) : LivroGateway {


    override fun save(book: Book) :Book{

        Book.createFromBookEntity(this.bookRepository.save(BookEntity.createFromBook(book)))

    }

    override fun findByIsbn13(isbn13: String): BookEntity = this.bookRepository.findByIsbn13(isbn13)

    override fun findByNomeContaining(nome: String?, pageable: Pageable?): Page<BookEntity?>? =
            this.bookRepository.findByNomeContaining(nome, pageable)

    override fun deleteById(id: Long?) = this.bookRepository.deleteById(id)

    override fun existsById(id: Long?): Boolean? = this.bookRepository.existsById(id)

    override fun findOneByCodBarrasIgnoreCase(codBarras: String?): Optional<BookEntity?>? =
            this.findOneByCodBarrasIgnoreCase(codBarras)

    override fun findOneByIsbn13IgnoreCase(isbn13: String?): Optional<BookEntity?>? =
            this.findOneByIsbn13IgnoreCase(isbn13)

}