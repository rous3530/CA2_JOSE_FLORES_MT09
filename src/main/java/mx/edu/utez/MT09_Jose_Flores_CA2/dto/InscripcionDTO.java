package mx.edu.utez.MT09_Jose_Flores_CA2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class InscripcionDTO {

    @NotNull(message = "El id del curso es obligatorio")
    private  Long cursoId;

    @NotBlank(message = "El nombre del participante no puede estar vacio")
    private  String nombreParticipante;

    @NotNull(message = "La edad es requerida")
    @Positive(message = "La edad debe ser un numero positivo")
    private  Integer edadParticipante;

    @NotBlank(message = "El correo es obligatorio")
    @Positive(message = "El correo debe tener un formato valido")
    private  String correo;
}
