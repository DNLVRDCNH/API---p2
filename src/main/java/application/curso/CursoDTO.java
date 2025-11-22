package application.curso;

import java.time.LocalDateTime;

public record CursoDTO(long id, String nome, String descricao, Integer cargaHoraria, String status, LocalDateTime horaCriacao) {
    public CursoDTO(Curso data) {
        this(data.getId(), 
             data.getNome(), 
             data.getDescricao(), 
             data.getCargaHoraria(), 
             data.getStatus(), 
             data.getHoraCriacao());
    }
}