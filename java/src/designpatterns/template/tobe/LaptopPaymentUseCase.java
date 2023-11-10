package designpatterns.template.tobe;

import static designpatterns.template.tobe.Application.LAPTOP_PRICE;
import static designpatterns.template.tobe.Application.members;

public class LaptopPaymentUseCase {
    public void execute(String who) {
        Member member = members.get(who);

        member.deductBalance(LAPTOP_PRICE);
    }
}
