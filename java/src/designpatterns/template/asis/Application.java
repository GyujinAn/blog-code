package designpatterns.template.asis;

import java.util.HashMap;
import java.util.Map;

public class Application {
    public static int LAPTOP_PRICE = 50;

    public static Map<String, Member> members = new HashMap<>();

    public static void main(String[] args) {
        members.put("ian", new Member(1000));
        members.put("logan", new Member(1000));
        members.put("jin", new Member(1000));

        new LaptopPaymentUseCase().execute("ian");
        new LaptopPaymentUseCase().execute("logan");
        new LaptopPaymentUseCase().execute("jin");
    }
}
