package com.example.openbankingdemo.model;

public enum TypeTransactionEnum {

    CREDIT(1), DEBIT(2);

    private Integer typeCode;

    private TypeTransactionEnum(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

}
