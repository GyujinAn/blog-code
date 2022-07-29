package general.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2022/03/29
 */
public class Main {

    public static void main(String[] args) {
        examArraySort();
        examCollectionSort();
    }

    static private void examArraySort(){
        System.out.println("---------examArraySort() is started---------");

        Person [] array = new Person[5];
        array[0] = new Person(2);
        array[1] = new Person(0);
        array[2] = new Person(4);
        array[3] = new Person(1);
        array[4] = new Person(3);

        System.out.println("---현재상태---");
        printArr(array);

        System.out.println();
        System.out.println("---오름차순으로 전략교체---");
        Arrays.sort(array, new AscendingPersonComparator());
        printArr(array);

        System.out.println();
        System.out.println("---내림차순으로 전략교체---");
        Arrays.sort(array, new DescendingPersonComparator());
        printArr(array);

    }

    static private void printArr(Person [] array){
        for(Person p : array){
            System.out.println(p.toString());
        }
    }

    static private void examCollectionSort(){
        System.out.println("---------examCollectionSort() is started---------");

        List<Person> list = new ArrayList<>();

        list.add(new Person(2));
        list.add(new Person(0));
        list.add(new Person(4));
        list.add(new Person(1));
        list.add(new Person(3));

        System.out.println("---현재상태---");
        list.stream().forEach(System.out::println);

        System.out.println();
        System.out.println("---오름차순으로 전략교체---");
        Collections.sort(list, new AscendingPersonComparator());

        list.stream().forEach(System.out::println);

        System.out.println();
        System.out.println("---내림차순으로 전략교체---");
        Collections.sort(list, new DescendingPersonComparator());

        list.stream().forEach(System.out::println);
    }
}
