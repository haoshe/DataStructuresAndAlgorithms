package UseStacksCreateQueue;

import java.util.Stack;

// use two stacks to implement a queue
// the most important method is to empty data in stackPush into stackPop
// pay attention to two things so to make sure the process is correct all the time
// 1. only when stackPop is empty, we then start emptying data process in stackPush
// 2. if we start emptying process, we have to empty all the data in stackPush
public class TwoStacksQueue {
    private final Stack<Integer> stackPush;
    private final Stack<Integer> stackPop;
    public TwoStacksQueue(){
        this.stackPush = new Stack<Integer>();
        this.stackPop = new Stack<Integer>();
    }

    // empty all data in stackPush into stackPop
    private void pushToPop(){
        if(stackPop.isEmpty()){
            while(!stackPush.isEmpty()){
                stackPop.push(stackPush.pop());
            }
        }
    }

    // enQueue
    public void add(int value){
        pushToPop();
        stackPush.push(value);
    }

    // deQueue
    public int poll(){
        if(stackPush.isEmpty() && stackPop.isEmpty()){
            throw new UnsupportedOperationException("the queue is empty.");
        }
        pushToPop();
        return stackPop.pop();
    }

    // have a look at the first element in the queue
    public int peek(){
        if(stackPush.isEmpty() && stackPop.isEmpty()){
            throw new UnsupportedOperationException("the queue is empty.");
        }
        pushToPop();
        return stackPop.peek();
    }

}
