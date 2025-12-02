package al.infnet.edu.br.DR4_TP3.events;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PedidoCriadoEvent extends DomainEvent {
    private final UUID clienteId;
    private final UUID lojaId;
    private final List<ItemPedido> itens;
    private final BigDecimal valorTotal;
    private final String enderecoEntrega;
    private final String formaPagamento;

    public PedidoCriadoEvent(UUID pedidoId, UUID clienteId, UUID lojaId,
            List<ItemPedido> itens, BigDecimal valorTotal,
            String enderecoEntrega, String formaPagamento, long version) {
        super(pedidoId, "Pedido", version);
        this.clienteId = clienteId;
        this.lojaId = lojaId;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.enderecoEntrega = enderecoEntrega;
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String getEventType() {
        return "PedidoCriado";
    }

    @Override
    public Object getEventData() {
        return new PedidoCriadoData(clienteId, lojaId, itens, valorTotal, enderecoEntrega, formaPagamento);
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public UUID getLojaId() {
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

    public record ItemPedido(UUID produtoId, String nomeProduto, int quantidade, BigDecimal precoUnitario) {
    }

    public record PedidoCriadoData(UUID clienteId, UUID lojaId, List<ItemPedido> itens,
            BigDecimal valorTotal, String enderecoEntrega, String formaPagamento) {
    }
}
