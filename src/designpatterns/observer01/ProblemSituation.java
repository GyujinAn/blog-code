package designpatterns.observer01;

/**
 * @author agj017@gmail.com
 * @since 2022/03/29
 */
class ProblemMailSender {
    void update(String title, String contents){
        System.out.println("---메전송---");
        System.out.println("제목: " + title);
        System.out.println("내용: " + contents);
    }

}

class ProblemMessageSender {
    void update(String title, String contents){
        System.out.println("---문자전송---");
        System.out.println("제목: " + title);
        System.out.println("내용: " + contents);
    }
}

class ProblemFaxSender {
    void update(String title, String contents){
        System.out.println("---팩스전송---");
        System.out.println("제목: " + title);
        System.out.println("내용: " + contents);
    }
}

class ProblemNewsStation{

    String title;

    String contents;

    ProblemMailSender problemMailSender = new ProblemMailSender();

    ProblemMessageSender problemMessageSender = new ProblemMessageSender();

    ProblemFaxSender problemFaxSender = new ProblemFaxSender();

    public void setNewsflash(String title, String contents) {
        this.title = title;
        this.contents = contents;
        problemMailSender.update(title, contents);
        problemMessageSender.update(title, contents);
        problemFaxSender.update(title, contents);
    }
}

public class ProblemSituation {

    public static void main(String[] args) {
        new ProblemNewsStation().setNewsflash("우크라이나 러시아 종전 선언","러시아 패배로 인하여 전쟁이 종료되었다.");;
    }
}
