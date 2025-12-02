package al.infnet.edu.br.DR4_TP3.controllers;

import al.infnet.edu.br.DR4_TP3.commands.CriarPedidoCommand.ItemPedidoCommand;
import al.infnet.edu.br.DR4_TP3.entities.PedidoEntity;
import al.infnet.edu.br.DR4_TP3.events.DomainEvent;
import al.infnet.edu.br.DR4_TP3.services.PedidoCommandService;
import al.infnet.edu.br.DR4_TP3.services.PedidoQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoCommandService pedidoCommandService;
    private final PedidoQueryService pedidoQueryService;

    public PedidoController(PedidoCommandService pedidoCommandService, PedidoQueryService pedidoQueryService) {
        this.pedidoCommandService = pedidoCommandService;
        this.pedidoQueryService = pedidoQueryService;
    }

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

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoEntity> buscarPorId(@PathVariable UUID pedidoId) {
        return pedidoQueryService.buscarPorId(pedidoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{pedidoId}/eventos")
    public ResponseEntity<List<? extends DomainEvent>> buscarEventos(@PathVariable UUID pedidoId) {
        List<? extends DomainEvent> eventos = pedidoQueryService.buscarEventosPorPedidoId(pedidoId);
        return ResponseEntity.ok(eventos);
    }

    public record CriarPedidoRequest(
            UUID clienteId,
            UUID lojaId,
            List<ItemPedidoCommand> itens,
            String enderecoEntrega,
            String formaPagamento) {
    }

    public record CriarPedidoResponse(UUID pedidoId, String mensagem) {
    }
}
