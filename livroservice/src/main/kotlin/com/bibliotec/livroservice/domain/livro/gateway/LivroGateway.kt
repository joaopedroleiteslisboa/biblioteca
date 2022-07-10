package com.bibliotec.livroservice.domain.livro.gateway

import com.bibliotec.livroservice.infrastructure.book.controller.models.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface LivroGateway {

    fun findById(id: Long): Book

    fun save(book: Book): Book

    fun findByIsbn13(isbn13: String): Book

    fun findByNameContaining(nome: String?, pageable: Pageable?): Page<Book?>?

    fun deleteById(id: Long)

    fun existsById(id: Long): Boolean

    fun findOneByCodBarrasIgnoreCase(codBarras: String): Book
}