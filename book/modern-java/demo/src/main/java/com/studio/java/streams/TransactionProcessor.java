package com.studio.java.streams;

import com.studio.java.domain.Transaction;

import java.util.Comparator;
import java.util.List;

public class TransactionProcessor {


    List<Transaction> findByYearAndSortByValue(List<Transaction> transactions, int year) {
        return transactions
                .stream()
                .filter(transaction -> transaction.year() > year)
                .sorted(Comparator.comparing(Transaction::value))
                .toList();
    }
}
