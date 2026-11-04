package mx.edu.utez.MT09_Jose_Flores_CA2.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.MT09_Jose_Flores_CA2.dto.CalificacionDTO;
import mx.edu.utez.MT09_Jose_Flores_CA2.dto.InscripcionDTO;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.EstadoInscripcion;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.Inscripcion;
import mx.edu.utez.MT09_Jose_Flores_CA2.service.InscripcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {


    private final InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<Inscripcion> inscribirParticipante(@Valid @RequestBody InscripcionDTO dto) {
        return new ResponseEntity<>(inscripcionService.inscribirParticipante(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Inscripcion> cancelarInscripcion(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.cancelarInscripcion(id));
    }

    @GetMapping("/correo")
    public ResponseEntity<List<Inscripcion>> consultarPorCorreo(@RequestParam String correo) {
        return ResponseEntity.ok(inscripcionService.consultarPorCorreo(correo));
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Inscripcion>> consultarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(inscripcionService.consultarPorCurso(String.valueOf(cursoId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> consultarDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.consultarDetalle(id));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<Inscripcion>> consultarPorEstado(@RequestParam EstadoInscripcion estado) {
        return ResponseEntity.ok(inscripcionService.consultarPorEstado(estado));
    }

    @PutMapping("/{id}/calificacion")
    public ResponseEntity<Inscripcion> registrarCalificacion(
            @PathVariable Long id,
            @Valid @RequestBody CalificacionDTO calificacionDTO) {
        return ResponseEntity.ok(inscripcionService.registrarCalificacion(id, calificacionDTO.getCalificacion()));
    }

```

}