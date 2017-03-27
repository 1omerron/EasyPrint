package Server.api.bidi;

/**
 * Created by Mika on 08/01/2017.
 */

import bgu.spl171.net.srv.ConnectionHandler;

import java.io.IOException;
import java.util.Collection;

public interface Connections<T> {

    boolean send(int connectionId, T msg) throws IOException;

    void sendAll(Collection<ConnectionHandler> handlers, T msg) throws IOException;
    void disconnect(int connectionId);
}