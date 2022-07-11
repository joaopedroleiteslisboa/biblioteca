package com.bibliotec.bookservice.infrastructure.book.controller

import com.bibliotec.bookservice.usecase.BookUseCase
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController(val bookUseCase: BookUseCase) {


}