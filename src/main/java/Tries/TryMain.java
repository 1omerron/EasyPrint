package Tries;

/**
 * Created by 1omer on 29/03/2017.
 */
public class TryMain
{
    public static void main(String[] args)
    {
        int num = 5;
        String intValue = String.valueOf(num)+'\0';
        byte[] numInBytes = intValue.getBytes();
        String numberIsString = new String(numInBytes);
        int number = Integer.parseInt(numberIsString);
        System.out.println(number);
    }
}
