package SinglyDoublyLinkedList;

public class SinglyDoublyLinkedList {

    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int data){
            this.value = data;
        }
    }

    public static Node reverseLinkedList(Node head){
        Node prev = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
         return prev;
    }

    public static DoubleNode reverseDoublyLinkedList(DoubleNode head){
        DoubleNode prev = null;
        DoubleNode next = null;
        while(head != null){
            next = head.next;
            head.next = prev;
            head.last = next;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static Node removeValue(Node head, int num){
        while(head != null){
            // stop moving the head when head.value != num
            if(head.value != num){
                break;
            }
            // if head.value = num, keep moving the head to the next element
            head = head.next;
        }
        // 1. head == null (all the elements in the linkedList == num)
        //    then the while code block will be ignored, return head
        // 2. head != null
        // curr to iterate linkedList
        // if curr.value == num, pre.next jump ove curr, point to the node after cur
        // if curr.value != num, prev point to curr node
        // curr = curr.next move curr to the next node
        Node prev = head;
        Node curr = head;
        while(curr != null){
            if(curr.value == num){
                // jump over num, let prev.next point to the node after num
                prev.next = curr.next;
            }else{// curr.value != num
                // let prev point to the current node
                prev = curr;
            }
            curr = curr.next;
        }
        return head;
    }
}
