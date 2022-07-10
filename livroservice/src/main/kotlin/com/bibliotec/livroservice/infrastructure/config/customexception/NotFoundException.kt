package com.bibliotec.livroservice.infrastructure.config.customexception

class NotFoundException(val code: String) : RuntimeException(code)