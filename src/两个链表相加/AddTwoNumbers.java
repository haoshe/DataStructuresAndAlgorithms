package 两个链表相加;

public class AddTwoNumbers {

    public static class ListNode{
        public int val;
        public ListNode next;
        public ListNode(int value){
            this.val = value;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        int len1 = getLength(l1);
        int len2 = getLength(l2);
        // find the longer list, assign it to l
        ListNode l = len1 > len2 ? l1 : l2;
        // assign the shorter list to s
        ListNode s = l == l1 ? l2 : l1;
        ListNode curL = l;
        ListNode curS = s;
        ListNode last = curL;
        int carry = 0;
        int currNum = 0;
        // three conditions
        // 1st, both long and short lists have nodes
        while(curS != null){
            currNum = curL.val + curS.val + carry;
            // copy the value back to the long list node
            curL.val = currNum % 10;
            carry = currNum / 10;
            last = curL;
            curL = curL.next;
            curS = curS.next;
        }
        // 2nd, only long list has nodes
        while(curL != null){
            currNum = curL.val + carry;
            curL.val = currNum % 10;
            carry = currNum / 10;
            last = curL;
            curL = curL.next;
        }
        // 3rd, neither long and short list have nodes
        // if carry is 1, and that node to the end of the list
        if(carry != 0){
            last.next = new ListNode(1);
        }
        return l;
    }

    public static int getLength(ListNode node){
        int len = 0;
        while(node != null){
            len++;
            node = node.next;
        }
        return len;
    }
}
