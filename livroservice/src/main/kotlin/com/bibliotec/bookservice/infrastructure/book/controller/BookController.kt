package com.bibliotec.bookservice.infrastructure.book.controller

import com.bibliotec.bookservice.infrastructure.book.controller.models.Book
import com.bibliotec.bookservice.infrastructure.config.db.querys.BookFilters
import com.bibliotec.bookservice.usecase.BookUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/book")
class BookController(val bookUseCase: BookUseCase) {

    @GetMapping("/all")
    fun listarComFiltros(bookFilters: BookFilters, page: Pageable): Page<Book?>? = this.bookUseCase.findFilters(bookFilters, page)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@Valid @RequestBody body: Book): Book {
        return this.bookUseCase.save(body)
    }


}