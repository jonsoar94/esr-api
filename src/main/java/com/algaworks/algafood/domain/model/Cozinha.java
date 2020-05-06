package com.algaworks.algafood.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

// @JsonRootName(value = "cozinha") // Serve apenas para o padrão XML. Isso quer dizer, que na representação, a raiz do 
//XML será chamada cozinha.
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cozinha")
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // @JsonIgnore -- Ignora a propriedade na representação do recurso.
    // @JsonProperty("titulo") // Altera o nome da propriedade na representação do recurso.
    @Column(nullable = false)
    private String nome;
    
}