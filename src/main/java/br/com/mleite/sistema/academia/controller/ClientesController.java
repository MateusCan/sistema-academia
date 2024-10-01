package br.com.mleite.sistema.academia.controller;

import br.com.mleite.sistema.academia.domain.entity.Clientes;
import br.com.mleite.sistema.academia.domain.repository.ClientesRepository;
import br.com.mleite.sistema.academia.dto.ClientesDTO;
import br.com.mleite.sistema.academia.exception.RegraNegocioException;
import br.com.mleite.sistema.academia.service.ClientesServiceImpl;
import br.com.mleite.sistema.academia.validation.OnCreate;
import br.com.mleite.sistema.academia.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@Validated
public class ClientesController {

    private ClientesRepository clientes;
    private ClientesServiceImpl clientesService;

    public ClientesController(ClientesRepository clientes, ClientesServiceImpl clientesService) {
        this.clientes = clientes;
        this.clientesService = clientesService;}

    @GetMapping("busca-cliente-por-id/{id}")
    @Operation(summary = "Obter detalhes de um cliente", description = "Retorna as informações de um cliente específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")})
    public Clientes getClienteById(@PathVariable @Parameter(description = "id do cliente") Long id) {
        return clientes.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente com ID: "+id+" Não encontrado"));
    }

    @GetMapping("busca-cliente-por-nome/{nome}")
    @Operation(summary = "Obter detalhes de um cliente", description = "Retorna as informações de um cliente específico pelo NOME.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")})
    public List<Clientes> getClienteByNome(@PathVariable @Parameter(description = "id do cliente") String nome) {

        return Optional.ofNullable(clientes.findByNomeContaining(nome))
                .filter(clientesEncontrados -> !clientesEncontrados.isEmpty()) // Verifica se a lista não está vazia
                .orElseThrow(() -> new RegraNegocioException("Cliente com nome: " + nome + " não encontrado"));
    }

    @PostMapping("inclui-cliente")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um cliente", description = "Cria um cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")})
    public void criarCliente(@RequestBody @Validated({OnCreate.class, Default.class}) Clientes cliente) {
        clientesService.criarCliente(cliente);
    }

    @DeleteMapping("deleta-cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um cliente", description = "Deleta um cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")})
    public void deletarCliente(@PathVariable Long id) {
        clientes.findById(id).map(cliente -> {
            clientes.delete(cliente);
            return cliente;
        }).orElseThrow(() -> new RegraNegocioException("Cliente com ID: "+id+" Não encontrado"));
    }

    @PutMapping("atualiza-cliente")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualiza um cliente", description = "Atualiza um cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")})
    public void atualizarCliente(@RequestBody @Validated({OnUpdate.class, Default.class}) ClientesDTO cliente) {
        Clientes clienteExistente = clientes.findById(cliente.getIdCliente())
                .orElseThrow(() -> new RegraNegocioException("Cliente com ID: " + cliente.getIdCliente() + " não encontrado"));

        BeanUtils.copyProperties(cliente, clienteExistente, "codigo");
        clientesService.atualizarCliente(clienteExistente);
    }
}
