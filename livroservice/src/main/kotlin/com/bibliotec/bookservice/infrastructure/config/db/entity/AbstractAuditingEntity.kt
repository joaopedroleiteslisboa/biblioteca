package com.bibliotec.bookservice.infrastructure.config.db.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open abstract class AbstractAuditingEntity(

)