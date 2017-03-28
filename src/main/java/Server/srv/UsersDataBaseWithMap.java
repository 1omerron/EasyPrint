package Server.srv;

import Server.API.UsersDataBase;

import java.util.HashMap;

/**
 * Created by 1omer on 28/03/2017.
 * 
 * Handles all the server's users and their passwords in one database
 */
public class UsersDataBaseWithMap implements UsersDataBase
{
    // for JSON
    public HashMap<String, String> getUsers()
    {
        return users;
    }

    private HashMap<String, String> users = new HashMap<>();

    /**
     * adds the username and its password to the database
     * @param userName username
     * @param password password
     * @return true if the username was successfuly added, or false if the username already exists
     */
    @Override
    public boolean add(String userName, String password)
    {
        if(users.containsKey(userName))
            return false;
        else
        {
            users.put(userName, password);
            return true;
        }
    }

    /**
     * removes an existing username from the database
     * @param userName the username to remove
     * @return true if the user existed and removed, or false if the username wasn't exist in the first place
     */
    @Override
    public boolean remove(String userName)
    {
        if(users.containsKey(userName)) {
            users.remove(userName);
            return true;
        }
        else
            return false;
    }
}
