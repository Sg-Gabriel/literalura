package com.alura.literalura.dto;

import com.alura.literalura.models.DadosLivro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDto(@JsonAlias("results") List<DadosLivro> resultados) {

}
