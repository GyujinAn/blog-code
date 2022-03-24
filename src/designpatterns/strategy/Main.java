package designpatterns.strategy;

/**
 * @author agj017@gmail.com
 * @since 2022/03/22
 */
public class Main {

    public static void main(String[] args) {
        BasketballPlayer kevinDurant = new BasketballPlayer();
        System.out.println("농구 경기 시작");
        System.out.println("듀란트 공격 턴");
        System.out.println("수비가 골대에 모인 상황");
        kevinDurant.setAttack(new ThreePointShot());
        kevinDurant.doAttack();
        System.out.println("듀란트 공격 완료");

        System.out.println("듀란트 공격 턴");
        System.out.println("수비가 듀란트에게 가까이 붙은 상황");
        kevinDurant.setAttack(new Layup());
        kevinDurant.doAttack();
        System.out.println("듀란트 공격 완료");


    }
}
