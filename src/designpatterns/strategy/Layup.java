package designpatterns.strategy;

/**
 * @author agj017@gmail.com
 * @since 2022/03/22
 */
public class Layup implements Attack {
    @Override
    public void doAttack() {
        System.out.println("**try a layup shot**");
    }
}
