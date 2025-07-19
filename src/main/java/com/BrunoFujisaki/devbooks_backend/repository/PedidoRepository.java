package com.BrunoFujisaki.devbooks_backend.repository;

import com.BrunoFujisaki.devbooks_backend.model.Pedido;
import com.BrunoFujisaki.devbooks_backend.model.enums.StatusPedido;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findAllByUsuarioId(UUID usuarioId);

    @Query("""
        SELECT SUM(p.valorTotal) FROM Pedido p
        WHERE p.status = :status"""
        )
    BigDecimal totalDeVendas(@Param("status")StatusPedido status);


}
