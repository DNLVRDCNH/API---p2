package application.aluno;

import java.time.LocalDate;

public record AlunoInsertDTO(String nome, String email, String telefone, LocalDate dataRegistro) {
    public AlunoInsertDTO(Aluno aluno) {
        this(aluno.getNome(), 
             aluno.getEmail(), 
             aluno.getTelefone(), 
             aluno.getDataRegistro());
    }
    
    // Construtor sem dataRegistro para permitir requisições sem esse campo
    public AlunoInsertDTO(String nome, String email, String telefone) {
        this(nome, email, telefone, null);
    }
}