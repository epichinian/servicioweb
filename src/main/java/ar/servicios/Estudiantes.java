package ar.servicios;

import ar.model.Estudiante;

import java.util.List;

public interface Estudiantes {

    List<Estudiante> estudiantes(String apellido);

    void crearEstudiante(String apellido, String nombre, String[] cursos);
}
