package org.sid.ebankingbackend.exceptionns;

public class BalanceNotSufficientException extends Throwable {
    public BalanceNotSufficientException(String message) {
        super(message);
    }
}
