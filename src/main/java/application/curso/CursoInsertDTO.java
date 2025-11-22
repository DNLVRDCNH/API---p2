package application.curso;

import java.time.LocalDateTime;

public record CursoInsertDTO(String nome, String descricao, Integer cargaHoraria, String status, LocalDateTime horaCriacao) {
    public CursoInsertDTO(Curso curso) {
        this(curso.getNome(), 
             curso.getDescricao(), 
             curso.getCargaHoraria(), 
             curso.getStatus(), 
             curso.getHoraCriacao());
    }
}