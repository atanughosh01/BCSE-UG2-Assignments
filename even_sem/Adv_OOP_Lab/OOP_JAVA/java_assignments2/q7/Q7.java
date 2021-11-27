/* 7. Implement a multithreaded program in Java to solve the producerconsumer problem.
Producer and Consumer are the two entities here who share the same buffer.
The producer can either go to sleep or discard data if the buffer is full. The
next time the consumer removes an item from the buffer, it notifies the
producer, who starts to fill the buffer again. In the same way, the consumer
can go to sleep if it finds the buffer to be empty. The next time the
producer puts data into the buffer, it wakes up the sleeping consumer.
An inadequate solution could result in a deadlock where both processes are
waiting to be awakened. */

import java.util.LinkedList;

public class Q7 {
    public static void main(String[] args) throws InterruptedException {
        // Object of a class that has both produce()
        // and consume() methods
        final PC pc = new PC();

        // Create producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
    }

    // This class has a list, producer (adds items to list)
    // and consumber (removes items).
    public static class PC {

        // Create a list shared by producer and consumer
        // Size of list is 2.
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 2;

        // Function called by producer thread
        public void produce() throws InterruptedException {
            int value = 0;
            while (true) {
                synchronized (this) {
                    // producer thread waits while list
                    // is full
                    while (list.size() == capacity)
                        wait();

                    System.out.println("Producer produced-" + value);

                    // to insert the jobs in the list
                    list.add(value++);

                    // notifies the consumer thread that
                    // now it can start consuming
                    notify();

                    // makes the working of program easier
                    // to understand
                    Thread.sleep(1000);
                }
            }
        }

        // Function called by consumer thread
        public void consume() throws InterruptedException {
            while (true) {
                synchronized (this) {
                    // consumer thread waits while list
                    // is empty
                    while (list.size() == 0)
                        wait();

                    // to retrive the first job in the list
                    int val = list.removeFirst();

                    System.out.println("Consumer consumed-" + val);

                    // Wake up producer thread
                    notify();

                    // and sleep
                    Thread.sleep(1000);
                }
            }
        }
    }
}