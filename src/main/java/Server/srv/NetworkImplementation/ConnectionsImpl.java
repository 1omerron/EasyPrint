package Server.srv.NetworkImplementation;

import Server.API.ConnectionHandler;
import Server.API.Connections;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 1omer on 09/01/2017.
 */
public class ConnectionsImpl<T> implements Connections<T>
{
    private ConcurrentHashMap<Integer,ConnectionHandler<T>> currentConnections;

    public ConnectionsImpl()
    {
        currentConnections = new ConcurrentHashMap<>();
    }

    public void add(int connectionId, ConnectionHandler<T> myHandler)
    {
        currentConnections.put(connectionId,myHandler);
    }

    public boolean send(int connectionId, T msg)
    {
        ConnectionHandler<T> handler = currentConnections.get(connectionId);
        if (handler != null)
        {
            synchronized (currentConnections.get(connectionId)) {
                try {
                    handler.send(msg);
                } catch (Exception e) {
                    return false;
                }
            }
        } 
        return true;
    }

    public void broadcast(T msg)
    {
        Collection<ConnectionHandler<T>> collection = currentConnections.values();
        for(ConnectionHandler<T> handler : collection)
        {
            handler.send(msg);
        }
    }

    public void disconnect(int connectionId)
    {
        Integer temp = connectionId;
        synchronized (currentConnections.get(temp))
        {
            currentConnections.remove(temp);
        }
    }
}
