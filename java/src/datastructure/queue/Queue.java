package datastructure.queue;

import java.util.Arrays;

public class Queue {

    private int [] arr;

    private int front;

    private int rear;

    private int capable;

    private final int QUEUE_SIZE = 8;

    public Queue() {
        this.arr = new int [QUEUE_SIZE];
        this.front = 0;
        this.rear = 0;
        this.capable = 0;
    }

    public void enqueue(int value) throws Exception {
        if(rear == QUEUE_SIZE)
            compact();

        arr[rear] = value;
        rear++;
        capable++;

    }

    public int dequeue() throws Exception {
        if(front == rear)
            throw new Exception("empty queue");

        int value = arr[front];
        front++;
        capable--;

        return value;
    }

    private void compact() throws Exception {
        if(front == 0)
            throw new Exception("full queue");

        int oldFront = front;
        for(int i = 0; i < capable; i++){
            arr[i] = arr[oldFront];
            oldFront++;
        }

        front = 0;
        rear = capable;
    }


    public void print() {

        System.out.print("Queue{ ");
        for(int i = front; i < rear; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("}");

    }
}
