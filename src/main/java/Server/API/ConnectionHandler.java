package Server.API;

import java.io.Closeable;

/**
 * Created by 1omer on 25/03/2017.
 */
public interface ConnectionHandler<T> extends Closeable
{
    void send(T msg) ;
}
