package general.comparator;

import java.util.Comparator;

public class DescendingPersonComparator implements Comparator<Person> {
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
