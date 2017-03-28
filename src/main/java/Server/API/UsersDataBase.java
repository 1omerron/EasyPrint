package Server.API;

/**
 * Created by 1omer on 28/03/2017.
 */
public interface UsersDataBase<T>
{
    /**
     * adds the username and its password to the database
     * @param userName username
     * @param password password
     * @return true if the username was successfuly added, or false if the username already exists
     */
    boolean add(String userName, String password);

    /**
     * removes an existing username from the database
     * @param userName the username to remove
     * @return true if the user existed and removed, or false if the username wasn't exist in the first place
     */
    boolean remove(String userName);
}
