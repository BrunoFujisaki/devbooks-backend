package com.BrunoFujisaki.devbooks_backend.repository;

import com.BrunoFujisaki.devbooks_backend.model.PedidoItem;
import com.BrunoFujisaki.devbooks_backend.model.PedidoItemID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, PedidoItemID> {
}
