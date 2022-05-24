package designpatterns.fectorymethod01;

public abstract class FilePrinter {


    void printToFile(){
        ContentFile contentFile = createContentFile("~","my","data","filename");
        contentFile.getPath();
        System.out.println(contentFile.getFileName() +"를 "+contentFile.getPath() +"에 파일로 출력한다.");
    }

    abstract ContentFile createContentFile(String basePath, String owner, String data, String fileName);


}
