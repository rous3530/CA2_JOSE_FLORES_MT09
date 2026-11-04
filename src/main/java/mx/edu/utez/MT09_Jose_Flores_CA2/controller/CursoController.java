package mx.edu.utez.MT09_Jose_Flores_CA2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.MT09_Jose_Flores_CA2.dto.CursoDTO;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.Curso;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.EstadoCurso;
import mx.edu.utez.MT09_Jose_Flores_CA2.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {


    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> registrarCurso(@Valid @RequestBody CursoDTO dto) {
        return new ResponseEntity<>(cursoService.registrarCurso(dto), HttpStatus.CREATED);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Curso>> listarCursosDisponibles() {
        return ResponseEntity.ok(cursoService.listarCursosDisponibles());
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarTodosLosCursos() {
        return ResponseEntity.ok(cursoService.listarTodosLosCursos());
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Curso> cambiarEstadoCurso(
            @PathVariable Long id,
            @RequestParam EstadoCurso nuevoEstado) {
        return ResponseEntity.ok(cursoService.cambiarEstadoCurso(id, nuevoEstado));
    }



}