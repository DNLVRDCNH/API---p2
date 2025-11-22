package application.aluno;

import java.time.LocalDate;

public record AlunoDTO(long id, String nome, String email, String telefone, LocalDate dataRegistro) {
    public AlunoDTO(Aluno aluno) {
        this(aluno.getId(), 
             aluno.getNome(), 
             aluno.getEmail(), 
             aluno.getTelefone(), 
             aluno.getDataRegistro());
    }
}