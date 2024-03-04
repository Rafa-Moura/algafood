package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("hibernateLazyInitializer")
    @JoinColumn(nullable = false, name = "cozinha_id")
    private Cozinha cozinha;

    @Embedded
    @JsonIgnore
    private Endereco endereco;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false)
    @JsonIgnore
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false)
    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id", referencedColumnName = "id"))
    private List<FormaPagamento> formaPagamentos;

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;
}
