package ru.kpfu.itis.application.client.exceptions;

public class GameClientException extends Exception{
    public GameClientException() {
        super();
    }

    public GameClientException(String message) {
        super(message);
    }

    public GameClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameClientException(Throwable cause) {
        super(cause);
    }

    protected GameClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
