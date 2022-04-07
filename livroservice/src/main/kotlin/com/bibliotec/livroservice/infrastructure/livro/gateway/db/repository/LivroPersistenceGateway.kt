package com.bibliotec.livroservice.infrastructure.livro.gateway.db.repository

import com.bibliotec.livroservice.domain.livro.gateway.LivroGateway
import com.bibliotec.livroservice.infrastructure.config.db.AutorEntity
import com.bibliotec.livroservice.infrastructure.config.db.CategoriaEntity
import com.bibliotec.livroservice.infrastructure.config.db.EditoraEntity
import com.bibliotec.livroservice.infrastructure.config.db.LivroEntity
import com.bibliotec.livroservice.infrastructure.livro.controller.dto.LivroDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class LivroPersistenceGateway(private val livroRepository: LivroRepository) : LivroGateway {


    //Todo criar classe de converter para ser reutilizada nas demais consultas abaixo


    override fun save(dto: LivroDTO) {

        this.livroRepository.save(
            LivroEntity(
                id = dto.id,
                codBarras = dto.codBarras,
                createdBy = dto.createdBy,
                createdDate = dto.createdDate,
                lastModifiedBy = dto.lastModifiedBy,
                lastModifiedDate = dto.lastModifiedDate,
                imagenUrl = dto.imagenUrl,
                nome = dto.nome,
                autoresEntity = dto.autoresEntity.map {
                    AutorEntity(
                        id = it.id,
                        createdBy = it.createdBy,
                        createdDate = it.createdDate,
                        lastModifiedBy = it.lastModifiedBy,
                        lastModifiedDate = it.lastModifiedDate,
                        nome = it.nome,
                        descricao = it.descricao
                    )
                }.toSet(),
                editora = EditoraEntity(
                    id = dto.editora!!.id,
                    createdBy = dto.editora!!.createdBy,
                    createdDate = dto.editora!!.createdDate,
                    lastModifiedBy = dto.editora!!.lastModifiedBy,
                    lastModifiedDate = dto.editora!!.lastModifiedDate,
                    nome = dto.editora!!.nome,
                    descricao = dto.editora!!.descricao
                ),
                edicao = dto.edicao,
                idiomaEnum = dto.idiomaEnum,
                categorias = dto.categorias.map {
                    CategoriaEntity(
                        id = it.id,
                        createdBy = it.createdBy,
                        createdDate = it.createdDate,
                        lastModifiedBy = it.lastModifiedBy,
                        lastModifiedDate = it.lastModifiedDate,
                        nome = it.nome
                    )
                }.toSet(),
                descricao = dto.descricao,
                isbn13 = dto.isbn13,
                numeroPaginas = dto.numeroPaginas,
                dataPublicacao = dto.dataPublicacao,
                valorUnitario = dto.valorUnitario,
                quantidade = dto.quantidade
            )
        )

    }

    override fun findByIsbn13(isbn13: String?): Optional<LivroEntity?>? = this.livroRepository.findByIsbn13(isbn13)

    override fun findByNomeContaining(nome: String?, pageable: Pageable?): Page<LivroEntity?>? =
        this.livroRepository.findByNomeContaining(nome, pageable)

    override fun deleteById(id: Long?) = this.livroRepository.deleteById(id)

    override fun existsById(id: Long?): Boolean? = this.livroRepository.existsById(id)

    override fun findOneByCodBarrasIgnoreCase(codBarras: String?): Optional<LivroEntity?>? =
        this.findOneByCodBarrasIgnoreCase(codBarras)

    override fun findOneByIsbn13IgnoreCase(isbn13: String?): Optional<LivroEntity?>? =
        this.findOneByIsbn13IgnoreCase(isbn13)

}