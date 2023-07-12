package com.danrocha.dialogframework.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "ordens_servicos")
public class OrdemServico implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate dataEntrada;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @NotBlank
    @Column(nullable = false,  columnDefinition = "text")
    private String descricao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdemServico that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "OrdemServico: " +
                "id: "+ id +
                ", cliente=" + cliente +
                ", dataEntrada=" + dataEntrada +
                ", preco=" + preco +
                ", descricao='" + descricao;
    }
}
