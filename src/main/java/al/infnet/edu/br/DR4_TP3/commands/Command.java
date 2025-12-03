package al.infnet.edu.br.DR4_TP3.commands;

import java.time.Instant;
import java.util.UUID;

public abstract class Command {
    private final UUID commandId;
    private final Long aggregateId;
    private final String aggregateType;
    private final Instant issuedAt;
    private String correlationId;
    private String causationId;
    private String issuedBy;

    protected Command(Long aggregateId, String aggregateType) {
        this.commandId = UUID.randomUUID();
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.issuedAt = Instant.now();
    }

    public abstract String getCommandType();

    public abstract Object getCommandData();

    public UUID getCommandId() {
        return commandId;
    }

    public Long getAggregateId() {
        return aggregateId;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public Instant getIssuedAt() {
        return issuedAt;
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

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    @Override
    public String toString() {
        return String.format(
                "%s[commandId=%s, aggregateId=%s, aggregateType=%s, issuedAt=%s, issuedBy=%s]",
                getCommandType(),
                commandId,
                aggregateId,
                aggregateType,
                issuedAt,
                issuedBy);
    }
}
