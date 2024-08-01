package com.studio.java.streams;

import com.studio.java.data.TransactionData;
import com.studio.java.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class TransactionProcessorTest {

    TransactionProcessor transactionProcessor;
    private Logger logger  = Logger.getLogger(TransactionProcessorTest.class.getName());

    @BeforeEach
    void setUp() {
        transactionProcessor = new TransactionProcessor();
    }


    @Test
    void findByYearAndSortByValue() {

        List<Transaction> data = TransactionData.data();

        List<Transaction> result = transactionProcessor
                .findByYearAndSortByValue(data, 2011);

        logger.info(result.toString());

        assertEquals(4, result.size());

    }
}