package ru.kpfu.itis.application.client.abstracts;

import ru.kpfu.itis.application.client.exceptions.ClientEventListenerException;
import ru.kpfu.itis.application.models.GameField;
import ru.kpfu.itis.protocol.Message;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractClientEventListener implements ClientEventListener{

    protected Queue<Pair<Message, GameField>> queue;
    protected List<Integer> types;
    protected boolean init;

    @Override
    public void init() {
        this.init = true;
        this.queue = new LinkedList<>();
        this.types = new ArrayList<>();
        //add types
    }

    @Override
    public List<Integer> getTypes() {
        return types;
    }

    @Override
    public void submit(Message message, GameField gameField) {
        queue.offer(new Pair<>(message, gameField));
    }

    @Override
    public void run() {
        while (true){
            Thread.yield();
            if (!queue.isEmpty()){
                try{
                    Pair<Message, GameField> pair = queue.poll();
                    Message message = pair.getFirst();
                    GameField gameField = pair.getSecond();
                    handle(message, gameField);
                } catch (ClientEventListenerException e){
                    throw new IllegalStateException(e);
                }
            }
        }
    }
}
