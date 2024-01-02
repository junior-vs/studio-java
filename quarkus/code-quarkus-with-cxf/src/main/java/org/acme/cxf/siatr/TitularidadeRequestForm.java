package org.acme.cxf.siatr;

import com.fasterxml.jackson.annotation.JsonCreator;

public record TitularidadeRequestForm(int icTitularidade, int tipoPessoa, long nuCpfCnpj, long nuConta) {


    @JsonCreator
    public TitularidadeRequestForm(int icTitularidade, int tipoPessoa, long nuCpfCnpj, long nuConta) {
        this.icTitularidade = icTitularidade;
        this.tipoPessoa = tipoPessoa;
        this.nuCpfCnpj = nuCpfCnpj;
        this.nuConta = nuConta;
    }
}
