package ar.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.model.*;
import ar.servicios.Cursos;
import ar.servicios.Estudiantes;
import ar.servicios.Localidades;
import ar.servicios.Personas;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class WebAPI {

 private Personas personas;
 private Localidades localidades;
 private Estudiantes estudiantes;
 private Cursos cursos;
 private int webPort;

 public WebAPI(Localidades localidades, Personas personas, Estudiantes estudiantes, Cursos cursos, int webPort) {
  this.personas = personas;
  this.localidades = localidades;
  this.estudiantes = estudiantes;
  this.cursos = cursos;
  this.webPort = webPort;
 }

 public void start() {
  Javalin app = Javalin.create(config -> {
   config.enableCorsForAllOrigins();
  }).start(this.webPort);
  app.get("/personas", traerPersonas());
  app.get("/localidades", traerLocalidades());
  app.get("/estudiantes", traerEstudiantes());
  app.get("/cursos", traerCursos());
  app.post("/personas", crearPersona());
  app.post("/estudiantes", crearEstudiante());

  app.exception(PersonaException.class, (e, ctx) -> {
   ctx.json(Map.of("result", "error", "message", e.getMessage()));
   // log error in a stream...
  });  
  
  app.exception(Exception.class, (e, ctx) -> {
   ctx.json(Map.of("result", "error", "message", "Ups... algo se rompió.: " + e.getMessage()));
   // log error in a stream...
  });
 }

 private Handler crearEstudiante() {
  return ctx -> {
   EstudianteDTO dto = ctx.bodyAsClass(EstudianteDTO.class);
   this.estudiantes.crearEstudiante(dto.getApellido(), dto.getNombre(), dto.getCursos() );
   ctx.json(Map.of("result", "success"));
  };
 }

 private Handler traerLocalidades() {
  return ctx -> {
   var localidades = this.localidades.localidades();
   var list = new ArrayList<Map<String, Object>>();
   for (Localidad l : localidades) {
    list.add(l.toMap());
   }
   ctx.json(Map.of("result", "success", "localidades", list));
  };
 }

 private Handler crearPersona() {
  return ctx -> {
   PersonaDto dto = ctx.bodyAsClass(PersonaDto.class);
   this.personas.crearPersona(dto.getNombre(), dto.getApellido(),
     dto.getDireccion(), dto.getTelefonos(), dto.getLocalidad());
   ctx.json(Map.of("result", "success"));
  };
 }

 private Handler traerPersonas() {
  return ctx -> {
   String apellido = ctx.queryParam("apellido");
   List<Persona> personas = this.personas.personas(apellido);

   var list = new ArrayList<Map<String, Object>>();

   for (Persona p : personas) {
    list.add(p.toMap());
   }

   ctx.json(Map.of("result", "success", "personas", list));

  };
 }

 private Handler traerEstudiantes() {
  return ctx -> {
   String apellido = ctx.queryParam("apellido");
   List<Estudiante> estudiantes = this.estudiantes.estudiantes(apellido);

   var list = new ArrayList<Map<String, Object>>();

   for (Estudiante e : estudiantes) {
    list.add(e.toMap());
   }

   ctx.json(Map.of("result", "success", "estudiantes", list));

  };
 }

 private Handler traerCursos() {
  return ctx -> {
   String nombre = ctx.queryParam("nombre");
   List<Curso> cursos = this.cursos.cursos(nombre);

   var list = new ArrayList<Map<String, Object>>();

   for (Curso c : cursos) {
    list.add(c.toMap());
   }

   ctx.json(Map.of("result", "success", "cursos", list));

  };
 }
}
