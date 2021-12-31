package user.lib;

public class File {

    private Long id;
    private String fileName;
    private String filePath;
    private String fileType;
    private Byte[] fileContent;

    // getters
    public Long getId() {
        return this.id;
    }
    public String getName() { return this.fileName; }
    public String getPath() {
        return this.filePath;
    }
    public String getType() {
        return this.fileType;
    }
    public Byte[] getContent() {
        return this.fileContent;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String fileName) {
        this.fileName = fileName;
    }
    public void setPath(String filePath) {
        this.filePath = filePath;
    }
    public void setType(String fileType) {
        this.fileType = fileType;
    }
    public void setContent(Byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
