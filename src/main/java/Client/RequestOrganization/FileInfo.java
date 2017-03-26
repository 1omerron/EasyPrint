package Client.RequestOrganization;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by 1omer on 23/03/2017.
 */
public class FileInfo
{
    private File file;
    private Path filePath;
    private String userId;
    private String fileId;
    private int numberOfPages;

    /**
     * Constructs FileInfo from an uploaded file
     */
    //todo remove after testing json
    public FileInfo()
    {
        file = null;
        filePath = null;
        userId = "123";
        fileId = "json test";
        numberOfPages = 20;
    }

    /**
     * @return the number of pages in this file
     */
    public int getNumberOfPages()
    {
        return this.numberOfPages;
    }
}
