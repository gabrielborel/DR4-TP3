package al.infnet.edu.br.DR4_TP3.services;

import al.infnet.edu.br.DR4_TP3.entities.PedidoEntity;
import al.infnet.edu.br.DR4_TP3.events.DomainEvent;
import al.infnet.edu.br.DR4_TP3.repositories.PedidoRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoQueryService {

    private final EventStore eventStore;
    private final PedidoRepository pedidoRepository;

    public PedidoQueryService(EventStore eventStore, PedidoRepository pedidoRepository) {
        this.eventStore = eventStore;
        this.pedidoRepository = pedidoRepository;
    }

    public List<? extends DomainEvent> buscarEventosPorPedidoId(Long pedidoId) {
        return eventStore.readEvents(pedidoId.toString())
                .asStream()
                .map(event -> (DomainEvent) event.getPayload())
                .toList();
    }

    public Optional<PedidoEntity> buscarPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }
}
