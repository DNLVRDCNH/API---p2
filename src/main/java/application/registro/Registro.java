package application.registro;

import java.time.LocalDate;

import application.curso.Curso;
import application.aluno.Aluno;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registrations")
@Getter
@Setter
@NoArgsConstructor
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "registrationDate", nullable = false)
    private LocalDate dataRegistro;
    
    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Curso curso;

    public Registro(LocalDate dataRegistro, String status, Aluno aluno, Curso curso) {
        this.dataRegistro = dataRegistro;
        this.status = status;
        this.aluno = aluno;
        this.curso = curso;
    }
}