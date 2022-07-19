package com.bibliotec.bookservice.infrastructure.config.db.querys

import com.bibliotec.bookservice.infrastructure.config.db.entity.BookEntity
import com.bibliotec.bookservice.infrastructure.config.db.entity.LanguageEnum
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Join
import javax.persistence.criteria.Root
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Expression

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
                    val join: Join<Any, Any> = root.join("publisher")
                    cb.equal(join.get<Any>("id"), idPublisher)
                }
            }
        }

        fun findByEdition(edition: String?): Specification<BookEntity?>? {
            return if (edition == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val BookEntity =
                            root.join<Any, Any>("edition")
                    cb.like(cb.upper(BookEntity.get("edition")), "%" + edition.toUpperCase() + "%")
                }
            }
        }

        fun findByBarCode(barcode: String?): Specification<BookEntity?>? {
            return if (barcode == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val BookEntity =
                            root.join<Any, Any>("barcode")
                    cb.like(cb.upper(BookEntity.get("barcode")), "%" + barcode.toUpperCase() + "%")
                }
            }
        }

        fun findByIsbn13(isbn13: String?): Specification<BookEntity?>? {
            return if (isbn13 == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val BookEntity =
                            root.join<Any, Any>("isbn13")
                    cb.like(cb.upper(BookEntity.get("isbn13")), "%" + isbn13.toUpperCase() + "%")
                }
            }
        }

        fun findByLanguageEnum(language: LanguageEnum?): Specification<BookEntity?>? {
            return if (language == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder
                    ->
                    cb.equal(root.get<Any>("languageEnum"), language)
                }
            }
        }

        fun findByNameContaining(name: String?): Specification<BookEntity?>? {
            return if (name == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val BookEntity =
                            root.join<Any, Any>("name")
                    cb.like(cb.upper(BookEntity.get("name")), "%" + name.toUpperCase() + "%")
                }
            }
        }

        fun findByDescriptionContaining(description: String?): Specification<BookEntity?>? {
            return if (description == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val BookEntity =
                            root.join<Any, Any>("description")
                    cb.like(cb.upper(BookEntity.get("description")), "%" + description.toUpperCase() + "%")
                }
            }
        }

        fun findByCreationDate(initialDate: LocalDate?, endDate: LocalDate?): Specification<BookEntity?>? {
            return if (initialDate == null || endDate == null) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder -> cb.between(root.get("createdDate"), initialDate, endDate) }
            }
        }

        fun hasCategorys(idsCategorys: List<Long>?): Specification<BookEntity?>? {
            return if (idsCategorys == null || idsCategorys.isEmpty()) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val joinCategorys = root.join<Any, Any>("categorys")
                    val categorysIdsPredicate: Predicate = cb.disjunction()

                    idsCategorys.forEach {
                        categorysIdsPredicate.expressions.add(cb.equal(joinCategorys.get<Any>("id"), it));
                    }

                    val count: Expression<Long> = cb.count(root.get<Any>("id"))
                    val cq: CriteriaQuery<BookEntity> = query!!.distinct(true).groupBy(root.get<Any>("name"))
                            .having(cb.greaterThanOrEqualTo(count, idsCategorys.size.toLong())) as CriteriaQuery<BookEntity>
                    categorysIdsPredicate
                }
            }
        }

        fun hasAthors(idsAuthors: List<Long>?): Specification<BookEntity?>? {
            return if (idsAuthors == null || idsAuthors.isEmpty()) {
                null
            } else {
                Specification<BookEntity?> { root: Root<BookEntity?>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                    val joinAuthorsEntity = root.join<Any, Any>("authorsEntity")
                    val AuthorsIdsPredicate: Predicate = cb.disjunction()

                    idsAuthors.forEach {
                        AuthorsIdsPredicate.expressions.add(cb.equal(joinAuthorsEntity.get<Any>("id"), it));
                    }

                    val count: Expression<Long> = cb.count(root.get<Any>("id"))
                    val cq: CriteriaQuery<BookEntity> = query!!.distinct(true).groupBy(root.get<Any>("name"))
                            .having(cb.greaterThanOrEqualTo(count, idsAuthors.size.toLong())) as CriteriaQuery<BookEntity>
                    AuthorsIdsPredicate
                }
            }
        }
    }
}
