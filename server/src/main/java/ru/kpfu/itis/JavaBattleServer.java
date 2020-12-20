package ru.kpfu.itis;

import ru.kpfu.itis.server.GameServer;
import ru.kpfu.itis.server.exceptions.GameServerException;
import ru.kpfu.itis.server.listeners.RequestGameListener;

public class JavaBattleServer extends GameServer {
    private static int PORT = 11903;

    public static void main(String[] args) {
        JavaBattleServer server = new JavaBattleServer(PORT);
        try {
            server.registerListener(new RequestGameListener());

            server.start();
        } catch (GameServerException e) {
            throw new IllegalStateException(e);
        }
    }

    public JavaBattleServer(int port) {
        super(port);
    }
}
