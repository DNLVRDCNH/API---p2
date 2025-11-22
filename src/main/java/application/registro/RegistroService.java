package application.registro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.curso.Curso;
import application.curso.CursoRepository;
import application.aluno.Aluno;
import application.aluno.AlunoRepository;

@Service
public class RegistroService {
    @Autowired
    private RegistroRepository registroRepo;
    @Autowired
    private AlunoRepository alunoRepo;
    @Autowired
    private CursoRepository cursoRepo;

    public Iterable<RegistroDTO> getAll() {
        return registroRepo.findAll().stream().map(RegistroDTO::new).toList();
    }

    public RegistroDTO insert(RegistroInsertDTO data) {
        Optional<Aluno> aluResult = alunoRepo.findById(data.alunoId());
        Optional<Curso> couResult = cursoRepo.findById(data.cursoId());

        String errorMessage = "Data not found for: ";
        boolean hasError = false;

        if (aluResult.isEmpty()) {
            errorMessage += "alunoId=" + data.alunoId() + " ";
            hasError = true;
        }
        if (couResult.isEmpty()) {
            errorMessage += "cursoId=" + data.cursoId() + " ";
            hasError = true;
        }
        if (hasError) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, errorMessage
            );
        }

        Registro novoRegistro = new Registro();
        novoRegistro.setDataRegistro(data.dataRegistro());
        novoRegistro.setStatus(data.status());
        novoRegistro.setAluno(aluResult.get());
        novoRegistro.setCurso(couResult.get());

        return new RegistroDTO(registroRepo.save(novoRegistro));
    }

    public RegistroDTO getOne(long id) {
        return registroRepo.findById(id).map(RegistroDTO::new).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Registro não encontrado para id=" + id
        ));
    }

    public RegistroDTO update(long id, RegistroInsertDTO data) {
        Optional<Registro> regResult = registroRepo.findById(id);
        if (regResult.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Registro não encontrado para id=" + id
            );
        }
        Optional<Aluno> aluResult = alunoRepo.findById(data.alunoId());
        Optional<Curso> couResult = cursoRepo.findById(data.cursoId());

        String errorMessage = "Data not found for: ";
        boolean hasError = false;

        if (aluResult.isEmpty()) {
            errorMessage += "alunoId=" + data.alunoId() + " ";
            hasError = true;
        }
        if (couResult.isEmpty()) {
            errorMessage += "cursoId=" + data.cursoId() + " ";
            hasError = true;
        }
        if (hasError) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, errorMessage
            );
        }

        Registro registro = regResult.get();
        if (data.dataRegistro() != null) {
            registro.setDataRegistro(data.dataRegistro());
        }
        if (data.status() != null) {
            registro.setStatus(data.status());
        }
        registro.setAluno(aluResult.get());
        registro.setCurso(couResult.get());

        return new RegistroDTO(registroRepo.save(registro));
    }

    public void delete(long id) {
        if (!registroRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Registro não encontrado para id=" + id
            );
        }
        registroRepo.deleteById(id);
    }
}