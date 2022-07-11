package com.bibliotec.bookservice.infrastructure.config.db.entity

enum class StatusBook(private val statusReservado: String) {

    AVARIADO("Avariado"), ATIVO("Ativo");

    fun getstatusReservado(): String {
        return statusReservado
    }
}
