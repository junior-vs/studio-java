package org.acme.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "restaurante_id_seq")
    @SequenceGenerator(
            name = "restaurante_id_seq",
            sequenceName = "restaurante_id_seq",
            allocationSize = 1,
            initialValue = 1)
    private Long id;

    @NotBlank
    @Size(max = 18)
    private String cnpj;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @Size(max = 1000)
    private String descricao;

    @Valid
    @Embedded
    private Endereco endereco;

    /**
     * @deprecated hibernate eyes only
     */
    @Deprecated
    public Restaurante() { //empty constructor
    }

    public Restaurante(String cnpj, String nome, String descricao, Endereco endereco) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void atualiza(Restaurante updatedRestaurante) {
        this.cnpj = updatedRestaurante.cnpj;
        this.nome = updatedRestaurante.nome;
        this.descricao = updatedRestaurante.descricao;
        this.endereco = updatedRestaurante.endereco;

    }
}
