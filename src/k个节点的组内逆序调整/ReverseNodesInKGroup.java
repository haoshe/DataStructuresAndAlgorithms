package k个节点的组内逆序调整;

public class ReverseNodesInKGroup {

    public static class ListNode{
        public int val;
        public ListNode next;
    }

    public static ListNode reverseKGroup(ListNode head, int k){
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if(end == null){
            return head;
        }
        head = end;
        reverse(start, end);
        ListNode lastEnd = start;
        while(lastEnd.next != null){
            start = lastEnd.next;
            end = getKGroupEnd(start,k);
            if(end == null){
                return head;
            }
            reverse(start, end);
            // although end in reverse function has become end.next
            // end in this statement is still at the place before reverse function
            // which is still the last element of the k group
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    public static ListNode getKGroupEnd(ListNode start, int k){
        while(--k != 0 && start != null){
            start = start.next;
        }
        return start;
    }

    public static void reverse(ListNode start, ListNode end){
        end = end.next;
        ListNode pre = null;
        ListNode next = null;
        ListNode curr = start;
        while(curr != end){
            // next point to the ListNode after current ListNode
            next = curr.next;
            // current listNode points to null
            curr.next = pre;
            // pre points to current ListNode
            pre = curr;
            // current ListNode points to its next ListNode
            curr = next;
        }
        start.next = end;
    }
}
