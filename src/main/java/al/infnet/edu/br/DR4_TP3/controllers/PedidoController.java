package al.infnet.edu.br.DR4_TP3.controllers;

import al.infnet.edu.br.DR4_TP3.commands.CriarPedidoCommand.ItemPedidoCommand;
import al.infnet.edu.br.DR4_TP3.services.PedidoCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoCommandService pedidoCommandService;

    public PedidoController(PedidoCommandService pedidoCommandService) {
        this.pedidoCommandService = pedidoCommandService;
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
