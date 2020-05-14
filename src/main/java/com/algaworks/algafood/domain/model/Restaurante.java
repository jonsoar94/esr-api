package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    //@JsonIgnore
    // @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false, foreignKey = @ForeignKey(name="fk_restaurante_cidade"))
    private Cozinha cozinha;
    
    // @JsonIgnore
    @ManyToMany //(fetch = FetchType.EAGER)
    @JoinTable(name = "restaurante_forma_pagamento", 
        joinColumns = @JoinColumn(name = "restaurante_id"), 
        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaDePagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore 
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();
}