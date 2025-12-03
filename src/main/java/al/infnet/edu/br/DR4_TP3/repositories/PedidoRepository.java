package al.infnet.edu.br.DR4_TP3.repositories;

import al.infnet.edu.br.DR4_TP3.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
