package general.queue.custompriorityqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class CustomPriorityQueue {

    public static void main(String[] args) {

        PriorityQueue<SampleNode> queue = new PriorityQueue<>(
                (o1, o2) -> o2.cnt - o1.cnt);

        queue.add(new SampleNode("hello", 1));
        System.out.println("here: "+queue);
        queue.add(new SampleNode("hello", 2));
        System.out.println("here: "+queue);
        queue.add(new SampleNode("hello", 3));
        System.out.println("here: "+queue);
        queue.add(new SampleNode("hello", 4));
        System.out.println("here: "+queue);
        queue.add(new SampleNode("hello", 5));
        System.out.println("here: "+queue);

        System.out.println(queue);

        List<SampleNode> list = new ArrayList<>();

        list.add(new SampleNode("hello", 1));
        list.add(new SampleNode("hello", 2));
        list.add(new SampleNode("hello", 3));
        list.add(new SampleNode("hello", 4));
        list.add(new SampleNode("hello", 5));

        System.out.println(list.toString());

        Collections.sort(list, (o1, o2) -> o2.cnt - o1.cnt);

        System.out.println(list.toString());
    }


}

class SampleNode{
    String name;
    int cnt;

    public SampleNode(String name, int cnt) {
        this.name = name;
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "SampleNode{" +
                "name='" + name + '\'' +
                ", cnt=" + cnt +
                '}';
    }
}
