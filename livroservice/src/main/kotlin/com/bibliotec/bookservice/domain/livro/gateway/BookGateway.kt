package com.bibliotec.bookservice.domain.livro.gateway

import com.bibliotec.bookservice.infrastructure.book.controller.models.Book
import com.bibliotec.bookservice.infrastructure.config.db.querys.BookFilters
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BookGateway {

    fun findById(id: Long): Book

    fun save(book: Book): Book

    fun findByIsbn13(isbn13: String): Book

    fun findByNameContaining(nome: String, pageable: Pageable?): Page<Book?>?

    fun deleteById(id: Long)

    fun existsById(id: Long): Boolean

    fun findOneBybarCodeIgnoreCase(codBarras: String): Book

    fun findFilters(filter: BookFilters, pageable: Pageable?): Page<Book?>?

}