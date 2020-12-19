package ru.kpfu.itis.application.client;

import ru.kpfu.itis.application.client.abstracts.Client;
import ru.kpfu.itis.application.client.abstracts.ClientEventListener;
import ru.kpfu.itis.protocol.Message;

import java.io.IOException;
import java.net.Socket;

public class MessageHandler implements Runnable{

    protected GameClient client;

    public MessageHandler(GameClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message message = Message.readMessage(client.socket.getInputStream());
                for (ClientEventListener listener : client.listeners){
                    if (listener.getTypes().contains(message.getType())){
                        listener.submit(message);
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
