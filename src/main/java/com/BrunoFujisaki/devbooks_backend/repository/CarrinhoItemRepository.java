package com.BrunoFujisaki.devbooks_backend.repository;

import com.BrunoFujisaki.devbooks_backend.model.CarrinhoItem;
import com.BrunoFujisaki.devbooks_backend.model.CarrinhoItemID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, CarrinhoItemID> {
}
