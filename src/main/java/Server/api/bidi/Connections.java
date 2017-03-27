package Server.api.bidi;

import Server.srv.ConnectionHandler;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Mika on 08/01/2017.
 */


public interface Connections<T> {

    boolean send(int connectionId, T msg) throws IOException;

    void sendAll(Collection<ConnectionHandler> handlers, T msg) throws IOException;
    void disconnect(int connectionId);
}