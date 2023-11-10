package designpatterns.template.tobe;

public class Developer extends Member{
    public Developer(int balance) {
        super(balance);
    }

    @Override
    void deductBalance(int price) {
        this.balance = this.balance - (price - 10);
    }
}
