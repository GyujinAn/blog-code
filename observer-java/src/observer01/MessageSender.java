package observer01;

/**
 * @author agj017@gmail.com
 * @since 2022/03/29
 */
public class MessageSender implements Observer {

    @Override
    public void update(String title, String contents) {
        System.out.println("---문자전송---");
        System.out.println("제목: " + title);
        System.out.println("내용: " + contents);

    }

}
