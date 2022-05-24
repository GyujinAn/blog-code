package designpatterns.fectorymethod01;

public abstract class ContentFile {
    protected final String basePath;
    protected final String userId;

    protected final String fileName;

    protected final String data;

    public ContentFile(String basePath, String userId, String fileName, String data) {
        this.basePath = basePath;
        this.userId = userId;
        this.fileName = fileName;
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    abstract String getPath();


    protected String getBasePath(){
        return basePath + "/" + userId + "/";
    }
}
