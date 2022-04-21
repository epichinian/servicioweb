package ar.jdbc;

import ar.model.Estudiante;

import ar.servicios.Estudiantes;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcEstudiantes implements Estudiantes{

    @Override
    public List<Estudiante> estudiantes(String apellido) {
        Estudiante e1 = new Estudiante("Martinez", "Juan");
        Estudiante e2 = new Estudiante("Garcia", "Carlos");
        Estudiante e3 = new Estudiante("Perez", "Ramón");

        e1.addCurso("Angular");
        e1.addCurso("VUE.JS");

        e2.addCurso("Java EE");

        e3.addCurso("React JS");

        var estudiantes = List.of(e1, e2, e3);

        if (apellido == null || apellido.isEmpty())
            return estudiantes;

        return estudiantes.stream().filter((p) -> {
            return p.containsApellido(apellido);
        }).collect(Collectors.toList());
    }

    @Override
    public void crearEstudiante(String apellido, String nombre, String[] cursos) {

        Estudiante e = new Estudiante(apellido, nombre);
        e.addCursos(cursos);

        System.out.println(e.toString());
    }
}
