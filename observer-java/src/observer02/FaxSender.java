package observer02;

/**
 * @author agj017@gmail.com
 * @since 2022/03/29
 */
public class FaxSender implements Observer{

    String title;

    String contents;

    @Override
    public void update(Subject subject) {

        if(subject instanceof NewsStation){
            NewsStation newsStation = (NewsStation) subject;
            title = newsStation.getTitle();
            contents = newsStation.getContents();
            sendFax();
        }

    }

    private void sendFax(){

        System.out.println("---팩스전송---");
        System.out.println("제목: " + title);
        System.out.println("내용: " + contents);

    }
}
