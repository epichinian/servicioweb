package ar.servicios;

import ar.model.Curso;
import ar.model.Estudiante;

import java.util.List;

public interface Cursos {

    List<Curso> cursos(String nombre);

    void crearCurso(String nombre, int cantHs);

}
