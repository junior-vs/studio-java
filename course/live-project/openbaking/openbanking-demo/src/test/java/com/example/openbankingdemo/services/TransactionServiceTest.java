package com.example.openbankingdemo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.openbankingdemo.model.Transaction;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @BeforeAll
    public void init(){
        transactionService = new TransactionService();
    }
    

    /**
     * 
     */
    @Test
    public void findAllByAccountNumberTest(){

        List<Transaction> result = transactionService.findAllByAccountNumber("001-0");

        assertEquals(3, result.size());
    
    }
}
