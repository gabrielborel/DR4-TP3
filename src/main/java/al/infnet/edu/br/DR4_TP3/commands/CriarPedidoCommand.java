package al.infnet.edu.br.DR4_TP3.commands;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CriarPedidoCommand extends Command {
    private final UUID clienteId;
    private final UUID lojaId;
    private final List<ItemPedidoCommand> itens;
    private final String enderecoEntrega;
    private final String formaPagamento;

    public CriarPedidoCommand(UUID pedidoId, UUID clienteId, UUID lojaId,
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

    public UUID getClienteId() {
        return clienteId;
    }

    public UUID getLojaId() {
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

    public record ItemPedidoCommand(UUID produtoId, String nomeProduto, int quantidade, BigDecimal precoUnitario) {
    }

    public record CriarPedidoData(UUID clienteId, UUID lojaId, List<ItemPedidoCommand> itens,
            String enderecoEntrega, String formaPagamento) {
    }
}
