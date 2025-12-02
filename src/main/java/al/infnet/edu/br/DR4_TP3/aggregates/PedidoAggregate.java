package al.infnet.edu.br.DR4_TP3.aggregates;

import al.infnet.edu.br.DR4_TP3.commands.CriarPedidoCommand;
import al.infnet.edu.br.DR4_TP3.events.PedidoCriadoEvent;
import al.infnet.edu.br.DR4_TP3.events.PedidoCriadoEvent.ItemPedido;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Aggregate
public class PedidoAggregate {
    @AggregateIdentifier
    private UUID pedidoId;

    protected PedidoAggregate() {
    }

    @CommandHandler
    public PedidoAggregate(CriarPedidoCommand command) {
        List<ItemPedido> itensEvento = command.getItens().stream()
                .map(item -> new ItemPedido(
                        item.produtoId(),
                        item.nomeProduto(),
                        item.quantidade(),
                        item.precoUnitario()))
                .toList();

        BigDecimal valorTotal = command.getItens().stream()
                .map(item -> item.precoUnitario().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        AggregateLifecycle.apply(new PedidoCriadoEvent(
                command.getAggregateId(),
                command.getClienteId(),
                command.getLojaId(),
                itensEvento,
                valorTotal,
                command.getEnderecoEntrega(),
                command.getFormaPagamento(),
                1L));
    }
}
