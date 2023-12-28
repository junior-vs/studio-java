
package com.example.openbankingdemo.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.openbankingdemo.model.CurrencyCode;
import com.example.openbankingdemo.model.Transaction;


@Service
public class TransactionService {

    /**
     * @param accountNumber
     * @return
     */
    public List<Transaction> findAllByAccountNumber(String accountNumber){

        List<Transaction> listTransaction = Arrays.asList({
            new Transaction(TypeTransactionEnum.CREDIT, LocalDateTime.now(), "001-0", CurrencyCode.BRL, BigDecimal.TEN, "Casa de pao", "merchantLogo")
        })

        return null;
        
    }

}