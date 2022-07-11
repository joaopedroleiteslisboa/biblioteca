package com.bibliotec.bookservice.usecase

import com.bibliotec.bookservice.domain.livro.gateway.LivroGateway
import com.bibliotec.bookservice.infrastructure.book.controller.models.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class BookUseCase(private val livroGateway: LivroGateway) {

    fun save(book: Book): Book {
        return this.livroGateway.save(book)
    }

    fun findById(id: Long): Book {
        return this.livroGateway.findById(id)
    }

    fun findByIsbn13(isbn13: String): Book {
        return this.livroGateway.findByIsbn13(isbn13)
    }

    fun findByNameContaining(nome: String?, pageable: Pageable?): Page<Book?>? {
        return this.livroGateway.findByNameContaining(nome, pageable)
    }

    fun deleteById(id: Long) {
        this.livroGateway.deleteById(id)
    }

    fun existsById(id: Long): Boolean {
        return this.livroGateway.existsById(id)
    }

    fun findOneByCodBarrasIgnoreCase(codBarras: String): Book {
        return this.livroGateway.findOneByCodBarrasIgnoreCase(codBarras)
    }


}