package com.bank.kata.exception;

public class OperationException extends Exception{

    public static final String AMOUNT_MUST_NOT_BE_NEGATIVE = "Le montant doit etre positif";
    public static final String  SPENDING_LIMIT_REACHED = "vous avez atteint le plafond de retrait autorisé";

    public OperationException(String message) {
        super(message);
    }
}
