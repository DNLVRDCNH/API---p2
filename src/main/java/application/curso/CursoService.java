package application.curso;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepo;

    public Iterable<CursoDTO> getAll() {
        return cursoRepo.findAll().stream().map(CursoDTO::new).toList();
    }

    public CursoDTO insert(CursoInsertDTO data) {
        return new CursoDTO(cursoRepo.save(new Curso(data)));
    }

    public CursoDTO getOne(@PathVariable long id) {
        Optional<Curso> result = cursoRepo.findById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        return new CursoDTO(result.get());
    }

    public CursoDTO update(long id, CursoInsertDTO data) {
        Optional<Curso> result = cursoRepo.findById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        Curso currentCurso = result.get();

        if (data.nome() != null) {
            currentCurso.setNome(data.nome());
        }
        if (data.descricao() != null) {
            currentCurso.setDescricao(data.descricao());
        }
        if (data.cargaHoraria() != null) {
            currentCurso.setCargaHoraria(data.cargaHoraria());
        }
        if (data.status() != null) {
            currentCurso.setStatus(data.status());
        }
        if (data.horaCriacao() != null) {
            currentCurso.setHoraCriacao(data.horaCriacao());
        }

        return new CursoDTO(cursoRepo.save(currentCurso));
    }

    public void delete(long id) {
        if (!cursoRepo.existsById(id)){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrado"
            );
        }

        cursoRepo.deleteById(id);
    }
}