package Client.API.Packets;

/**
 * Created by 1omer on 26/03/2017.
 *
 * Code : l (el, not ai)
 *
 * Operation Field in LogInOutPacket represents:
 * Log In - the char '1'
 * Log Out - the char '0'
 *
 * byte[] data = the bytes representing the User Name string
 */
public class LogInOutPacket extends Packet
{
    private String userName; // 1-login, 0-logout

    public LogInOutPacket(char code, char operation, String userName)
    {
        super(code,operation,userName.getBytes());
        this.userName = userName;
    }
}
