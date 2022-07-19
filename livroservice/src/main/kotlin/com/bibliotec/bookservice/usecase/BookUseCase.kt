package com.bibliotec.bookservice.usecase

import com.bibliotec.bookservice.domain.livro.gateway.BookGateway
import com.bibliotec.bookservice.infrastructure.book.controller.models.Book
import com.bibliotec.bookservice.infrastructure.config.db.querys.BookFilters
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class BookUseCase(private val livroGateway: BookGateway) {

    fun save(book: Book): Book = this.livroGateway.save(book)

    fun findById(id: Long): Book = this.livroGateway.findById(id)

    fun findByIsbn13(isbn13: String): Book = this.livroGateway.findByIsbn13(isbn13)

    fun findByNameContaining(nome: String, pageable: Pageable?): Page<Book?>? = this.livroGateway.findByNameContaining(nome, pageable)

    fun deleteById(id: Long) = this.livroGateway.deleteById(id)

    fun existsById(id: Long): Boolean = this.livroGateway.existsById(id)

    fun findOneBybarCodeIgnoreCase(codBarras: String): Book = this.livroGateway.findOneBybarCodeIgnoreCase(codBarras)

    fun findFilters(filters: BookFilters, pageable: Pageable?) = this.livroGateway.findFilters(filters, pageable)

}