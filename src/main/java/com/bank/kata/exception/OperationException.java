package com.bank.kata.exception;

public class OperationException extends Exception{

    public static final String AMOUNT_MUST_NOT_BE_NEGATIVE = "Le montant doit etre positif";

    public OperationException(String message) {
        super(message);
    }
}
