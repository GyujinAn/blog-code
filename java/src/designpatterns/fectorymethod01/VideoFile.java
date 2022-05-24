package designpatterns.fectorymethod01;

public class VideoFile extends ContentFile{
    private final String CONTENT_TYPE = "video";

    public VideoFile(String basePath, String userId, String fileName, String data) {
        super(basePath, userId, fileName, data);
    }
    @Override
    String getPath() {
        return getBasePath() + CONTENT_TYPE + "/" + fileName;
    }


}
