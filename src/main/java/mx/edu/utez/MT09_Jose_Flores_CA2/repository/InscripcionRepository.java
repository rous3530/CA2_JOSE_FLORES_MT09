package mx.edu.utez.MT09_Jose_Flores_CA2.repository;

import mx.edu.utez.MT09_Jose_Flores_CA2.model.EstadoInscripcion;
import mx.edu.utez.MT09_Jose_Flores_CA2.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository  extends JpaRepository<Inscripcion, Long> {
List findByCorreo(String correo);
List findByCursoId(Long cursoId);
List findByEstado(EstadoInscripcion estado);
}

