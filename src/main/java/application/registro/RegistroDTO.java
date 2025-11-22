package application.registro;

import java.time.LocalDate;

import application.curso.CursoDTO;
import application.aluno.AlunoDTO;

public record RegistroDTO(long id, LocalDate dataRegistro, String status, AlunoDTO aluno, CursoDTO curso) {
    public RegistroDTO(Registro registro) {
        this(registro.getId(),
        registro.getDataRegistro(),
        registro.getStatus(),
        new AlunoDTO(registro.getAluno()),
        new CursoDTO(registro.getCurso()));
    }
}