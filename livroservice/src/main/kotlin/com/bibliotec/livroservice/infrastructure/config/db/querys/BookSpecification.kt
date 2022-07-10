package com.bibliotec.livroservice.infrastructure.config.db.querys

import com.bibliotec.livroservice.infrastructure.book.controller.models.Publisher
import com.bibliotec.livroservice.infrastructure.config.db.entity.BookEntity
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Join
import javax.persistence.criteria.Root

class BookSpecification {

    companion object {

        fun findById(id: Long?): Specification<BookEntity?>? {
            return if (id == null) {
                null
            } else {
                Specification { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    cb.equal(
                            root.get<Any>("id"), id
                    )
                }
            }
        }

        fun findByIdPublisher(idPublisher: Long?): Specification<BookEntity?>? {
            return if (idPublisher == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val join: Join<Any, Any> = root.join("setor")
                    cb.equal(join.get<Any>("id"), idPublisher)
                }
            }
        }


        fun porContatoId(idContato: Long?): Specification<BookEntity?>? {
            return if (idContato == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val join: Join<Any, Any> = root.join("contato")
                    cb.equal(join.get<Any>("id"), idContato)
                }
            }
        }


        fun porNumeroTelefone(numero: String?): Specification<BookEntity?>? {
            return if (numero == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val BookEntity = root.join<Any, Any>("contato")
                    cb.like(cb.upper(BookEntity.get("numero")), "%" + numero.toUpperCase() + "%")
                }
            }
        }


        fun porNomeBookEntity(nome: String?): Specification<BookEntity?>? {
            return if (nome == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val BookEntity =
                            root.join<Any, Any>("nome")
                    cb.like(cb.upper(BookEntity.get("nome")), "%" + nome.toUpperCase() + "%")
                }
            }
        }


        fun porEmailBookEntity(email: String?): Specification<BookEntity?>? {
            return if (email == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val contato =
                            root.join<Any, Any>("contato")
                    cb.like(cb.upper(contato.get("email")), "%" + email.toUpperCase() + "%")
                }
            }
        }


    }
}