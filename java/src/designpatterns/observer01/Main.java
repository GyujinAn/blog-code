package designpatterns.observer01;

/**
 * @author agj017@gmail.com
 * @since 2022/03/29
 */
public class Main {

    public static void main(String[] args) {
        NewsStation newsStation = new NewsStation();
        newsStation.registeObserver(new FaxSender());
        newsStation.registeObserver(new MailSender());
        newsStation.registeObserver(new MessageSender());
        newsStation.breakNews("우크라이나 러시아 종전 선언","러시아 패배로 인하여 전쟁이 종료되었다.");
    }
}
