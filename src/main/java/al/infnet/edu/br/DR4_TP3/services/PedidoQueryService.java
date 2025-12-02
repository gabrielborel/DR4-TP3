package al.infnet.edu.br.DR4_TP3.services;

import al.infnet.edu.br.DR4_TP3.events.DomainEvent;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PedidoQueryService {

    private final EventStore eventStore;

    public PedidoQueryService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public List<? extends DomainEvent> buscarEventosPorPedidoId(UUID pedidoId) {
        return eventStore.readEvents(pedidoId.toString())
                .asStream()
                .map(event -> (DomainEvent) event.getPayload())
                .toList();
    }
}
