package mx.edu.utez.MT09_Jose_Flores_CA2.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


    @Entity
    @Table(name = "cursos")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Curso {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String nombre;

        private String descripcion;

        @Column(nullable = false)
        private LocalDate fechaInicio;

        @Column(nullable = false)
        private LocalDate fechaFin;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private EstadoCurso estado;
}
