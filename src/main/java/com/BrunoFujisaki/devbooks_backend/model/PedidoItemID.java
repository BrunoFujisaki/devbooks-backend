package com.BrunoFujisaki.devbooks_backend.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"pedidoId", "livroId"})
public class PedidoItemID implements Serializable {
    private UUID pedidoId;
    private UUID livroId;
}
