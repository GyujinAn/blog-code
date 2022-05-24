package designpatterns.fectorymethod01;

public class MusicFilePrinter extends FilePrinter{
    @Override
    ContentFile createContentFile(String basePath, String owner, String data, String fileName) {
        return new MusicFile(basePath, owner, data, fileName);
    }
}
