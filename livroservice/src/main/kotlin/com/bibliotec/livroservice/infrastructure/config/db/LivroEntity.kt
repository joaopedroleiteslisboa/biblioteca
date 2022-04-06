package com.bibliotec.livroservice.infrastructure.config.db

import org.hibernate.validator.constraints.Range
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.ManyToMany
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.Enumerated
import javax.persistence.ManyToOne
import javax.persistence.EnumType
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_livro")
class LivroEntity : AbstractAuditingEntity() {

    companion object {
        /**
         *
         */
        private const val serialVersionUID = -7879526825677312026L
    }

    @Column(name = "codBarras", length = 13)
    private val codBarras: String? = null

    @Size(max = 256)
    @Column(name = "imagenUrl", length = 256)
    private val imagenUrl: String? = null

    @NotBlank(message = "Digite um nome de livro")
    @Size(max = 200, message = "Nome de livro muito grande")
    @Column(name = "nome")
    private val nome: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "livro_has_autores",
        joinColumns = [JoinColumn(name = "id_livro")],
        inverseJoinColumns = [JoinColumn(name = "id_autor")]
    )
    private val autoresEntity: Set<AutorEntity> = HashSet<AutorEntity>()


    @JoinColumn(name = "idEditora")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private val editora: EditoraEntity? = null

    @NotNull(message = "Este campo é obrigatorio")
    @Column(name = "edicao")
    private val edicao: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "idioma")
    private val idiomaEnum: IdiomaEnum? = null


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "livro_has_categorias",
        joinColumns = [JoinColumn(name = "id_livro")],
        inverseJoinColumns = [JoinColumn(name = "id_categoria")]
    )
    private val categorias: Set<CategoriaEntity> = HashSet<CategoriaEntity>()

    @Size(max = 8388607, min = 3, message = "Descrição do livro muito grande")
    @Column(name = "descricao")
    private val descricao: String? = null

    @NotNull(message = "Insira o ISBN do livro")
    @Column(name = "isbn13")
    private val isbn13: String? = null

    @NotNull(message = "O livro deve ter o número de paginas")
    @Min(value = 1, message = "O livro deve ter no minimo 1 pagina")
    @Column(name = "numeroPaginas")
    private val numeroPaginas: Int? = null

    @NotNull(message = "Insira uma data de publicação")
    @Past(message = "A data deve ser inferior a atual")
    @Column(name = "dataPublicacao")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private val dataPublicacao: LocalDate? = null

    @DecimalMin(value = "1.00", message = "O livro deve ter um preço valido")
    @NotNull(message = "Insira um preço")
    @Column(name = "valorUnitario", precision = 10, scale = 2)
    private val valorUnitario = BigDecimal.ZERO


    @NotNull(message = "Informe uma quantidade adicionada em seu Estoque de Livros")
    @Column(name = "quantidade")
    @Range(
        min = 1,
        max = 100,
        message = "Informe pelo menos {min} livro para Salvar no Estoque ou um valor maximo de {max}"
    )
    private val quantidade: Int? = null

}