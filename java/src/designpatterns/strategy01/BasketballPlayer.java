package designpatterns.strategy01;

/**
 * @author agj017@gmail.com
 * @since 2022/03/22
 */
public class BasketballPlayer {
    public Attack attack;

    public void doAttack(){
        attack.doAttack();
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }
}
