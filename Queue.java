package LinkedList2;
import LinkedList2.LinkedList;
import java.util.Stack;

import java.util.Iterator;
import java.util.*;
/**
 * Queue: custom implementation
 * @author     John Mortensen
 *
 * 1. Uses custom LinkedList of Generic type T
 * 2. Implements Iterable
 * 3. "has a" LinkedList for head and tail
 */
public class Queue<T> implements Iterable<T> {
    LinkedList<T> head, tail;

    /**
     *  Add a new object at the end of the Queue,
     *
     * @param  data,  is the data to be inserted in the Queue.
     */
    public void add(T data) {
        // add new object to end of Queue
        LinkedList<T> tail = new LinkedList<>(data, this.tail);

        if (head == null)  // initial condition
            this.head = this.tail = tail;
        else {  // nodes in queue
            this.tail.setNextNode(tail); // current tail points to new tail
            this.tail = tail;  // update tail
        }
    }


    //FRQ part 1
    public void delete() {
        if (this.head == null) {
            return;
        }

        LinkedList<T> head = this.head.getNext();
        if (head == null) {
          this.head = this.tail = null;
          return;
        }
        head.setPrevNode(null);
        this.head.setNextNode(null);
        this.head = head;
    }

    //FRQ part 2

    public Queue<T> merge(Queue<T> queue2) {
        Queue<T> mergeArr = new Queue<>();
        while (this.getHead() != null && queue2.getHead() != null) {
            if (this.getHead() == null) {
                T add = queue2.getHead().getData();
                queue2.delete();
                mergeArr.add(add);
            } else if (queue2.getHead() == null) {
                T add = this.getHead().getData();
                this.delete();
                mergeArr.add(add);
            } else if (this.getHead().getData().hashCode() <= queue2.getHead().getData().hashCode()) {
                T add = this.getHead().getData();
                this.delete();
                mergeArr.add(add);
            } else if (this.getHead().getData().hashCode() > queue2.getHead().getData().hashCode()){
                T add = queue2.getHead().getData();
                queue2.delete();
                mergeArr.add(add);
            }
        }
        if (this.getHead() != null) {
            T add = this.getHead().getData();
            this.delete();
            mergeArr.add(add);
        } else if (queue2.getHead() != null) {
            T add = queue2.getHead().getData();
            queue2.delete();
            mergeArr.add(add);
        }
        /**for (T element: mergeArr) {
            System.out.println(element);
        }*/
        return mergeArr;
    }

    //FRQ 3
    public Queue<T> reverseQueue() {
        Queue<T> reverse = new Queue<>();
        while (this.getHead() != null) {
            T add = this.getTail().getData();
            reverse.add(add);
        }

        return reverse;

    }
    /**
     *  Returns the head object.
     *
     * @return  this.head, the head object in Queue.
     */
    public LinkedList<T> getHead() {
        return this.head;
    }

    /**
     *  Returns the tail object.
     *
     * @return  this.tail, the last object in Queue
     */
    public LinkedList<T> getTail() {
        return this.tail;
    }

    /**
     *  Returns the iterator object.
     *
     * @return  this, instance of object
     */
    public Iterator<T> iterator() {
        return new QueueIterator<>(this);
    }
}

/**
 * Queue Iterator
 *
 * 1. "has a" current reference in Queue
 * 2. supports iterable required methods for next that returns a data object
 */
class QueueIterator<T> implements Iterator<T> {
    LinkedList<T> current;  // current element in iteration

    // QueueIterator is intended to the head of the list for iteration
    public QueueIterator(Queue<T> q) {
        current = q.getHead();
    }

    // hasNext informs if next element exists
    public boolean hasNext() {
        return current != null;
    }

    // next returns data object and advances to next position in queue
    public T next() {
        T data = current.getData();
        current = current.getNext();
        return data;
    }
}

/**
 * Queue Manager
 * 1. "has a" Queue
 * 2. support management of Queue tasks (aka: titling, adding a list, printing)
 */
class QueueManager<T> {
    // queue data
    private final String name; // name of queue
    private int count = 0; // number of objects in queue
    public final Queue<T> queue = new Queue<>(); // queue object

    /**
     *  Queue constructor
     *  Title with empty queue
     */
    public QueueManager(String name) {
        this.name = name;
    }

    /**
     *  Queue constructor
     *  Title with series of Arrays of Objects
     */
    public QueueManager(String name, T[]... seriesOfObjects) {
        this.name = name;
        this.addList(seriesOfObjects);
    }

    /**
     * Add a list of objects to queue
     */
    public void addList(T[]... seriesOfObjects) {
        for (T[] objects: seriesOfObjects)
            for (T data : objects) {
                this.queue.add(data);
                this.count++;
            }
    }

    /**
     * Print any array objects from queue
     */
    public void printQueue() {
        System.out.println(this.name + " count: " + count);
        System.out.print(this.name + " data: ");
        for (T data : queue)
            System.out.print(data + " ");
        System.out.println();
    }
}

/**
 * Driver Class
 * Tests queue with string, integers, and mixes of Classes and types
 */

class QueueTester {
    public static void main(String[] args)
    {
        // Create iterable Queue of Words
        Object[] words = new String[] { "seven", "slimy", "snakes", "sallying", "slowly", "slithered", "southward"};
        QueueManager qWords = new QueueManager("Words", words );
        qWords.printQueue();

        // Create iterable Queue of Integers
        Object[] numbers = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        QueueManager qNums = new QueueManager("Integers", numbers );
        qNums.printQueue();



        qNums.queue.add(10);
        qNums.printQueue();
        qNums.queue.delete();
        qNums.printQueue();
        qNums.queue.delete();
        qNums.printQueue();

        qWords.printQueue();
        qWords.queue.delete();
        qWords.printQueue();
        qWords.queue.delete();
        qWords.printQueue();
        qWords.queue.delete();
        qWords.printQueue();
        qWords.queue.delete();
        qWords.printQueue();
        qWords.queue.delete();
        qWords.printQueue();
        qWords.queue.delete();
        qWords.printQueue();


        Object[] num1 = new Integer[] { 1, 4, 5, 8};
        QueueManager qNum1 = new QueueManager("Integers", num1 );
        qNum1.printQueue();

        Object[] num2 = new Integer[] { 2, 3, 6, 7};
        QueueManager qNum2 = new QueueManager("Integers", num2 );
        qNum2.printQueue();

        Queue qMerged = qNum1.queue.merge(qNum2.queue);

        System.out.print("Merged Queue: ");
        for (Object data : qMerged)
            System.out.print(data + " -> ");
        System.out.print("nil");


        Queue qReverse = qNum1.queue.reverseQueue();
        for (Object data : qReverse)
            System.out.print(data + " -> ");
        System.out.print("nil");

    }
}