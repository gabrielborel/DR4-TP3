package al.infnet.edu.br.DR4_TP3.projections;

import al.infnet.edu.br.DR4_TP3.entities.ItemPedidoEntity;
import al.infnet.edu.br.DR4_TP3.entities.PedidoEntity;
import al.infnet.edu.br.DR4_TP3.events.PedidoCriadoEvent;
import al.infnet.edu.br.DR4_TP3.repositories.PedidoRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class PedidoProjection {

    private final PedidoRepository pedidoRepository;

    public PedidoProjection(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @EventHandler
    public void on(PedidoCriadoEvent event) {
        PedidoEntity pedido = new PedidoEntity(
                event.getAggregateId(),
                event.getClienteId(),
                event.getLojaId(),
                event.getValorTotal(),
                event.getEnderecoEntrega(),
                event.getFormaPagamento(),
                "CRIADO"
        );

        for (var item : event.getItens()) {
            ItemPedidoEntity itemEntity = new ItemPedidoEntity(
                    item.produtoId(),
                    item.nomeProduto(),
                    item.quantidade(),
                    item.precoUnitario()
            );
            pedido.addItem(itemEntity);
        }

        pedidoRepository.save(pedido);
    }
}
