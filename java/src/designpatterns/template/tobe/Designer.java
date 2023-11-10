package designpatterns.template.tobe;

public class Designer extends Member{
    public Designer(int balance) {
        super(balance);
    }

    @Override
    void deductBalance(int price) {
        this.balance = this.balance - (price - 20);
    }
}
