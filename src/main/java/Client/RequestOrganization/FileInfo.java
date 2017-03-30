package Client.RequestOrganization;

import java.io.File;

/**
 * Created by 1omer on 23/03/2017.
 */
public class FileInfo
{
    private File file;
    private String fileName;

    public File getFile() {
        return file;
    }


    public String getFileId() {
        return fileId;
    }

    private String fileId;
    private int numberOfPages;

    /**
     * Constructs FileInfo from an uploaded file
     */
    //todo remove after testing json
    public FileInfo()
    {
        file = new File("\"C:\\\\Users\\\\חן\\\\IdeaProjects\\\\EasyPrint\\\\test.txt\"");
        numberOfPages = 1;
        this.fileName = file.getName();
    }

    public FileInfo(String path)
    {
        file = new File(path);
        numberOfPages = 20;//todo check
        this.fileName = file.getName();
    }

    /**
     * @return the number of pages in this file
     */
    public int getNumberOfPages()
    {
        return this.numberOfPages;
    }

    public void setFile(File file) {
        this.file = file;
        this.fileName = file.getName();
    }

    public String getFileName() {
        return fileName;
    }
}
