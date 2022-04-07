package designpatterns.strategy01;

/**
 * @author agj017@gmail.com
 * @since 2022/03/22
 */
public class ThreePointShot implements Attack {
    @Override
    public void doAttack() {
        System.out.println("**try a three-point shot**");
    }
}
