package com.bibliotec.livroservice.domain.livro.gateway

import com.bibliotec.livroservice.infrastructure.config.db.BookEntity
import com.bibliotec.livroservice.infrastructure.book.controller.dto.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface LivroGateway {


    fun save(book: Book): Book

    fun findByIsbn13(isbn13: String): Optional<Book?>?

    fun findByNomeContaining(nome: String, pageable: Pageable?): Page<Book?>?

    fun deleteById(id: Long)

    fun existsById(id: Long): Boolean?

    fun findOneByCodBarrasIgnoreCase(codBarras: String): Book

    fun findOneByIsbn13IgnoreCase(isbn13: String): Book?

}

