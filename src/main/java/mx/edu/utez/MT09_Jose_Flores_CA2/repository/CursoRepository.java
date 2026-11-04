package mx.edu.utez.MT09_Jose_Flores_CA2.repository;

import mx.edu.utez.MT09_Jose_Flores_CA2.model.Curso;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.EstadoCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository  extends JpaRepository<Curso, Long>{
    List FindByEstado (EstadoCurso estado);

    List<Curso> findByEstado(EstadoCurso estadoCurso);
}
