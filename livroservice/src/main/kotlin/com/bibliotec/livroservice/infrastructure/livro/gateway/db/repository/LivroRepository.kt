package com.bibliotec.livroservice.infrastructure.livro.gateway.db.repository

import com.bibliotec.livroservice.infrastructure.config.db.LivroEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface LivroRepository: JpaRepository<LivroEntity, Long> {

    fun findByIsbn13(cpf: String?): Optional<LivroEntity?>?

    fun findByNomeContaining(nome: String?, pageable: Pageable?): Page<LivroEntity?>?

    fun deleteById(id: Long?)

    fun existsById(id: Long?): Boolean

    fun findOneByCodBarrasIgnoreCase(codBarras: String?): Optional<LivroEntity?>?

    fun findOneByIsbn13IgnoreCase(isbn13: String?): Optional<LivroEntity?>?

}