package me.paulojr.cadastro.infra.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import me.paulojr.cadastro.domain.shared.search.Pagination;
import me.paulojr.cadastro.infra.api.impl.ExceptionController;
import me.paulojr.cadastro.infra.context.user.models.UserRequestBody;
import me.paulojr.cadastro.infra.context.user.models.UserResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/user")
public interface UserAPI {

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            operationId = "createUser",
            summary = "Cria um usuário",
            description = "Cria um usuário novo no sistema a partir dos dados informados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(schema = @Schema(implementation = ExceptionController.CustomErrorResponse.class))),
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = UserRequestBody.class))),
            method = "POST"

    )
    ResponseEntity<UserResponse> createUser(@RequestBody UserRequestBody body);

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            operationId = "updateUser",
            summary = "Atualiza um usuário",
            description = "Atualiza um usuário no sistema a partir dos dados informados",
            parameters = {
                    @Parameter(name = "id", description = "ID do usuário a ser atualizado", in = ParameterIn.PATH, example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(schema = @Schema(implementation = ExceptionController.CustomErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ExceptionController.CustomErrorResponse.class))),
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = UserRequestBody.class))),
            method = "PUT"

    )
    ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequestBody body);


    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            operationId = "findUser",
            summary = "Busca um usuário",
            description = "Busca um usuário no sistema a partir dos dados informados",
            parameters = {
                    @Parameter(name = "id", description = "ID do usuário a ser buscado", in = ParameterIn.PATH, example = "10"),
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário retornado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(schema = @Schema(implementation = ExceptionController.CustomErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = ExceptionController.CustomErrorResponse.class))),
            },
            method = "GET"

    )
    ResponseEntity<UserResponse> findUser(@PathVariable Long id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "search",
            summary = "Pesquisa usuários",
            description = "Pesquisa usuários no sistema a partir dos dados informados",
            parameters = {
                    @Parameter(name = "name", description = "Nome para ser filtrado", in = ParameterIn.QUERY, example = "João"),
                    @Parameter(name = "email", description = "Email para filtrar resultados", in = ParameterIn.QUERY, example = "joao@email.com"),
                    @Parameter(name = "page", description = "Página a ser requisitada", in = ParameterIn.QUERY, example = "0")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuários retornados"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(schema = @Schema(implementation = ExceptionController.CustomErrorResponse.class))),
            },
            method = "GET"

    )
    ResponseEntity<Pagination<UserResponse>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false, defaultValue = "0") Integer page
    );
}
