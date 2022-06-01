package designpatterns.abstractfactory01;

public class ConcreteProductA2 implements AbstractProductA{

    @Override
    public void firstMethodInProductA() {
        System.out.println("here is firstMethodInProductA2");
    }

    @Override
    public void secondMethodInProductA() {
        System.out.println("here is secondMethodInProductA2");
    }
}
