package com.BrunoFujisaki.devbooks_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "carrinho_itens")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "carrinhoItemID")
public class CarrinhoItem {
    @EmbeddedId
    private CarrinhoItemID carrinhoItemID;
    @ManyToOne
    @MapsId("livroId")
    @JoinColumn(name = "livro_id")
    private Livro livro;
    @ManyToOne
    @MapsId("carrinhoId")
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;
    private BigDecimal valor;
    @Setter
    private Integer quantidade;

}
