package application.aluno;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String nome;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "telephone", nullable = false)
    private String telefone;
    
    @Column(name = "registrationDate", nullable = false)
    private LocalDate dataRegistro;

    public Aluno(String nome, String email, String telefone, LocalDate dataRegistro) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataRegistro = dataRegistro;
    }

    public Aluno(AlunoDTO aluno) {
        this.id = aluno.id();
        this.nome = aluno.nome();
        this.email = aluno.email();
        this.telefone = aluno.telefone();
        this.dataRegistro = aluno.dataRegistro();
    }

    public Aluno(AlunoInsertDTO aluno) {
        this.nome = aluno.nome();
        this.email = aluno.email();
        this.telefone = aluno.telefone();
        this.dataRegistro = aluno.dataRegistro();
    }
}