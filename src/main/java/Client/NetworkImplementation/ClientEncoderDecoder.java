package Client.NetworkImplementation;

import Client.API.MessageEncoderDecoder;

/**
 * Created by 1omer on 25/03/2017.
 */
public class ClientEncoderDecoder implements MessageEncoderDecoder
{
    /**
     * add the next byte to the decoding process
     *
     * @param nextByte the next byte to consider for the currently decoded
     *                 message
     * @return a message if this byte completes one or null if it doesnt.
     */
    @Override
    public Object decodeNextByte(byte nextByte) {
        return null;
    }

    /**
     * encodes the given message to bytes array
     *
     * @param message the message to encode
     * @return the encoded bytes
     */
    @Override
    public byte[] encode(Object message) {
        return new byte[0];
    }
}
