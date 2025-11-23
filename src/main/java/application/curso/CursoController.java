package application.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Operation(summary = "Cria um novo curso",
               description = "Cria um novo curso com as informações fornecidas.")
    @ApiResponse(responseCode = "200", description = "Curso criado com sucesso")
    public CursoDTO insert(@RequestBody CursoInsertDTO novoCurso) {
        return cursoService.insert(novoCurso);
    }

    @GetMapping
    @Operation(summary = "Lista todos os cursos disponíveis",
               description = "Retorna uma lista de todos os cursos disponíveis no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso")
    public Iterable<CursoDTO> getAll() {
        return cursoService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um curso por ID",
               description = "Retorna um curso específico pelo ID dado.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso encontrado e retornado"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoDTO getOne(@Parameter(description = "Procurando ID do curso") @PathVariable long id) {
        return cursoService.getOne(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um curso",
               description = "Atualiza as informações de um curso pelo ID e dados fornecidos no corpo da requisição.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso atualizado "),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoDTO update(@Parameter(description = "ID do Curso a ser atualizado") @PathVariable long id, @RequestBody CursoInsertDTO data) {
        return cursoService.update(id, data);
    }

    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID do Curso para remover") @PathVariable long id) {
        cursoService.delete(id);
    }
}