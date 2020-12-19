package ru.kpfu.itis.server.listeners;

import ru.kpfu.itis.models.Game;
import ru.kpfu.itis.protocol.Message;
import ru.kpfu.itis.server.Connection;
import ru.kpfu.itis.server.GameServer;
import ru.kpfu.itis.server.abstracts.AbstractServerEventListener;
import ru.kpfu.itis.server.abstracts.Server;
import ru.kpfu.itis.server.exceptions.ServerEventListenerException;

import java.util.List;

public class RequestGameListener extends AbstractServerEventListener {

    private List<Game> games;

    @Override
    public void init(GameServer server) {
        super.init(server);
        games = server.getGames();
        types.add(Message.TYPE_REQ_GAME);
    }

    @Override
    public void handle(Connection connection, Message message) throws ServerEventListenerException {
        if (message.getType() == Message.TYPE_REQ_GAME){
            if (games.isEmpty()){
                games.add(new Game());
            }

        }
    }
}
