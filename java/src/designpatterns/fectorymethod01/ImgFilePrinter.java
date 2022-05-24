package designpatterns.fectorymethod01;

public class ImgFilePrinter extends FilePrinter{
    @Override
    ContentFile createContentFile(String basePath, String owner, String data, String fileName) {
        return new ImgFile(basePath, owner, data, fileName);
    }
}
