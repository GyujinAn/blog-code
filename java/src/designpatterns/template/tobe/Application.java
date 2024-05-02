package designpatterns.template.tobe;


import java.util.HashMap;
import java.util.Map;

public class Application {
    public static int LAPTOP_PRICE = 50;

    public static Map<String, Member> members = new HashMap<>();

    public static void main(String[] args) {
        members.put("ian", new Developer(1000));
        members.put("logan", new Designer(1000));
        members.put("jin", new CommonMember(1000));

        new LaptopPaymentUseCase().execute("ian");
        new LaptopPaymentUseCase().execute("logan");
        new LaptopPaymentUseCase().execute("jin");
    }
}
