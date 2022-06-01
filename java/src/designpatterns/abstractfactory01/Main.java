package designpatterns.abstractfactory01;

public class Main {
    public static void main(String[] args) {
        AbstractFactory abstractFactory1 = new ConcreteFactory1();

        AbstractFactory abstractFactory2 = new ConcreteFactory2();

        Client server1 = new Client(abstractFactory1);

        Client server2 = new Client(abstractFactory2);

        server1.process();

        server2.process();

    }
}
