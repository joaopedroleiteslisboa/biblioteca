package com.bibliotec.livroservice.usecase

import com.bibliotec.livroservice.domain.livro.gateway.LivroGateway
import com.bibliotec.livroservice.infrastructure.livro.controller.dto.LivroDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.UUID
import javax.transaction.Transactional

@Component
class LivroUseCase(
    private val livroGateway: LivroGateway
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun save(livro: LivroDTO): LivroDTO? {
        log.info("M=save")

        livro.codBarras = UUID.randomUUID().toString()

        return livroGateway.save(livro)
    }


}