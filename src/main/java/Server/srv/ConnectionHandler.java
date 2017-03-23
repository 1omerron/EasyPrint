/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.srv;

import Server.api.bidi.BidiMessagingProtocol;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @author bennyl
 */
public interface ConnectionHandler<T> extends Closeable{

    void send(T msg) throws IOException;
    void setId(int id);
    int getId();
    BidiMessagingProtocol getProtocol();

}