package datastructure.queue;

public class CircularQueue {

    private int [] arr;

    private int front;

    private int rear;

    private int capable;

    private final int QUEUE_SIZE = 8;


    public CircularQueue() {
        this.arr = new int [QUEUE_SIZE];
        this.front = 0;
        this.rear = 0;
        this.capable = 0;
    }

    public void enqueue(int value) throws Exception {
        if(rear == QUEUE_SIZE)
            rear = 0;

        arr[rear] = value;
        rear++;
        capable++;

    }

    public int dequeue() throws Exception {
        if(front == rear)
            throw new Exception("empty datastructure.queue");

        if(front == QUEUE_SIZE)
            front = 0;

        int value = arr[front];
        front++;
        capable--;

        return value;
    }

    public void print() {

        System.out.print("CircularQueue{ ");

        int i = front;
        do{
            if(i == QUEUE_SIZE)
                i = 0;

            System.out.print(arr[i] + " ");
            i++;
        }while (i != rear);
        System.out.println("}");

    }

}
