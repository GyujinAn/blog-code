package designpatterns.template.asis;

public class Member {
    public Job job;
    public int balance;

    public Member(Job job, int balance) {
        this.job = job;
        this.balance = balance;
    }
}

enum Job {
    DEVELOPER
}