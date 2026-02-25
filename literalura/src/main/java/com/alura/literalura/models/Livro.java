package com.alura.literalura.models;

import com.alura.literalura.dto.DadosLivro;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long gutendexId;

    private String titulo;
    private String idioma;
    private Integer numeroDownloads;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro(DadosLivro dadosLivro, Autor autor) {
        this.gutendexId = dadosLivro.gutendexId();
        this.titulo = dadosLivro.titulo();
        this.autor = autor;
        this.idioma = String.join(", ", dadosLivro.idiomas());
        this.numeroDownloads = dadosLivro.numeroDownloads();
    }

    public Livro () {}

    public Long getGutendexId() {
        return gutendexId;
    }

    public void setGutendexId(Long gutendexId) {
        this.gutendexId = gutendexId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return
                " titulo= " + titulo +
                " autor= " + (autor != null ? autor.getNome() : "null") +
                " idioma= " + idioma +
                " numeroDownloads= " + numeroDownloads;
    }
}
