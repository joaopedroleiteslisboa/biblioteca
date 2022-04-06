package com.bibliotec.livroservice.infrastructure.config.db

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.Objects
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.EntityListeners
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected var id: Long? = null

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 70, updatable = false)
    private val createdBy: String? = null

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private val createdDate = Instant.now()

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 70)
    private val lastModifiedBy: String? = null

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private val lastModifiedDate = Instant.now()

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as AbstractAuditingEntity
        return id == that.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

}