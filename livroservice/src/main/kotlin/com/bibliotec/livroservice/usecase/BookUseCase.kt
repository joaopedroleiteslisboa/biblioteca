package com.bibliotec.livroservice.usecase

import com.bibliotec.livroservice.domain.livro.gateway.LivroGateway
import com.bibliotec.livroservice.infrastructure.book.controller.dto.Book
import com.bibliotec.livroservice.infrastructure.config.db.BookEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.UUID
import javax.transaction.Transactional

@Component
class BookUseCase(
    private val livroGateway: LivroGateway
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun save(book: Book): Book? {
        log.info("M=save, name=${book.nome}")

        return livroGateway.save(BookEntity.createFromBook(book))
    }


}