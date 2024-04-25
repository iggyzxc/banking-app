package com.iggyzxc.bankingapp.exception;

// My custom exceptions parent class
public class AccountException extends RuntimeException{

    public AccountException(String message) {
        super(message);
    }

    // Id does not exist exception
    public static class IdNotFoundException extends AccountException {
        public IdNotFoundException(String message) {
            super(message);
        }
    }

    // Insufficient balance exception
    public static class InsufficientBalanceException extends AccountException {
        public InsufficientBalanceException(String message) {
            super(message);
        }
    }
}
