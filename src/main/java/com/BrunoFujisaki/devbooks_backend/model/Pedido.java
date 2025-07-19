package com.BrunoFujisaki.devbooks_backend.model;

import com.BrunoFujisaki.devbooks_backend.dto.usuarios.EnderecoDTO;
import com.BrunoFujisaki.devbooks_backend.model.enums.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {
    @Id
    @UuidGenerator
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    @Setter
    private StatusPedido status;
    private LocalDateTime data;
    @Embedded
    @NotNull
    private Endereco endereco;
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @OneToMany(
            mappedBy = "pedido",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<PedidoItem> itens = new ArrayList<>();

    public Pedido(Usuario usuario, StatusPedido status, LocalDateTime data, EnderecoDTO endereco) {
        this.usuario = usuario;
        this.status = status;
        this.data = data;
        this.endereco = new Endereco(endereco);
    }

    public void calcularValorTotal() {
        this.valorTotal = BigDecimal.ZERO;
        this.itens.forEach(item -> {
            this.valorTotal = this.valorTotal
                    .add(item.getValor()
                            .multiply(BigDecimal.valueOf(item.getQuantidade())));
        });
    }
}
