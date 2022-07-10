package com.bibliotec.livroservice.infrastructure.book.controller

import com.bibliotec.livroservice.usecase.BookUseCase
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController(val bookUseCase: BookUseCase) {


}