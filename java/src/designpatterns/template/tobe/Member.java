package designpatterns.template.tobe;

abstract class Member {
    public int balance;

    public Member(int balance) {
        this.balance = balance;
    }
    abstract void deductBalance(int price);
}
