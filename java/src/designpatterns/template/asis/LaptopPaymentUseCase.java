package designpatterns.template.asis;

import static designpatterns.template.asis.Application.LAPTOP_PRICE;
import static designpatterns.template.asis.Application.members;

public class LaptopPaymentUseCase {
    public void execute(String who) {
        Member member = members.get(who);

        if (member.job == Job.DEVELOPER) {
            member.balance = member.balance - (LAPTOP_PRICE - 10);
        } else {
            member.balance = member.balance - LAPTOP_PRICE;
        }

    }
}
