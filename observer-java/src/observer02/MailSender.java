package observer02;

/**
 * @author agj017@gmail.com
 * @since 2022/03/29
 */
public class MailSender implements Observer{

    String title;

    String contents;

    @Override
    public void update(Subject subject) {

        if(subject instanceof NewsStation){
            NewsStation newsStation = (NewsStation) subject;
            title = newsStation.getTitle();
            contents = newsStation.getContents();
            sendMail();
        }

    }

    private void sendMail(){

        System.out.println("---메일전송---");
        System.out.println("제목: " + title);
        System.out.println("내용: " + contents);

    }
}
