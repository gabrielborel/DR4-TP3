package al.infnet.edu.br.DR4_TP3.services;

import al.infnet.edu.br.DR4_TP3.commands.CriarPedidoCommand;
import al.infnet.edu.br.DR4_TP3.commands.CriarPedidoCommand.ItemPedidoCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PedidoCommandService {
    private final CommandGateway commandGateway;
    private final AtomicLong pedidoIdGenerator = new AtomicLong(1);

    public PedidoCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<Long> criarPedido(Long clienteId, Long lojaId,
            List<ItemPedidoCommand> itens, String enderecoEntrega, String formaPagamento) {

        Long pedidoId = pedidoIdGenerator.getAndIncrement();

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
