package com.bibliotec.livroservice.common

import org.springframework.context.ApplicationEvent
import javax.servlet.http.HttpServletResponse

class CreateEventLocation : ApplicationEvent {

    private var response: HttpServletResponse? = null
    private var idLong: Long? = null
    private var idString: String? = null

    constructor(source: Any?, response: HttpServletResponse, idLong: Long?) : super(source!!) {
        this.response = response
        this.idLong = idLong
    }

    constructor(source: Any?, response: HttpServletResponse, idString: String?) : super(source!!) {
        this.response = response
        this.idString = idString
    }

    fun getResponse(): HttpServletResponse? {
        return response
    }

    fun getIdLong(): Long? {
        return this.idLong
    }

    fun getIdString(): String? {
        return this.idString
    }

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 3955508203542987060L
    }

}
