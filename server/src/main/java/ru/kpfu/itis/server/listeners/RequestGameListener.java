package ru.kpfu.itis.server.listeners;

import ru.kpfu.itis.models.Game;
import ru.kpfu.itis.models.Player;
import ru.kpfu.itis.network.InitGamePackage;
import ru.kpfu.itis.protocol.Message;
import ru.kpfu.itis.server.Connection;
import ru.kpfu.itis.server.GameServer;
import ru.kpfu.itis.server.abstracts.AbstractServerEventListener;
import ru.kpfu.itis.server.abstracts.Server;
import ru.kpfu.itis.server.exceptions.GameServerException;
import ru.kpfu.itis.server.exceptions.ServerEventListenerException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
        try {
            if (message.getType() == Message.TYPE_REQ_GAME) {
                if (games.isEmpty()) {
                    games.add(new Game());
                }
                List<Player> players = games.get(0).getPlayers();
                if (players.isEmpty()) {
                    Player player1 = new Player(
                            0,
                            0,
                            connection
                    );
                    players.add(player1);
                    server.sendMessage(connection, Message.createMessage(Message.TYPE_WAIT_SECOND_PLAYER, new byte[]{0}));
                } else {
                    if (players.size() == 2) {
                        server.sendMessage(connection, Message.createMessage(Message.TYPE_GAME_REJECTED, new byte[]{0}));
                        return;
                    }
                    Player player2 = new Player(
                            9,
                            9,
                            connection
                    );
                    players.add(player2);
                    for (Player player : players){
                        byte[] bytes = buildInitGamePackage(players, player.getConnection());
                        server.sendMessage(player.getConnection(), Message.createMessage(Message.TYPE_GAME_STARTING, bytes));
                    }
                }
            }
        } catch (GameServerException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private byte[] buildInitGamePackage(List<Player> players, Connection connection) throws GameServerException {
        Player player;
        Player enemy;
        if (players.get(0).getConnection().equals(connection)){
            player = players.get(0);
            enemy = players.get(1);
        } else {
            player = players.get(1);
            enemy = players.get(0);
        }
        InitGamePackage gamePackage = new InitGamePackage(
                player.getX(),
                player.getY(),
                enemy.getX(),
                enemy.getY(),
                player.getHero(),
                enemy.getHero()
        );
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(gamePackage);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new GameServerException(e);
        }
    }
}
