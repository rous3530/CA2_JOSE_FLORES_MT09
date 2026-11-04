package mx.edu.utez.MT09_Jose_Flores_CA2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CursoDTO {
    @NotBlank(message = "El nombre del curso no puede estar vacio")
    private  String nombre;
    private String descripcion;

    @NotNull(message = "la fecha de inicio es requerida")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es requerida")
    private  LocalDate fechaFin;
}
