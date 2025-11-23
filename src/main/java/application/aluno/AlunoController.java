package application.aluno;

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
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @Operation(summary = "Cria um novo aluno",
               description = "Cria um novo aluno com as informações fornecidas ")
    @ApiResponse(responseCode = "200", description = "Aluno criado com sucesso")
    public AlunoDTO insert(@RequestBody AlunoInsertDTO aluno) {
        return alunoService.insert(aluno);
    }

    @GetMapping
    @Operation(summary = "Lista todos os alunos",
               description = "Retorna uma lista de todos os alunos no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso")
    public Iterable<AlunoDTO> getAll() {
        return alunoService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um aluno por ID",
               description = "Retorna os detalhes de um aluno específico pelo ID fornecido.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aluno encontrado e retornado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public AlunoDTO getOne(@Parameter(description = "procura ID do aluno") @PathVariable long id) {
        return alunoService.getOne(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um aluno",
               description = "Atualiza as informações de um aluno pelo ID fornecido e nos dados fornecidos no corpo da requisição.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aluno atualizado"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public AlunoDTO update(@Parameter(description = "ID do aluno a ser atualizado.") @PathVariable long id, @RequestBody AlunoInsertDTO aluno) {
        return alunoService.update(id, aluno);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um aluno",
               description = "Remove um aluno pelo ID fornecido.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aluno removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public void delete(@Parameter(description = "ID do aluno a ser removido.") @PathVariable long id) {
        alunoService.delete(id);
    }
}