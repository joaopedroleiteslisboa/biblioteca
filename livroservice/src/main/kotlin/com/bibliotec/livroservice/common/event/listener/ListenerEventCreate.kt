package com.bibliotec.livroservice.common.event.listener

import com.bibliotec.livroservice.common.CreateEventLocation
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.servlet.http.HttpServletResponse

@Component
class ListenerEventCreate : ApplicationListener<CreateEventLocation> {

    override fun onApplicationEvent(recursoCriadoEvent: CreateEventLocation) {
        val response: HttpServletResponse = recursoCriadoEvent.getResponse()!!
        val codigo: Long = recursoCriadoEvent.getIdLong()!!
        addHeaderLocation(response, codigo)
    }

    private fun addHeaderLocation(response: HttpServletResponse, id: Long) {
        val uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri()
        response.setHeader("Location", uri.toASCIIString())
    }
}
