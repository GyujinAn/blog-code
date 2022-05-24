package designpatterns.fectorymethod01;

public class MusicFile extends ContentFile{
    private final String CONTENT_TYPE = "music";

    public MusicFile(String basePath, String userId, String fileName, String data) {
        super(basePath, userId, fileName, data);
    }

    @Override
    String getPath() {
        return getBasePath() + CONTENT_TYPE + "/" + fileName;
    }
}
