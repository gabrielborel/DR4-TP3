package al.infnet.edu.br.DR4_TP3.repositories;

import al.infnet.edu.br.DR4_TP3.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, UUID> {
}
