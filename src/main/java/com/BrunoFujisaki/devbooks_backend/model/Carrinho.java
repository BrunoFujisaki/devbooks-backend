package com.BrunoFujisaki.devbooks_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.context.annotation.Lazy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carrinhos")
@Getter
public class Carrinho {
    @Id
    @UuidGenerator
    private UUID id;
    @OneToOne
    @Setter
    private Usuario usuario;
    private BigDecimal valorTotal;
    @OneToMany(
            mappedBy = "carrinho",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<CarrinhoItem> itens = new ArrayList<>();

    public void calcularValorTotal() {
        this.valorTotal = BigDecimal.ZERO;
        this.itens.forEach(item -> {
            this.valorTotal = this.valorTotal
                    .add(item.getValor()
                            .multiply(BigDecimal.valueOf(item.getQuantidade())));
        });
    }
}
