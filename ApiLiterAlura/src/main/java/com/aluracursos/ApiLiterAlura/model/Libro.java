package com.aluracursos.ApiLiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

@Entity
@Table(name="libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String actor;
    private Integer actorAñoFallecimiento;
    private String idioma;
    private Integer numeroDescarga;

    public Libro() {
    }

    public Libro(DatosGenerales libroJson) {
        this.titulo = libroJson.resultados().get(0).titulo();
        this.actor=libroJson.resultados().get(0).actor().get(0).nombreAutor().toString();
        if(libroJson.resultados().get(0).idioma()==null){
            this.idioma="";
        }else{
            this.idioma=libroJson.resultados().get(0).idioma().toString().split(",")[0].trim();
        }
        this.numeroDescarga=libroJson.resultados().get(0).numeroDescargas();
        if(libroJson.resultados().get(0).actor().get(0).fechaNacimiento().toString()!=null){
            this.actorAñoFallecimiento= Integer.parseInt(libroJson.resultados().get(0).actor().get(0).fechaNacimiento().toString());
        }else{
            this.actorAñoFallecimiento=0;
        }

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDescarga() {
        return numeroDescarga;
    }

    public void setNumeroDescarga(Integer numeroDescarga) {
        this.numeroDescarga = numeroDescarga;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getActorAñoFallecimiento() {
        return actorAñoFallecimiento;
    }

    public void setActorAñoFallecimiento(Integer actorAñoFallecimiento) {
        this.actorAñoFallecimiento = actorAñoFallecimiento;
    }

    @Override
    public String toString() {
        return  "\n*******************************" +
                "\nTitulo='" + titulo + '\'' +
                "\nActor='" + actor + '\'' +
                "\nIdioma='" + idioma + '\'' +
                "\nNumeroDescarga=" + numeroDescarga+
                "\n*******************************";
    }
}
