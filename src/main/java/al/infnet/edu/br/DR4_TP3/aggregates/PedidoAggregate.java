package al.infnet.edu.br.DR4_TP3.aggregates;

import al.infnet.edu.br.DR4_TP3.commands.CriarPedidoCommand;
import al.infnet.edu.br.DR4_TP3.events.PedidoCriadoEvent;
import al.infnet.edu.br.DR4_TP3.events.PedidoCriadoEvent.ItemPedido;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Aggregate
public class PedidoAggregate {
    @AggregateIdentifier
    private Long pedidoId;
    private Long clienteId;
    private Long lojaId;
    private List<ItemPedido> itens;
    private BigDecimal valorTotal;
    private String enderecoEntrega;
    private String formaPagamento;
    private String status;

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

    @EventSourcingHandler
    public void on(PedidoCriadoEvent event) {
        this.pedidoId = event.getAggregateId();
        this.clienteId = event.getClienteId();
        this.lojaId = event.getLojaId();
        this.itens = new ArrayList<>(event.getItens());
        this.valorTotal = event.getValorTotal();
        this.enderecoEntrega = event.getEnderecoEntrega();
        this.formaPagamento = event.getFormaPagamento();
        this.status = "CRIADO";
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getLojaId() {
        return lojaId;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public String getStatus() {
        return status;
    }
}
