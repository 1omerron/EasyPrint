package Server.srv.NetworkImplementation;

import Client.API.MessageEncoderDecoder;
import Server.API.Packets.Packet;

/**
 * Created by 1omer on 27/03/2017.
 */
public class ServerEncoderDecoder implements MessageEncoderDecoder<Packet>
{

    /**
     * add the next byte to the decoding process
     *
     * @param nextByte the next byte to consider for the currently decoded
     *                 message
     * @return a message if this byte completes one or null if it doesnt.
     */
    @Override
    public Packet decodeNextByte(byte nextByte) {
        return null;
    }

    /**
     * encodes the given message to bytes array
     *
     * @param message the message to encode
     * @return the encoded bytes
     */
    @Override
    public byte[] encode(Packet message) {
        return new byte[0];
    }
}
