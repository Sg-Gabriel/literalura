package com.alura.literalura.repository;

import com.alura.literalura.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository <Livro, Long> {

    Optional<Livro> findByGutendexId(Long gutendexId);

    List<Livro> findByIdioma(String idioma);

    List<Livro> findTop10ByOrderByNumeroDownloadsDesc();

    Optional<Livro> findByTituloAndAutor_Nome(String titulo, String nome);
}
