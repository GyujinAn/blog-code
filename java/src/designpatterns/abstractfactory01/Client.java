package designpatterns.abstractfactory01;

public class Client {
    public static void main(String[] args) {
        AbstractFactory abstractFactory1 = new ConcreteFactory1();

        AbstractFactory abstractFactory2 = new ConcreteFactory2();

        Server server1 = new Server(abstractFactory1);

        Server server2 = new Server(abstractFactory2);

        server1.process();

        server2.process();

    }
}
