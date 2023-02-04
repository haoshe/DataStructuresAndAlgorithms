package 两个有序链表合并;

public class MergeTwoLists {
      public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null || list2 == null){
            return list1 == null ? list2 : list1;
        }

        ListNode head = list1.val <= list2.val ? list1 : list2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == list1 ? list2 : list1;
        ListNode pre = head;

        while(cur1 != null && cur2 != null){
            if(cur1.val <= cur2.val){
                pre.next = cur1;
                cur1 = cur1.next;
            }else{
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }

}
