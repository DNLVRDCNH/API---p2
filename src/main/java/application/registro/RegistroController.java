package application.registro;

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
@RequestMapping("/registros")
public class RegistroController {
    @Autowired
    private RegistroService registroService;

    @PostMapping
    @Operation(summary = "Cria um novo registro",
               description = "Cria um novo registro com as informações fornecidas da requisição.")
    @ApiResponse(responseCode = "200", description = "Registro criado")     
    public RegistroDTO insert(@RequestBody RegistroInsertDTO data) {
        return registroService.insert(data);
    }

    @GetMapping
    @Operation(summary = "Lista todos os registros",
               description = "Retorna uma lista de todos os registros no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de registros retornada")
    public Iterable<RegistroDTO> getAll() {
        return registroService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um registro por ID",
               description = "Retorna um registro específico pelo ID fornecido.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Registro encontrado"),
        @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public RegistroDTO getOne(@Parameter(description = "ID do registro") @PathVariable long id) {
        return registroService.getOne(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um registro",
               description = "Atualiza as informações de um registro pleo ID fornecido e nos dados fornecidos no corpo da requisição.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Registro atualizado"),
        @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public RegistroDTO update(@Parameter(description = "ID do registro a ser atualizado") @PathVariable long id, @RequestBody RegistroInsertDTO data) {
        return registroService.update(id, data);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um registro",
               description = "Remove um registro específico com base no ID fornecido.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Registro removido"),
        @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    public void delete(@Parameter(description = "ID do registro a ser removido") @PathVariable long id) {
        registroService.delete(id);
    }
}