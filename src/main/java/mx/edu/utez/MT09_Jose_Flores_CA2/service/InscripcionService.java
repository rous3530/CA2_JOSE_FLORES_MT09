package mx.edu.utez.MT09_Jose_Flores_CA2.service;

import jakarta.validation.Valid;
import mx.edu.utez.MT09_Jose_Flores_CA2.dto.InscripcionDTO;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.EstadoInscripcion;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.Inscripcion;

import java.util.List;

public interface InscripcionService {
    Inscripcion inscribirPaticipante(InscripcionDTO dto);
    Inscripcion cancelarInscripcion (Long id);
    List consultarPorCorreo(String correo);
    List consultarPorCurso(String cursoId);

    List<Inscripcion> consultarPorCurso(Long cursoId);

    Inscripcion consultarDetalle(Long id);
    List consultarPorEstado(EstadoInscripcion estado);
    Inscripcion resgistrarCalificacion(Long id, Double calificacion);

    Inscripcion registrarCalificacion(Long id, Double calificacion);

    Inscripcion inscribirParticipante(@Valid InscripcionDTO dto);
}
