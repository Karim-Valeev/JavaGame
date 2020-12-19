package ru.kpfu.itis.application.client.abstracts;

import ru.kpfu.itis.application.client.exceptions.GameClientException;
import ru.kpfu.itis.protocol.Message;


public interface Client {

    void connect() throws GameClientException;
    void disconnect();
    void sendMessage(Message message) throws GameClientException;
    void registerListener(ClientEventListener listener) throws GameClientException;
}
