package com.aluracursos.ApiLiterAlura.repository;

import com.aluracursos.ApiLiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILibroRepository  extends JpaRepository<Libro,Long> {
    @Query("SELECT l.actor FROM Libro l")
    List<String>  findActors();
    @Query("SELECT l.actor FROM Libro l WHERE l.actorAñoFallecimiento>:dato")
    List<String>  findActorsVivos(int dato);

    @Query("SELECT l FROM Libro l WHERE l.idioma=:idiomaIngles")
    List<Libro> findLibroPorIdioma(String idiomaIngles);
}
