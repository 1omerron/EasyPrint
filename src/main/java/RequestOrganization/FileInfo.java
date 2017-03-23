package RequestOrganization;

import API.FileType;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by 1omer on 23/03/2017.
 */
public class FileInfo implements FileType
{
    private File file;
    private Path filePath;
    private String userId;
    private String fileId;
    private String fileType;

    /**
     * Construcs FileInfo from an uploaded file
     */
    public FileInfo()
    {
    }

    /**
     * @return the type of the document
     */
    @Override
    public String getFileType()
    {
        return fileType;
    }
}
