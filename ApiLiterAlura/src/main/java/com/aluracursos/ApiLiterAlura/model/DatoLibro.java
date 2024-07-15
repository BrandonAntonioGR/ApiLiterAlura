package com.aluracursos.ApiLiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatoAutores> actor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Integer numeroDescargas
) {
}