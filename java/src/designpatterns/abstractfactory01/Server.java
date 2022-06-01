package designpatterns.abstractfactory01;

public class Server {

    AbstractProductA abstractProductA;
    AbstractProductB abstractProductB;
    AbstractFactory abstractFactory;
    public Server(AbstractFactory abstractFactory) {
        this.abstractFactory = abstractFactory;
    }

    void process(){

        createProduct();

        abstractProductA.firstMethodInProductA();

        abstractProductA.secondMethodInProductA();

        abstractProductB.firstMethodInProductB();

        abstractProductB.secondMethodInProductB();

    }

    private void createProduct(){

        abstractProductA = abstractFactory.createProductA();

        abstractProductB = abstractFactory.createProductB();

    }


}
