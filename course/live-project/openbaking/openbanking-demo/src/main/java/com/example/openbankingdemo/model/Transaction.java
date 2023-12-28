package com.example.openbankingdemo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private final UUID id;
    private final TypeTransactionEnum type;
    private final LocalDateTime date;
    private final String accountNumber;
    private final CurrencyCode  currency;
    private final BigDecimal amount;
    private final String merchantName;
    private final String merchantLogo;

    public Transaction(TypeTransactionEnum type, LocalDateTime date, String accountNumber, CurrencyCode  currency,
            BigDecimal amount, String merchantName, String merchantLogo) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.date = date;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.amount = amount;
        this.merchantName = merchantName;
        this.merchantLogo = merchantLogo;
    }

    
    public TypeTransactionEnum getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public CurrencyCode  getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantLogo() {
        return merchantLogo;
    }

    @Override
    public String toString() {
        return "Transaction [accountNumber=" + accountNumber + ", amount=" + amount + ", currency=" + currency
                + ", date=" + date + ", merchantLogo=" + merchantLogo + ", merchantName=" + merchantName + ", type="
                + type + "]";
    }


    public UUID getId() {
        return id;
    }

}
