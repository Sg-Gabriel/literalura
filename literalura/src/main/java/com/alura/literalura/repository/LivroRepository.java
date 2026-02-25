package com.alura.literalura.repository;

import com.alura.literalura.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository <Livro, Long> {

}
