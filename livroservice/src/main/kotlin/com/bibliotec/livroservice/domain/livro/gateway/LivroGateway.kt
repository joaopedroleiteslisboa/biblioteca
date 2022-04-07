package com.bibliotec.livroservice.domain.livro.gateway

import com.bibliotec.livroservice.infrastructure.config.db.LivroEntity
import com.bibliotec.livroservice.infrastructure.livro.controller.dto.LivroDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface LivroGateway {


    fun save(livroDTO: LivroDTO)

    fun findByIsbn13(isbn13: String?): Optional<LivroEntity?>?

    fun findByNomeContaining(nome: String?, pageable: Pageable?): Page<LivroEntity?>?

    fun deleteById(id: Long?)

    fun existsById(id: Long?): Boolean?

    fun findOneByCodBarrasIgnoreCase(codBarras: String?): Optional<LivroEntity?>?

    fun findOneByIsbn13IgnoreCase(isbn13: String?): Optional<LivroEntity?>?

}

