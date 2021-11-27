/* 7. Implement a multithreaded program in Java to solve the producerconsumer problem.
Producer and Consumer are the two entities here who share the same buffer.
The producer can either go to sleep or discard data if the buffer is full. The
next time the consumer removes an item from the buffer, it notifies the
producer, who starts to fill the buffer again. In the same way, the consumer
can go to sleep if it finds the buffer to be empty. The next time the
producer puts data into the buffer, it wakes up the sleeping consumer.
An inadequate solution could result in a deadlock where both processes are
waiting to be awakened. */

import java.util.Scanner;

// A correct implementation of a producer and consumer.
class Buffer {
    int n;
    boolean empty = false;

    synchronized int get() {
        while (!empty)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        System.out.println("Got: " + n);
        empty = false;
        notify();
        return n;
    }

    synchronized void put(int n) {
        while (empty)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        this.n = n;
        empty = true;
        System.out.println("Put: " + n);
        notify();
    }
}

class Producer implements Runnable {
    Buffer b;
    int count;

    Producer(Buffer b, int n) {
        this.b = b;
        count = n;
        new Thread(this, "Producer").start();
    }

    public void run() {
        int i = 0;
        while (count > 0) {
            b.put(i++);
            count--;
        }
    }
}

class Consumer implements Runnable {
    Buffer b;
    int count;

    Consumer(Buffer b, int n) {
        this.b = b;
        count = n;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        while (count > 0) {
            b.get();
            count--;
        }
    }
}

class ProdCon {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input no of elements to be produced = ");
        int n = sc.nextInt();
        Buffer b = new Buffer();
        sc.close();
        new Producer(b, n);
        new Consumer(b, n);
    }
}
