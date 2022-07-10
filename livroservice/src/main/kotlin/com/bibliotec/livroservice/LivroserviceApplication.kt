package com.bibliotec.livroservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LivroserviceApplication

fun main(args: Array<String>) {
	runApplication<LivroserviceApplication>(*args)
}

//Todo: criar controllers da aplicação baseado no outro projeto bibliotec existente...
//Todo: Criar camadas e consultas dinamicas com filtros...
//Todo: preparar handler de exceção da aplicação
//Todo: prepara message properties da app para o handler
//Todo: criar o config repo para app...
//Todo: verificar a implementação fo sleuth na app