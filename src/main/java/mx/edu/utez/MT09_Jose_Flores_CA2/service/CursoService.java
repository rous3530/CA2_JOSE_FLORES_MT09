package mx.edu.utez.MT09_Jose_Flores_CA2.service;

import mx.edu.utez.MT09_Jose_Flores_CA2.dto.CursoDTO;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.Curso;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.EstadoCurso;

import java.util.List;

public interface CursoService {
    Curso registrarCurso(CursoDTO dto);

    Curso resgistrarCurso(CursoDTO dto);

    List<Curso> listarCursosDisponibles();

    List listarCursoDisponible();
    List listarTodosLosCursos();
    Curso cambiarEstadoCurso(Long id, EstadoCurso nuevoEstado);

}
