package general.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2022/03/29
 */
class Person{
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

class AscendingPersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if(o1.no > o2.no){
            return 1;
        }else if(o1.no < o2.no){
            return -1;
        }else {
            return 0;
        }
    }
}

class DescendingPersonComparator implements Comparator<Person>{
    @Override
    public int compare(Person o1, Person o2) {
        if(o1.no > o2.no){
            return -1;
        }else if(o1.no < o2.no){
            return 1;
        }else {
            return 0;
        }
    }
}

public class Main {

    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();

        list.add(new Person(2));
        list.add(new Person(0));
        list.add(new Person(4));
        list.add(new Person(1));
        list.add(new Person(3));

        System.out.println("---현재상태---");
        list.stream().forEach(System.out::print);

        System.out.println();
        System.out.println("---오름차순으로 전략교체---");
        Collections.sort(list, new AscendingPersonComparator());

        list.stream().forEach(System.out::print);

        System.out.println();
        System.out.println("---내림차순으로 전략교체---");
        Collections.sort(list, new DescendingPersonComparator());

        list.stream().forEach(System.out::print);

    }


}
