package mx.edu.utez.MT09_Jose_Flores_CA2.service.impl;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.MT09_Jose_Flores_CA2.dto.CursoDTO;
import mx.edu.utez.MT09_Jose_Flores_CA2.exception.BusinessRuleException;
import mx.edu.utez.MT09_Jose_Flores_CA2.exception.ResourceNotFoundException;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.Curso;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.EstadoCurso;
import mx.edu.utez.MT09_Jose_Flores_CA2.repository.CursoRepository;
import mx.edu.utez.MT09_Jose_Flores_CA2.service.CursoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
    private  final CursoRepository cursoRepository;

    @Override
    public Curso registrarCurso(CursoDTO dto) {
        return null;
    }

    @Override
    public Curso resgistrarCurso(CursoDTO dto){
        Curso curso = Curso.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .estado(EstadoCurso.DISPONIBLE)
                .build();
        return cursoRepository.save(curso);
    }

    @Override
    public List<Curso> listarCursosDisponibles() {
        return cursoRepository.findByEstado(EstadoCurso.DISPONIBLE);
    }

    @Override
    public List listarCursoDisponible() {
        return List.of();
    }


    @Override
    public List<Curso> listarTodosLosCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso cambiarEstadoCurso(Long id, EstadoCurso nuevoEstado) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con el ID: " + id));

        if (curso.getEstado() == EstadoCurso.FINALIZADO) {
            throw new BusinessRuleException("No se puede cambiar el estado de un curso que ya ha FINALIZADO.");
        }

        if (curso.getEstado() == EstadoCurso.DISPONIBLE && nuevoEstado != EstadoCurso.EN_CURSO) {
            throw new BusinessRuleException("Transición no válida. Un curso DISPONIBLE solo puede pasar a EN_CURSO.");
        }

        if (curso.getEstado() == EstadoCurso.EN_CURSO && nuevoEstado != EstadoCurso.FINALIZADO) {
            throw new BusinessRuleException("Transición no válida. Un curso EN_CURSO solo puede pasar a FINALIZADO.");
        }

        curso.setEstado(nuevoEstado);
        return cursoRepository.save(curso);
    }
}
