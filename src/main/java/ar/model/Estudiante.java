package ar.model;

import java.util.*;
import java.util.stream.Collectors;

public class Estudiante {
    private UUID id;
    private String apellido;
    private String nombre;
    private List<Curso> cursos;

    public Estudiante(String apellido, String nombre){
        this.apellido = new NotNullNotEmpty(apellido).value();
        this.nombre =  new NotNullNotEmpty(nombre).value();
        this.cursos = new ArrayList<>();
    }

    public Estudiante(String apellido, String nombre, String curso){
        this.apellido = new NotNullNotEmpty(apellido).value();
        this.nombre =  new NotNullNotEmpty(nombre).value();
        addCurso(curso);
    }

    public String nombre() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return "Estudiante [nombre=" + nombre + ", apellido=" + apellido
                + ", cursos=" + cursos.toString() + "]";
    }

    public Map<String, Object> toMap() {
        var map = new HashMap<String, Object>(
                Map.of("nombre", nombre, "apellido", apellido));

        if (this.cursos != null && this.cursos.size() > 0) {
            map.put("cursos", cursos.stream().map((e) -> e.toMap())
                    .collect(Collectors.toList()));
        }
        return map;
    }

    public void addCurso(String nombre) {
        this.cursos.add(new Curso(new NotNullNotEmpty(nombre).value(), 25) );
    }

    public void addCursos(String[] nombre) {

        String[] cursos = new NotNullNotEmpty(nombre).values();

        var curso = List.of(cursos).stream().map((n) -> {
            return new Curso(n, 28);
        }).collect(Collectors.toList());

        this.cursos.addAll(curso);
    }

    public boolean containsApellido(String apellido) {
        return this.apellido.contains(apellido);
    }

}
