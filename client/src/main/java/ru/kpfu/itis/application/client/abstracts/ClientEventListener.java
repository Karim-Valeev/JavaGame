package ru.kpfu.itis.application.client.abstracts;

import ru.kpfu.itis.application.client.exceptions.ClientEventListenerException;
import ru.kpfu.itis.application.models.GameField;
import ru.kpfu.itis.protocol.Message;

import java.util.List;

public interface ClientEventListener extends Runnable{

    void init();
    void handle(Message message, GameField gameField) throws ClientEventListenerException;
    List<Integer> getTypes();

    void submit(Message message, GameField gameField);
}
