package al.infnet.edu.br.DR4_TP3.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID clienteId;

    @Column(nullable = false)
    private UUID lojaId;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoEntity> itens = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private String enderecoEntrega;

    @Column(nullable = false)
    private String formaPagamento;

    @Column(nullable = false)
    private String status;

    public PedidoEntity() {
    }

    public PedidoEntity(UUID id, UUID clienteId, UUID lojaId,
            BigDecimal valorTotal, String enderecoEntrega, String formaPagamento, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.lojaId = lojaId;
        this.valorTotal = valorTotal;
        this.enderecoEntrega = enderecoEntrega;
        this.formaPagamento = formaPagamento;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public UUID getLojaId() {
        return lojaId;
    }

    public void setLojaId(UUID lojaId) {
        this.lojaId = lojaId;
    }

    public List<ItemPedidoEntity> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoEntity> itens) {
        this.itens = itens;
    }

    public void addItem(ItemPedidoEntity item) {
        itens.add(item);
        item.setPedido(this);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
