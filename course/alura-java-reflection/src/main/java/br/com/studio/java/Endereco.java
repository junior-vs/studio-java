package br.com.studio.java;

public class Endereco {
    private String rua;
    private Integer numero;

    public Endereco(String rua, Integer numero) {
        this.rua = rua;
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public Integer getNumero() {
        return numero;
    }
}
