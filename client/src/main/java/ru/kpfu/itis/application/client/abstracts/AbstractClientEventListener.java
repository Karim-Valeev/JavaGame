package ru.kpfu.itis.application.client.abstracts;

import ru.kpfu.itis.application.client.exceptions.ClientEventListenerException;
import ru.kpfu.itis.protocol.Message;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractClientEventListener implements ClientEventListener{

    protected Queue<Message> queue;
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
    public void submit(Message message) {
        queue.offer(message);
    }

    @Override
    public void run() {
        while (true){
            Thread.yield();
            if (!queue.isEmpty()){
                try{
                    Message message = queue.poll();
                    handle(message);
                } catch (ClientEventListenerException e){
                    throw new IllegalStateException(e);
                }
            }
        }
    }
}
