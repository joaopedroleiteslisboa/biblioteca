package com.bibliotec.bookservice.infrastructure.book.gateway.db.repository

import com.bibliotec.bookservice.domain.livro.gateway.BookGateway
import com.bibliotec.bookservice.infrastructure.config.db.entity.BookEntity
import com.bibliotec.bookservice.infrastructure.book.controller.models.Book
import com.bibliotec.bookservice.infrastructure.config.customexception.NotFoundException
import com.bibliotec.bookservice.infrastructure.config.db.querys.BookFilters
import com.bibliotec.bookservice.infrastructure.config.db.querys.BookSpecification
import com.bibliotec.bookservice.infrastructure.config.exception.ErrorMessageConstants
import org.slf4j.LoggerFactory
import org.springframework.data.domain.*
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class BookPersistenceGateway(private val bookRepository: BookRepository) : BookGateway {

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

    override fun findByNameContaining(name: String, pageable: Pageable?): Page<Book?>? {
        log.info("M=findByNomeContaining, name=$name")

        var returnBooks: Page<Book?>? = null
        this.bookRepository.findByNameContaining(name, pageable)?.let { it ->
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

    override fun findFilters(filter: BookFilters, pageable: Pageable?): Page<Book?>? {
        log.info("M=findFilters")
        var returnBooks: Page<Book?>? = null
        val query: Specification<BookEntity?>? = Specification.where(
                BookSpecification.findById(filter.id)
                        ?.and(BookSpecification.findByIdPublisher(filter.idPublisher))
                        ?.and(BookSpecification.findByEdition(filter.edition))
                        ?.and(BookSpecification.findByBarCode(filter.barcode))
                        ?.and(BookSpecification.findByIsbn13(filter.isbn13))
                        ?.and(BookSpecification.findByLanguageEnum(filter.language))
                        ?.and(BookSpecification.findByNameContaining(filter.name))
                        ?.and(BookSpecification.findByDescriptionContaining(filter.description))
                        ?.and(BookSpecification.hasCategorys(filter.idsCategorys))
                        ?.and(BookSpecification.hasAthors(filter.idsAuthors))
                        ?.and(BookSpecification.findByCreationDate(filter.initialDateCreate, filter.endDateDateCreate))

        )

        val page: Pageable = PageRequest.of(pageable?.pageNumber ?: 0, pageable?.pageSize
                ?: 100, Sort.by("name").descending())

        this.bookRepository.findAll(query, page).let { it ->
            val book = it.content.map { itEntity -> Book.createFromBookEntity(itEntity) }
            returnBooks = PageImpl(book, it.pageable, book.size.toLong())
        }

        return returnBooks
    }
}