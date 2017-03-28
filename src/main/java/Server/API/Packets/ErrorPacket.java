package Server.API.Packets;

/**
 * Created by 1omer on 26/03/2017.
 *
 * Code : e
 *
 * Operation Field in ErrorPacket represents the Error Code:
 * '0' - illegal packet received (could not decode packet / illegal op-code / illegal operation-code / etc.)
 * '1' - username already exists (for login request) / username does not exists (for logout request)
 * '2' -
 * '3' -
 * .
 * .
 * .
 *
 * byte[] data = the bytes representing the Error Message
 */
public class ErrorPacket extends Packet
{
    private String errorMessage;

    public ErrorPacket(char code, char operation, String errorMessage)
    {
        super(code,operation,errorMessage.getBytes());
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {return errorMessage;}
}
