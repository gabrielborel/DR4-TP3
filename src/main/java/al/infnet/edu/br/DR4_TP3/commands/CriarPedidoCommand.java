package al.infnet.edu.br.DR4_TP3.commands;

import java.math.BigDecimal;
import java.util.List;

public class CriarPedidoCommand extends Command {
    private final Long clienteId;
    private final Long lojaId;
    private final List<ItemPedidoCommand> itens;
    private final String enderecoEntrega;
    private final String formaPagamento;

    public CriarPedidoCommand(Long pedidoId, Long clienteId, Long lojaId,
            List<ItemPedidoCommand> itens, String enderecoEntrega, String formaPagamento) {
        super(pedidoId, "Pedido");
        this.clienteId = clienteId;
        this.lojaId = lojaId;
        this.itens = itens;
        this.enderecoEntrega = enderecoEntrega;
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String getCommandType() {
        return "CriarPedido";
    }

    @Override
    public Object getCommandData() {
        return new CriarPedidoData(clienteId, lojaId, itens, enderecoEntrega, formaPagamento);
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getLojaId() {
        return lojaId;
    }

    public List<ItemPedidoCommand> getItens() {
        return itens;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public record ItemPedidoCommand(Long produtoId, String nomeProduto, int quantidade, BigDecimal precoUnitario) {
    }

    public record CriarPedidoData(Long clienteId, Long lojaId, List<ItemPedidoCommand> itens,
            String enderecoEntrega, String formaPagamento) {
    }
}
