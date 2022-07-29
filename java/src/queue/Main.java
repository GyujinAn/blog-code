package queue;

public class Main {
    public static void main(String[] args) {

        try {
            Queue queue = new Queue();
            queue.enqueue(12);
            queue.enqueue(54);
            queue.enqueue(67);
            queue.enqueue(91);
            queue.enqueue(12);
            queue.enqueue(54);
            queue.enqueue(67);
            queue.enqueue(91);
            queue.dequeue();
            queue.dequeue();
            queue.enqueue(88);
            queue.enqueue(45);
            queue.print();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            CircularQueue queue = new CircularQueue();
            queue.enqueue(12);
            queue.enqueue(54);
            queue.enqueue(67);
            queue.enqueue(91);
            queue.enqueue(12);
            queue.enqueue(54);
            queue.enqueue(67);
            queue.enqueue(91);
            queue.dequeue();
            queue.dequeue();
            queue.enqueue(88);
            queue.enqueue(45);
            queue.print();
            System.out.println(queue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
