package application.aluno;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public Iterable<AlunoDTO> getAll() {
        return alunoRepository.findAll().stream().map(AlunoDTO::new).toList();
    }

    public AlunoDTO insert(AlunoInsertDTO aluno) {
        return new AlunoDTO(alunoRepository.save(new Aluno(aluno)));
    }

    public AlunoDTO getOne(long id) {
        Optional<Aluno> result = alunoRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }

        return new AlunoDTO(result.get());
    }

    public AlunoDTO update(long id, AlunoInsertDTO aluno) {
        Optional<Aluno> result = alunoRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }

        Aluno currentAluno = result.get();

        if (aluno.nome() != null) {
            currentAluno.setNome(aluno.nome());
        }
        if (aluno.email() != null) {
            currentAluno.setEmail(aluno.email());
        }
        if (aluno.telefone() != null) {
            currentAluno.setTelefone(aluno.telefone());
        }
        if (aluno.dataRegistro() != null) {
            currentAluno.setDataRegistro(aluno.dataRegistro());
        }

        return new AlunoDTO(alunoRepository.save(currentAluno));
    }

    public void delete(long id) {
        Optional<Aluno> result = alunoRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }

        alunoRepository.deleteById(id);
    }
}