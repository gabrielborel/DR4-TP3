package al.infnet.edu.br.DR4_TP3.events;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {
    private final UUID eventId;
    private final UUID aggregateId;
    private final String aggregateType;
    private final Instant occurredOn;
    private final long version;
    private String correlationId;
    private String causationId;

    protected DomainEvent(UUID aggregateId, String aggregateType, long version) {
        this.eventId = UUID.randomUUID();
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.occurredOn = Instant.now();
        this.version = version;
    }

    public abstract String getEventType();

    public abstract Object getEventData();

    public UUID getEventId() {
        return eventId;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }

    public long getVersion() {
        return version;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getCausationId() {
        return causationId;
    }

    public void setCausationId(String causationId) {
        this.causationId = causationId;
    }

    @Override
    public String toString() {
        return String.format(
            "%s[eventId=%s, aggregateId=%s, aggregateType=%s, occurredOn=%s, version=%d]",
            getEventType(),
            eventId,
            aggregateId,
            aggregateType,
            occurredOn,
            version
        );
    }
}
