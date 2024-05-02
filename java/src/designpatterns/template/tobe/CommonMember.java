package designpatterns.template.tobe;

public class CommonMember extends Member{
    public CommonMember(int balance) {
        super(balance);
    }

    @Override
    void deductBalance(int price) {
        this.balance = this.balance - price;
    }
}
