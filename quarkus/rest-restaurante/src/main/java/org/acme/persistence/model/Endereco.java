package org.acme.persistence.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Embeddable
public class Endereco {

    @NotBlank
    @Size(max = 9)
    private String cep;
    @NotBlank
    @Size(max = 300)
    private String endereco;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String pais;

    private String complemento;

    private String bairro;

    /**
     * @Deprecated Necessário para o Hibernate
     */
    @Deprecated
    public Endereco() {
    }

    public Endereco(String cep, String endereco, String cidade, String estado, String pais, String complemento, String bairro) {
        this.cep = cep;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.complemento = complemento;
        this.bairro = bairro;
    }


    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }
}