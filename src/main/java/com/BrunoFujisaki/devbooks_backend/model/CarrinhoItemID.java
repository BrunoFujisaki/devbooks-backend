package com.BrunoFujisaki.devbooks_backend.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"livroId", "carrinhoId"})
public class CarrinhoItemID implements Serializable {
    private UUID livroId;
    private UUID carrinhoId;
}
