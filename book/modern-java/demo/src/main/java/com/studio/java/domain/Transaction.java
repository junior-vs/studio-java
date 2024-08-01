package com.studio.java.domain;

public record Transaction(Trader trader,
                          int year,
                          int value) {
}
