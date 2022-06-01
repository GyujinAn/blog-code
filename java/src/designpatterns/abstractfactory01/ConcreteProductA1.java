package designpatterns.abstractfactory01;

public class ConcreteProductA1 implements AbstractProductA{

    @Override
    public void firstMethodInProductA() {
        System.out.println("here is firstMethodInProductA1");
    }

    @Override
    public void secondMethodInProductA() {
        System.out.println("here is secondMethodInProductA1");
    }
}
