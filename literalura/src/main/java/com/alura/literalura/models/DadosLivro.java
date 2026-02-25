package com.alura.literalura.models;

import com.alura.literalura.dto.AutorDto;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo,
                         @JsonAlias("authors") List<AutorDto> autores,
                         @JsonAlias("languages") List<String> idiomas,
                         @JsonAlias("download_count") Integer numeroDownloads){

}
