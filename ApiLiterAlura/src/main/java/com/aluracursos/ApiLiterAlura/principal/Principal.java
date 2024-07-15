package com.aluracursos.ApiLiterAlura.principal;

import com.aluracursos.ApiLiterAlura.model.DatoLibro;
import com.aluracursos.ApiLiterAlura.model.DatosGenerales;
import com.aluracursos.ApiLiterAlura.model.Libro;
import com.aluracursos.ApiLiterAlura.repository.ILibroRepository;
import com.aluracursos.ApiLiterAlura.service.ConsumoAPI;
import com.aluracursos.ApiLiterAlura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private final String NOMBRE_LIBRO = "/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private ILibroRepository repositorio;
    private List<Libro> series;
    Optional<Libro> serieBuscada;

    public Principal(ILibroRepository repository) {
        this.repositorio = repository;

    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ****************************************+
                    Elija la opcion a travez de su numero
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4.- Listar autores vivos en un determinado año
                    5.- Listar libros por idiomas
                    0 - Salir
                    ****************************************+
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1://1 - Buscar libro por titulo
                    buscarSerieWeb();
                    break;
                case 2://2 - Listar libros registrados
                    List<Libro> listaLibro =repositorio.findAll();
                    for(Libro libro:listaLibro){
                        System.out.println(libro);
                    }
                    break;
                case 3://3 - Listar autores registrados
                    List<String> listaActores =repositorio.findActors();
                    for(String libro:listaActores){
                        System.out.println(libro);
                    }
                    break;
                case 4://4.- Listar autores vivos en un determinado año
                    System.out.println("Escribe el año para encontrar autores vivos a partir de este ");
                    var nombreLibro = teclado.nextInt();
                    List<String> lista =repositorio.findActorsVivos(nombreLibro);
                    for(String libro:lista){
                        System.out.println("Actores "+libro);
                    }
                    break;
                case 5://5.- Listar libros por idiomas
                    System.out.println("Escribe el idioma que desee para filtrar los libros existentes");
                    var idioma = teclado.nextLine();
                    String idiomaIngles=obtenerIdioma(idioma);
                    List<Libro> listaLibrosIdioma =repositorio.findLibroPorIdioma(idiomaIngles);
                    for(Libro libro:listaLibrosIdioma){
                        System.out.println(libro);
                    }
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private String obtenerIdioma(String idiomaIngles) {
        switch(idiomaIngles.toUpperCase()){
            case "INGLES":
                return "[en]";
            case "ESPAÑOL":
                return "[es]";
            default:
                return "";
        }
    }

    private DatosGenerales getDatosSerie() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE +NOMBRE_LIBRO+ nombreLibro.replace(" ", "+"));
        System.out.println("Json "+json);
        System.out.println(json);
        DatosGenerales datos = conversor.obtenerDatos(json, DatosGenerales.class);
        System.out.println("Datos : "+datos);
        return datos;
    }
    private void buscarSerieWeb() {
        DatosGenerales datos = getDatosSerie();
        System.out.println(datos);
        //datosSeries.add(datos);
        Libro libro= new Libro(datos);
        //System.out.println("Libro: "+libro);
        repositorio.save(libro);
    }

}
