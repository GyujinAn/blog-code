package designpatterns.fectorymethod01;

public class ImgFile extends ContentFile{

    private final String CONTENT_TYPE = "img";

    public ImgFile(String basePath, String userId, String fileName, String data) {
        super(basePath, userId, fileName, data);
    }

    @Override
    String getPath() {
        return getBasePath() + CONTENT_TYPE + "/" + fileName;
    }
}
