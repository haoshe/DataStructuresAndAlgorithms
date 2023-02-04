package JosephusProblem;

public class JosephusProblem {
    public static void main(String[] args){
        // test if addBoy() and showBoy work
        CircularOneWayLinkedList circularOneWayLinkedList = new CircularOneWayLinkedList();
        // add five boy nodes
        circularOneWayLinkedList.addBoy(5);
        circularOneWayLinkedList.showBoy();

        // test countBoy()
        circularOneWayLinkedList.countBoy(1,2,5); // 2->4->1->5->3
    }
}

// create a circular one-way linked list
class CircularOneWayLinkedList{
    // create a Boy pointer, let it point to null;
    private Boy first = null;
    // add Boy node, create a circular one-way linked list
    // nums represents the number of nodes you want to add
    public void addBoy(int nums){
        // verify nums
        if(nums < 1){
            System.out.println("invalid nums");
            return;
        }
        // auxiliary pointer, helps to create the circular linked list
        Boy curBoy = null;
        // use for loop to create the circular linked list
        for(int i=1; i<=nums; i++){
            // create Boy nodes based on its no
            Boy boy = new Boy(i);
            // if it's the first Boy node
            if(i==1){
                // let the first pointer points to this node
                first = boy;
                // point to itself, create a circular loop
                first.setNext(first);
                // let curBoy point the first Boy. Because first pointer will be fixed,
                // we need curBoy pointer to move forward later
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // iterate circular linked list
    public void showBoy(){
        // determine if the linked list is null
        if(first == null){
            System.out.println("this linked list doesn't exit");
            return;
        }
        // because first pointer is fixed, so we still need to use an auxiliary pointer to iterate
        Boy curBoy = first;
        while(true){
            System.out.printf("boy's number %d \n", curBoy.getNo());
            if(curBoy.getNext()==first){ // indicate the pointer has reached the end of the loop
                break;
            }
            // curBoy pointer moves forward
            curBoy = curBoy.getNext();
        }
    }

    // calculate the sequence of boys coming out of the circle, based on customer's input

    /**
     *
     * @param startNo from which boy start to count
     * @param countNo count how many times
     * @param nums    initial number of boys in the circle
     */
    public void countBoy(int startNo, int countNo, int nums){
        // verify data
        if(first==null || startNo<1 || startNo>nums){
            System.out.println("invalid input");
            return;
        }
        // create an auxiliary pointer, help boy coming out of the circle
        Boy helper = first;
        // helper pointer should point the last node of the circle
        while(true) {
            if (helper.getNext() == first) {// indicate helper points to the last node
                break;
            }
            helper = helper.getNext();
        }
        // let first and helper pointers move to the start of counting place before boys start counting
        // move to k-1
        for(int j=0; j<startNo-1; j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        // when boys start counting, let first and helper pointers move m-1 times, then the (m-1)th boy coming out
        // this is a loop, until only one node left
        while(true){
            if(helper==first){// indicate only one node left
                break;
            }
            // let first and helper pointer simultaneously move countNum-1 times
            for(int j=0; j<countNo-1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            // at this time, first pointer points the boy who needs to come out of the circle
            System.out.printf("boy %d coming out\n", first.getNo());
            // get rid of the node which first pointer points to
            first = first.getNext();
            helper.setNext(first);
        }
        // when while loop finishes, only one node left in the circle
        System.out.printf("the last boy's number who stays in the circle is %d\n", first.getNo());
    }
}

// create a Boy class, represents a node
class Boy{
    private int no; // number
    private Boy next; // the pointer which points the next node, default value is null
    public Boy(int no){
        this.no = no;
    }
    public int getNo(){
        return no;
    }
    public void setNo(int no){
        this.no = no;
    }
    public Boy getNext(){
        return next;
    }
    public void setNext(Boy next) {
        this.next = next;
    }
}
