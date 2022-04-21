package ar.jdbc;

import ar.model.Curso;
import ar.model.Estudiante;
import ar.servicios.Cursos;

import java.util.List;
import java.util.stream.Collectors;

public class JdbcCursos implements Cursos {
    @Override
    public List<Curso> cursos(String nombre) {

        Curso c1 = new Curso("Angular", 24);
        Curso c2 = new Curso("React JS", 30);
        Curso c3 = new Curso("Vue JS", 21);
        Curso c4 = new Curso("Java EE", 18);
        Curso c5 = new Curso("MongoDB", 21);

        var cursos = List.of(c1, c2, c3, c4, c5);

        if (nombre == null || nombre.isEmpty())
            return cursos;

        return cursos.stream().filter((c) -> {
            return c.containsNombre(nombre);
        }).collect(Collectors.toList());
    }

    @Override
    public void crearCurso(String nombre, int cantHs) {
        Curso curso = new Curso(nombre, cantHs);
        System.out.println(curso.toString());
    }
}
