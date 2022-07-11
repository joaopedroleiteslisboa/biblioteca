package com.bibliotec.bookservice.infrastructure.book.gateway.db.repository

import com.bibliotec.bookservice.domain.livro.gateway.LivroGateway
import com.bibliotec.bookservice.infrastructure.config.db.entity.BookEntity
import com.bibliotec.bookservice.infrastructure.book.controller.models.Book
import com.bibliotec.bookservice.infrastructure.config.customexception.NotFoundException
import com.bibliotec.bookservice.infrastructure.config.exception.ErrorMessageConstants
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class BookPersistenceGateway(private val bookRepository: BookRepository) : LivroGateway {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun findById(id: Long): Book {

        return Book.createFromBookEntity(this.bookRepository.findById(id).orElseThrow { NotFoundException(ErrorMessageConstants.BOOK_NOTFOUND_EXCEPTION) })
    }

    @Transactional
    override fun save(book: Book): Book {
        log.info("M=save, name=${book.name}")

        return Book.createFromBookEntity(this.bookRepository.save(BookEntity.createFromBook(book)))

    }

    override fun findByIsbn13(isbn13: String): Book {
        log.info("M=findByIsbn13, isbn13=$isbn13")

        val book = this.bookRepository.findOneByIsbn13IgnoreCase(isbn13) ?: run {
            log.warn("M=findByIsbn13, isbn13=$isbn13, error=${ErrorMessageConstants.BOOK_NOTFOUND_EXCEPTION}")
            throw NotFoundException(ErrorMessageConstants.BOOK_NOTFOUND_EXCEPTION)
        }

        return Book.createFromBookEntity(book)

    }

    override fun findByNameContaining(name: String?, pageable: Pageable?): Page<Book?>? {
        log.info("M=findByNomeContaining, name=$name")

        var returnBooks: Page<Book?>? = null
        val booksPageEntity = this.bookRepository.findByNameContaining(name, pageable)?.let { it ->
            val book = it.content.map { itEntity -> Book.createFromBookEntity(itEntity) }
            returnBooks = PageImpl(book, it.pageable, book.size.toLong())
        }

        return returnBooks
    }

    override fun deleteById(id: Long) {
        log.info("M=deleteById, id=${id}")

        findById(id)
        this.bookRepository.deleteById(id)
    }

    override fun existsById(id: Long): Boolean {
        log.info("M=existsById, id=${id}")

        return this.bookRepository.existsById(id)
    }

    override fun findOneBybarCodeIgnoreCase(codBarras: String): Book {
        log.info("M=findOneByCodBarrasIgnoreCase, codBarras=${codBarras}")

        return Book.createFromBookEntity(this.bookRepository.findOneBybarCodeIgnoreCase(codBarras))

    }

}