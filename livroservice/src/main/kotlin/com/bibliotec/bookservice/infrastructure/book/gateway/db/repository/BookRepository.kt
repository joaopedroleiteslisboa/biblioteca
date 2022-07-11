package com.bibliotec.bookservice.infrastructure.book.gateway.db.repository

import com.bibliotec.bookservice.infrastructure.config.db.entity.BookEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<BookEntity, Long> , JpaSpecificationExecutor<BookEntity> {

    fun findByNameContaining(nome: String?, pageable: Pageable?): Page<BookEntity?>?


    fun findOneByCodBarrasIgnoreCase(codBarras: String?): BookEntity?

    fun findOneByIsbn13IgnoreCase(isbn13: String?): BookEntity?

}