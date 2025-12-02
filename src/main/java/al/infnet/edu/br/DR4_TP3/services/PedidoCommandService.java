package al.infnet.edu.br.DR4_TP3.services;

import al.infnet.edu.br.DR4_TP3.commands.CriarPedidoCommand;
import al.infnet.edu.br.DR4_TP3.commands.CriarPedidoCommand.ItemPedidoCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PedidoCommandService {
    private final CommandGateway commandGateway;

    public PedidoCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<UUID> criarPedido(UUID clienteId, UUID lojaId,
            List<ItemPedidoCommand> itens, String enderecoEntrega, String formaPagamento) {

        UUID pedidoId = UUID.randomUUID();

        CriarPedidoCommand command = new CriarPedidoCommand(
                pedidoId,
                clienteId,
                lojaId,
                itens,
                enderecoEntrega,
                formaPagamento);

        return commandGateway.send(command);
    }
}
