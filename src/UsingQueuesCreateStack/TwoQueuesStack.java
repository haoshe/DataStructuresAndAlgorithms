package UsingQueuesCreateStack;

import UseStacksCreateQueue.TwoStacksQueue;

import java.util.LinkedList;
import java.util.Queue;

// use queues to implement a stack
public class TwoQueuesStack<T> {
    private Queue<T> queue;
    private Queue<T> help;
    public TwoQueuesStack(){
        queue = new LinkedList<>();
        help = new LinkedList<>();
    }

    public void push(T value){
        queue.offer(value);// add a value into the queue without violating capacity restrictions
    }

    // get the last element of the queue
    public T pop(){
        // leave the last element in the queue, empty the rest into help queue
        while(queue.size() > 1){
            help.offer(queue.poll());
        }
        T result = queue.poll();
        // swap queue and help
        Queue<T> temp = queue;
        queue = help;
        help = temp;
        return result;
    }

    // have a look at the last element in the queue
    public T peek(){
        while(queue.size() > 1){
            help.offer(queue.poll());
        }
        // get the only left element in the queue
        T result = queue.poll();
        // add it to the help
        help.offer(result);
        Queue<T> temp = queue;
        queue = help;
        help = temp;
        return result;
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
