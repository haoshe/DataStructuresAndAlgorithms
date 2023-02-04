package CreateQueueUisngArray;

public class MyQueue {
    private int[] arr;
    private int pushPos; // end position, add from the end
    private int pollPos; // begin position, take from the beginning
    private int size;
    private final int limit;
    public MyQueue(int limit){
        arr = new int[limit];
        pushPos = 0;
        pollPos = 0;
        size = 0;
        this.limit = limit;
    }

    // add an int to the queue
    public void enQueue(int value){
        if(size == limit){
            throw new RuntimeException("the queue is full!");
        }
        size++;
        arr[pollPos] = value;
        pushPos = nextIndex(pushPos);
    }

    // pop the first element from the queue
    public int deQueue(){
        if(size == 0){
            throw new RuntimeException("the Queue is empty!");
        }
        size--;
        int ans = arr[pollPos];
        pollPos = nextIndex(pollPos);
        return ans;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int nextIndex(int position){
        return position < limit - 1 ? position++ : 0;
    }
}
