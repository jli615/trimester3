package LinkedList2;
import LinkedList2.LinkedList;
import java.util.Stack;

import java.util.Iterator;
import java.util.*;

public class Stack {
    public static void reverse (Queue<Integer> queue){
        Queue<T> reverse = new Queue<>();
        while (queue.getHead() != null) {
            T add = queue.getTail().getData();
            reverse.add(add);
        }

        return reverse;
    }

    public static void main (String args[]) {
        Object[] num1 = new Integer[] { 1, 4, 5, 8};
        QueueManager qNum1 = new QueueManager("Integers", num1 );
        qNum1.queue.reverse();
    }
}
