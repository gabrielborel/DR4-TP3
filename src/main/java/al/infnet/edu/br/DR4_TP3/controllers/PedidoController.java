package al.infnet.edu.br.DR4_TP3.controllers;

import al.infnet.edu.br.DR4_TP3.commands.CriarPedidoCommand.ItemPedidoCommand;
import al.infnet.edu.br.DR4_TP3.entities.PedidoEntity;
import al.infnet.edu.br.DR4_TP3.events.DomainEvent;
import al.infnet.edu.br.DR4_TP3.services.PedidoCommandService;
import al.infnet.edu.br.DR4_TP3.services.PedidoQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "API para gerenciamento de pedidos do PetFriends")
public class PedidoController {

    private final PedidoCommandService pedidoCommandService;
    private final PedidoQueryService pedidoQueryService;

    public PedidoController(PedidoCommandService pedidoCommandService, PedidoQueryService pedidoQueryService) {
        this.pedidoCommandService = pedidoCommandService;
        this.pedidoQueryService = pedidoQueryService;
    }

    @Operation(summary = "Criar um novo pedido", description = "Cria um novo pedido no sistema utilizando Event Sourcing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso",
                    content = @Content(schema = @Schema(implementation = CriarPedidoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public CompletableFuture<ResponseEntity<CriarPedidoResponse>> criarPedido(@RequestBody CriarPedidoRequest request) {
        return pedidoCommandService.criarPedido(
                request.clienteId(),
                request.lojaId(),
                request.itens(),
                request.enderecoEntrega(),
                request.formaPagamento()
        ).thenApply(pedidoId -> ResponseEntity.ok(new CriarPedidoResponse(pedidoId, "Pedido criado com sucesso")));
    }

    @Operation(summary = "Buscar pedido por ID", description = "Retorna os detalhes de um pedido específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                    content = @Content(schema = @Schema(implementation = PedidoEntity.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoEntity> buscarPorId(
            @Parameter(description = "ID do pedido", example = "1") @PathVariable Long pedidoId) {
        return pedidoQueryService.buscarPorId(pedidoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar eventos do pedido", description = "Retorna todos os eventos de domínio associados a um pedido (Event Sourcing)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de eventos do pedido")
    })
    @GetMapping("/{pedidoId}/eventos")
    public ResponseEntity<List<? extends DomainEvent>> buscarEventos(
            @Parameter(description = "ID do pedido", example = "1") @PathVariable Long pedidoId) {
        List<? extends DomainEvent> eventos = pedidoQueryService.buscarEventosPorPedidoId(pedidoId);
        return ResponseEntity.ok(eventos);
    }

    @Schema(description = "Dados para criação de um novo pedido")
    public record CriarPedidoRequest(
            @Schema(description = "ID do cliente", example = "1")
            Long clienteId,
            @Schema(description = "ID da loja", example = "1")
            Long lojaId,
            @Schema(description = "Lista de itens do pedido")
            List<ItemPedidoCommand> itens,
            @Schema(description = "Endereço de entrega", example = "Rua das Flores, 123 - Centro")
            String enderecoEntrega,
            @Schema(description = "Forma de pagamento", example = "CARTAO_CREDITO")
            String formaPagamento) {
    }

    @Schema(description = "Resposta da criação de pedido")
    public record CriarPedidoResponse(
            @Schema(description = "ID do pedido criado", example = "1")
            Long pedidoId,
            @Schema(description = "Mensagem de confirmação", example = "Pedido criado com sucesso")
            String mensagem) {
    }
}
