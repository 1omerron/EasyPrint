package RequestOrganization;

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
    private String fileType;

    /**
     * Constructs FileInfo from an uploaded file
     */
    public FileInfo()
    {
    }
}
