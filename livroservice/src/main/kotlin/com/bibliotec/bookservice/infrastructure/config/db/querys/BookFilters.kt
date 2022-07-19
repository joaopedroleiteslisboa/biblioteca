package com.bibliotec.bookservice.infrastructure.config.db.querys

import com.bibliotec.bookservice.infrastructure.config.db.entity.LanguageEnum
import java.time.LocalDate

class BookFilters(
        val id: Long?,
        val idPublisher: Long?,
        val edition: String?,
        val barcode: String?,
        val isbn13: String?,
        val language: LanguageEnum?,
        val name: String?,
        val description: String?,
        val initialDateCreate: LocalDate?,
        val endDateDateCreate: LocalDate?,
        val idsCategorys: List<Long>?,
        val idsAuthors: List<Long>?
)