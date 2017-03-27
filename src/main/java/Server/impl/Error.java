package Server.impl;

/**
 * Created by Mika on 09/01/2017.
 */
public class Error extends Packet {
    private short errorCode;
    private String errMsg;
    //private short endOfStrigName = 0;
    public Error(short code, String msg) {
        super((short)5, "ERROR");
        errorCode = code;
        errMsg = msg;
    }

    public short getErrorCode() {
        return errorCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
