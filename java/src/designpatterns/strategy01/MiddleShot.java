package designpatterns.strategy01;

/**
 * @author agj017@gmail.com
 * @since 2022/03/22
 */
public class MiddleShot implements Attack {
    @Override
    public void doAttack() {
        System.out.println("**try a middle shot**");
    }
}
