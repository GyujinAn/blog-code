package designpatterns.observer01;

/**
 * @author agj017@gmail.com
 * @since 2022/03/29
 */
class ProblemMailSender {
    void send(String newsflash){
        System.out.println("--- 메일 속보 발송 ---");
        System.out.println("내용: " + newsflash);
    }

}

class ProblemMessageSender {
    void send(String newsflash){
        System.out.println("--- 문자 속보 발송 ---");
        System.out.println("내용: " + newsflash);
    }
}

class ProblemFaxSender {
    void send(String newsflash){
        System.out.println("--- 팩스 속보 발송 ---");
        System.out.println("내용: " + newsflash);
    }
}

//class ProblemLineSender{
//
//}
//
//class ProblemKakaoSender{
//
//}

class ProblemNewsStation{

    String newsflash;

    ProblemMailSender problemMailSender = new ProblemMailSender();

    ProblemMessageSender problemMessageSender = new ProblemMessageSender();

    ProblemFaxSender problemFaxSender = new ProblemFaxSender();

    public String getNewsflash() {
        return newsflash;
    }

    public void setNewsflash(String newsflash) {
        this.newsflash = newsflash;
        problemMailSender.send(newsflash);
        problemMessageSender.send(newsflash);
        problemFaxSender.send(newsflash);
    }
}

public class ProblemSituation {

    public static void main(String[] args) {
        new ProblemNewsStation().setNewsflash("속보!! 외계인이 침공하였다.");
    }
}
