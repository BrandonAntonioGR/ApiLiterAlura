package com.aluracursos.ApiLiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoAutores(
        @JsonAlias("name") String nombreAutor,
        @JsonAlias("birth_year") String fechaNacimiento
) {
}
