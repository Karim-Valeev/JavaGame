package ru.kpfu.itis.application.client.listeners;

import ru.kpfu.itis.application.client.abstracts.AbstractClientEventListener;
import ru.kpfu.itis.application.client.exceptions.ClientEventListenerException;
import ru.kpfu.itis.application.models.GameField;
import ru.kpfu.itis.application.models.Hero;
import ru.kpfu.itis.network.InitGamePackage;
import ru.kpfu.itis.protocol.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class WaitGameListener extends AbstractClientEventListener {

    @Override
    public void init() {
        super.init();
        types.add(Message.TYPE_GAME_STARTING);
    }

    @Override
    public void handle(Message message, GameField gameField) throws ClientEventListenerException {
        if (message.getType() == Message.TYPE_GAME_STARTING){
            InitGamePackage gamePackage = getInitGamePackage(message.getData());
            gameField.createNewField(
                    gamePackage.getxPlayer(),
                    gamePackage.getyPlayer(),
                    gamePackage.getxEnemy(),
                    gamePackage.getyEnemy(),
                    new Hero(gamePackage.getPlayerHero().getHp(), gamePackage.getPlayerHero().getAttack()),
                    new Hero(gamePackage.getEnemyHero().getHp(), gamePackage.getEnemyHero().getAttack())
            );
            gameField.setVisible(true);
        }
    }

    private InitGamePackage getInitGamePackage(byte [] bytes) throws ClientEventListenerException {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (InitGamePackage) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ClientEventListenerException(e);
        }
    }
}
