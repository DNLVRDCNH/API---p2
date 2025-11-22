package application.registro;

import java.time.LocalDate;

public record RegistroInsertDTO(LocalDate dataRegistro, String status, long alunoId, long cursoId) {
    public RegistroInsertDTO(Registro registro) {
        this(registro.getDataRegistro(),
        registro.getStatus(),
        registro.getAluno().getId(),
        registro.getCurso().getId());
    }
}