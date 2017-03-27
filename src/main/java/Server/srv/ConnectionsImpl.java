/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.srv;

import Server.api.bidi.Connections;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionsImpl<T> implements Connections<T>{


    private AtomicInteger nextConnectionId= new AtomicInteger(0);
    private  ConcurrentHashMap<Integer,ConnectionHandler> connectedClients = new ConcurrentHashMap<>();

    public void add(ConnectionHandler handler){
        connectedClients.put(nextConnectionId.get(),handler);
        handler.setId(nextConnectionId.get());
        nextConnectionId.incrementAndGet();

    }

    @Override
    public boolean send(int connectionId, T msg) throws IOException {
        if(connectedClients.containsKey(connectionId)){
            connectedClients.get(connectionId).send(msg);
            return true;
        }
        return false;
    }

   

    public void sendAll(Collection<ConnectionHandler> handlers,T msg) throws IOException {
        for (ConnectionHandler handler:handlers) {
             handler.send(msg);

        }
    }

    @Override
    public void disconnect(int connectionId) {
        connectedClients.remove(connectionId);

    }

    public ConcurrentHashMap<Integer, ConnectionHandler> getConnectedClients() {
        return connectedClients;
    }
}