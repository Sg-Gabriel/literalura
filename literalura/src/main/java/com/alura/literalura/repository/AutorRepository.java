package com.alura.literalura.repository;

import com.alura.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    @Query("SELECT a From Autor a WHERE a.anoNascimento <= :ano AND a.anoMorte > :ano")
    List<Autor> encontrarPorAno(int ano);
}
