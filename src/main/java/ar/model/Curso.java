package ar.model;

import java.util.Map;
import java.util.UUID;

public class Curso {

    private UUID id;
    private String nombre;
    private int cantHoras;

    public Curso(String nombre, int cantHoras){
        this.nombre = nombre;
        this.cantHoras = cantHoras;
    }

    public String nombre (){
        return this.nombre;
    }

    public Map<String, Object> toMap() {
        return Map.of("nombre", nombre, "cantHs", cantHoras);
    }

    public boolean containsNombre(String nombre) {
        return this.nombre.contains(nombre);
    }


    @Override
    public String toString() {
        return "Curso [curso=" + nombre + "]";
    }

}
