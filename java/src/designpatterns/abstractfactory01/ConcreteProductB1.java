package designpatterns.abstractfactory01;

public class ConcreteProductB1 implements AbstractProductB{

    @Override
    public void firstMethodInProductB() {
        System.out.println("here is firstMethodInProductB1");
    }

    @Override
    public void secondMethodInProductB() {
        System.out.println("here is secondMethodInProductB1");
    }
}
