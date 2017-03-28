package Client.API;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by 1omer on 25/03/2017.
 */
public interface ConnectionHandler<T> extends Closeable
{
    void send(T msg) ;
}
