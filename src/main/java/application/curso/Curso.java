package application.curso;

import java.time.LocalDateTime;

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
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "name", nullable = false)
    private String nome; 
    
    @Column(name = "description", nullable = false)
    private String descricao;
    
    @Column(name = "courseLoad", nullable = false)
    private Integer cargaHoraria;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "creationTime", nullable = false)
    private LocalDateTime horaCriacao;

    public Curso(String nome, String descricao, Integer cargaHoraria, String status, LocalDateTime horaCriacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.status = status;
        this.horaCriacao = horaCriacao;
    }

    public Curso(CursoInsertDTO data) {
        this.nome = data.nome();
        this.descricao = data.descricao();
        this.cargaHoraria = data.cargaHoraria();
        this.status = data.status();
        this.horaCriacao = data.horaCriacao();
    }
}