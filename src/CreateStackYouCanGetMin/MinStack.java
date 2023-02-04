package CreateStackYouCanGetMin;

import java.util.Stack;

/*
* 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
1)pop: push、getMin操作的时间复杂度都是O(1)；
2)设计的栈类型可以使用现成的栈结构。
* */
public class MinStack {
    // create two stacks, one for data, another for current min data
    private final Stack<Integer> stackData;
    private final Stack<Integer> stackMin;
    public MinStack(){
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    // method 1: when we push a value into stackData, we check if the value is smaller than stackMin.peek()
    // if it is, we also push the value into stackMin. if it isn't, when push stackMin.peek() into stackMin,
    // let stackMin's size grow at the same rate as stackData
    public void push(int value){
        if(stackMin.isEmpty()){
            this.stackMin.push(value);
        }else if(value < stackMin.peek()){
            this.stackMin.push(value);
        }else{// if value >= stackMin.peek(), push stackMin.peek() again into stackMin
            this.stackMin.push(this.stackMin.peek());
        }
        stackData.push(value);
    }

    // method 1: because stackMin's size should stay the same as stackData
    // when we stackData.pop(), we have to stackMin.pop(), but only return stackData.pop()
    public int pop(){
        if(this.stackData.isEmpty()){
            throw new RuntimeException("this stack is empty.");
        }
        // throw away the last element in the stackMin
        stackMin.pop();
        return stackData.pop();
    }

    //****************************************************************************************
    // method 2 for push and pop, stackMin's size is not necessary growing with stackData, only when stackData.push()
    // a value which is smaller than stackMin.peek(), then we push a new min value into stackMin
    public void push2(int value){
        if(this.stackMin.isEmpty()){
            this.stackMin.push(value);
        }else if(value <= stackMin.peek()){
            this.stackMin.push(value);
        }
        this.stackData.push(value);
    }

    // when we pop a value, if this value is the same as stackMin.peek(), whe pop a value from stackMin as well
    // we don't need to consider if the value is smaller or bigger than stackMin.peek(), because when we push the value
    // into stackData, we already make sure only the smaller value get pushed into stackMin, which means when we pop the
    // value, if the popped value is bigger than stackMin.peek(), we don't do anything about stackMin. the popped value
    // won't be smaller than stackMin.peek(), because stackMin's values are all smaller or equal to stackData's value
    // that's why we only consider when the popped value is equal to stackMin.peek()
    public int pop2(){
        if(this.stackData.isEmpty()){
            throw new RuntimeException("this stack is empty!");
        }
        int result = stackData.pop();
        if(result == getMin()){
            stackMin.pop();
        }
        return result;
    }


    public int getMin(){
        if(this.stackData.isEmpty()){
            throw new RuntimeException("the stack is empty");
        }
       // don't use pop(), because we just want to know what min is, not changing data in the stack
        return stackMin.peek();
    }
}
