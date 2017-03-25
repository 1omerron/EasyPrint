package Client.API.Packets;

/**
 * Created by 1omer on 26/03/2017.
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
