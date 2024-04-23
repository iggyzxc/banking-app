package com.iggyzxc.bankingapp.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp,
                           String message,
                           String errorDescription,
                           String errorCode) {
}
