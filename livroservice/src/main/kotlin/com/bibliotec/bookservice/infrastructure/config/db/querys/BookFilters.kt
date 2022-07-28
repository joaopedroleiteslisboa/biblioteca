package com.bibliotec.bookservice.infrastructure.config.db.querys

import com.bibliotec.bookservice.infrastructure.config.db.entity.LanguageEnum
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class BookFilters(
        var id: Long?,
        var idPublisher: Long?,
        var edition: String?,
        var barcode: String?,
        var isbn13: String?,
        var language: LanguageEnum?,
        var name: String?,
        var description: String?,

        @field:JsonFormat(pattern = "yyyy-MM-dd")
        @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        var initialDateCreate: LocalDate?,

        @field:JsonFormat(pattern = "yyyy-MM-dd")
        @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        var endDateDateCreate: LocalDate?,

        var idsCategorys: List<Long>?,
        var idsAuthors: List<Long>?
)