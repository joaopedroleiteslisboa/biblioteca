package com.bibliotec.livroservice.infrastructure.book.gateway.db.repository

import com.bibliotec.livroservice.infrastructure.config.db.BookEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface BookRepository: JpaRepository<BookEntity, Long> {

    fun findByIsbn13(cpf: String?): BookEntity?

    fun findByNomeContaining(nome: String?, pageable: Pageable?): Page<BookEntity?>?

    fun deleteById(id: Long?)

    fun existsById(id: Long?): Boolean

    fun findOneByCodBarrasIgnoreCase(codBarras: String?): Optional<BookEntity?>?

    fun findOneByIsbn13IgnoreCase(isbn13: String?): Optional<BookEntity?>?

}