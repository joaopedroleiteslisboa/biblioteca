package com.bibliotec.bookservice

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableAutoConfiguration
@SpringBootApplication
class BookServiceApplication

fun main(args: Array<String>) {
	runApplication<BookServiceApplication>(*args)
}

//Todo: criar controllers da aplicação baseado no outro projeto bibliotec existente...
//Todo: Criar camadas e consultas dinamicas com filtros...
//Todo: preparar handler de exceção da aplicação
//Todo: prepara message properties da app para o handler
//Todo: criar o config repo para app...
//Todo: verificar a implementação fo sleuth na app