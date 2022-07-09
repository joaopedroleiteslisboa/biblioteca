package com.bibliotec.livroservice.infrastructure.config.db

enum class StatusBook(private val statusReservado: String) {

    AVARIADO("Avariado"), ATIVO("Ativo");

    fun getstatusReservado(): String {
        return statusReservado
    }
}
