package designpatterns.fectorymethod01;

public class VideoFilePrinter extends FilePrinter{
    @Override
    ContentFile createContentFile(String basePath, String owner, String data, String fileName) {
        return new VideoFile(basePath, owner, data, fileName);
    }
}
