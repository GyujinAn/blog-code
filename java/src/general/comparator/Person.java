package general.comparator;

public class Person{
    public Person(int no) {
        this.no = no;
    }

    int no;

    @Override
    public String toString() {
        return "Person{" +
                "no=" + no +
                '}';
    }
}