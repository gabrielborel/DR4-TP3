package al.infnet.edu.br.DR4_TP3.events;

import java.math.BigDecimal;
import java.util.List;

public class PedidoCriadoEvent extends DomainEvent {
    private final Long clienteId;
    private final Long lojaId;
    private final List<ItemPedido> itens;
    private final BigDecimal valorTotal;
    private final String enderecoEntrega;
    private final String formaPagamento;

    public PedidoCriadoEvent(Long pedidoId, Long clienteId, Long lojaId,
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

    public record ItemPedido(Long produtoId, String nomeProduto, int quantidade, BigDecimal precoUnitario) {
    }

    public record PedidoCriadoData(Long clienteId, Long lojaId, List<ItemPedido> itens,
            BigDecimal valorTotal, String enderecoEntrega, String formaPagamento) {
    }
}
