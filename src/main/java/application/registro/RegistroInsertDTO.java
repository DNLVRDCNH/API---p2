package application.registro;

import java.time.LocalDate;

public record RegistroInsertDTO(LocalDate dataRegistro, String status, long alunoId, long cursoId) {
    public RegistroInsertDTO(Registro registro) {
        this(registro.getDataRegistro(),
        registro.getStatus(),
        registro.getAluno().getId(),
        registro.getCurso().getId());
    }
    
    // Construtor sem dataRegistro para permitir requisições sem esse campo
    public RegistroInsertDTO(String status, long alunoId, long cursoId) {
        this(null, status, alunoId, cursoId);
    }
}