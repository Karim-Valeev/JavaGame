package ru.kpfu.itis.application.client.exceptions;

public class ClientEventListenerException extends Exception{
    public ClientEventListenerException() {
        super();
    }

    public ClientEventListenerException(String message) {
        super(message);
    }

    public ClientEventListenerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientEventListenerException(Throwable cause) {
        super(cause);
    }

    protected ClientEventListenerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
