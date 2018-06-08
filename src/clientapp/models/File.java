package clientapp.models;

public class File {
    private String name;
    private String file;
    private String contentType;
    private String fullPath;

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", file='" + file + '\'' +
                ", contentType='" + contentType + '\'' +
                ", fullPath='" + fullPath + '\'' +
                '}';
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
