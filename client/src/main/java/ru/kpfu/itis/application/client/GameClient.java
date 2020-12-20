package ru.kpfu.itis.application.client;

import ru.kpfu.itis.application.client.abstracts.Client;
import ru.kpfu.itis.application.client.abstracts.ClientEventListener;
import ru.kpfu.itis.application.client.exceptions.GameClientException;
import ru.kpfu.itis.application.models.GameField;
import ru.kpfu.itis.protocol.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameClient implements Client {
    protected final InetAddress address;
    protected final int port;
    protected Socket socket;
    protected List<ClientEventListener> listeners;
    protected boolean connected;
    protected MessageHandler messageHandler;
    protected GameField gameField;

    public GameClient(InetAddress address, int port, GameField gameField) {
        this.gameField = gameField;
        this.address = address;
        this.port = port;
        listeners = new ArrayList<>();
        connected = false;
    }

    @Override
    public void connect() throws GameClientException {
        try {
            socket = new Socket(address, port);
            messageHandler = new MessageHandler(this);
            Thread messageHandlerThread = new Thread(messageHandler);
            messageHandlerThread.start();
            connected = true;
        }catch (IOException e){
            throw new GameClientException("Can't connect", e);
        }
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void sendMessage(Message message) throws GameClientException {
        if (!connected){
            throw new GameClientException("Not connected");
        }
        try {
            socket.getOutputStream().write(Message.getBytes(message));
            socket.getOutputStream().flush();
        } catch (IOException e){
            throw new GameClientException("Can't send message",e);
        }
    }

    @Override
    public void registerListener(ClientEventListener listener) throws GameClientException {
        if (connected){
            throw new GameClientException("Connected");
        }
        listener.init();
        Thread listenerThread = new Thread(listener);
        listenerThread.start();
        listeners.add(listener);
    }

    public GameField getGameField() {
        return gameField;
    }
}
