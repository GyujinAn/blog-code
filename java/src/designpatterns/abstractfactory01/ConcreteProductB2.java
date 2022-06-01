package designpatterns.abstractfactory01;

public class ConcreteProductB2 implements AbstractProductB{

    @Override
    public void firstMethodInProductB() {
        System.out.println("here is firstMethodInProductB2");
    }

    @Override
    public void secondMethodInProductB() {
        System.out.println("here is secondMethodInProductB2");
    }
}
