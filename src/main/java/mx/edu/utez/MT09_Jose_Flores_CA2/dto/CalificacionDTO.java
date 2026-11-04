package mx.edu.utez.MT09_Jose_Flores_CA2.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CalificacionDTO {
    @NotNull(message = "La calificacion es obligatoria")
    @Min(value = 0, message = "la calificacion minima es 0")
    @Max(value = 10, message = "la calificacion maxima es 10")
    private double calificacion;

}
