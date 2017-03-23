package Tries;

import java.util.UUID;

/**
 * Created by 1omer on 23/03/2017.
 */
public class PrimaryKeyGenerator
{
    public static void main(String[] args)
    {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        System.out.println(uuid);
    }
}
