package mx.edu.utez.MT09_Jose_Flores_CA2.service.impl;

import mx.edu.utez.MT09_Jose_Flores_CA2.dto.InscripcionDTO;
import mx.edu.utez.MT09_Jose_Flores_CA2.exception.BusinessRuleException;
import mx.edu.utez.MT09_Jose_Flores_CA2.exception.ResourceNotFoundException;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.Curso;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.EstadoCurso;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.EstadoInscripcion;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.Inscripcion;
import mx.edu.utez.MT09_Jose_Flores_CA2.repository.CursoRepository;
import mx.edu.utez.MT09_Jose_Flores_CA2.repository.InscripcionRepository;
import mx.edu.utez.MT09_Jose_Flores_CA2.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl  implements InscripcionService {

    private  final InscripcionRepository inscripcionRepository;
    private  final CursoRepository cursoRepository;

    @Override
    public Inscripcion inscribirPaticipante(InscripcionDTO dto){
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + dto.getCursoId()));

        if (curso.getEstado() != EstadoCurso.DISPONIBLE) {
            throw new BusinessRuleException("Solo es posible inscribirse a cursos en estado DISPONIBLE.");
        }

        Inscripcion inscripcion = Inscripcion.builder()
                .nombreParticipante(dto.getNombreParticipante())
                .edadParticipante(dto.getEdadParticipante())
                .correo(dto.getCorreo())
                .fechaInscripcion(LocalDate.now())
                .estado(EstadoInscripcion.ACTIVA)
                .calificacion(null)
                .curso(curso)
                .build();

        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public Inscripcion cancelarInscripcion(Long id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con ID: " + id));

        if (inscripcion.getEstado() != EstadoInscripcion.ACTIVA) {
            throw new BusinessRuleException("Solo se pueden cancelar inscripciones en estado ACTIVA.");
        }

        if (LocalDate.now().isAfter(inscripcion.getCurso().getFechaInicio()) ||
                LocalDate.now().isEqual(inscripcion.getCurso().getFechaInicio())) {
            throw new BusinessRuleException("No se puede cancelar la inscripción porque el curso ya ha comenzado.");
        }

        inscripcion.setEstado(EstadoInscripcion.CANCELADA);
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public List<Inscripcion> consultarPorCorreo(String correo) {
        return inscripcionRepository.findByCorreo(correo);
    }

    @Override
    public List consultarPorCurso(String cursoId) {
        return List.of();
    }

    @Override
    public List<Inscripcion> consultarPorCurso(Long cursoId) {
        if (!cursoRepository.existsById(cursoId)) {
            throw new ResourceNotFoundException("El curso especificado no existe.");
        }
        return inscripcionRepository.findByCursoId(cursoId);
    }

    @Override
    public Inscripcion consultarDetalle(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con ID: " + id));
    }
    @Override
    public List<Inscripcion> consultarPorEstado(EstadoInscripcion estado) {
        return inscripcionRepository.findByEstado(estado);
    }

    @Override
    public Inscripcion resgistrarCalificacion(Long id, Double calificacion) {
        return null;
    }

    @Override
    public Inscripcion registrarCalificacion(Long id, Double calificacion) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con ID: " + id));

        if (inscripcion.getCurso().getEstado() != EstadoCurso.FINALIZADO) {
            throw new BusinessRuleException("Solo se pueden calificar participantes de cursos en estado FINALIZADO.");
        }

        if (inscripcion.getEstado() != EstadoInscripcion.ACTIVA) {
            throw new BusinessRuleException("Solo se puede registrar calificación a inscripciones en estado ACTIVA.");
        }

        inscripcion.setCalificacion(calificacion);
        inscripcion.setEstado(EstadoInscripcion.COMPLETADA);
        return inscripcionRepository.save(inscripcion);
    }
}
