package com.bibliotec.bookservice.infrastructure.config.customexception

class NotFoundException(val code: String) : RuntimeException(code)