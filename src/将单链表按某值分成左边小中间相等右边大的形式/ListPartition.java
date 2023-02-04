package 将单链表按某值分成左边小中间相等右边大的形式;

public class ListPartition {

    public static class Node{
        int val;
        Node next;
    }

    public static Node listPartition(Node head, int pivot){
        Node sHead = null; // 小于区域的头
        Node sTail = null; // 小于区域的尾
        Node eHead = null; // 等于区域的头
        Node eTail = null; // 等于区域的尾
        Node bHead = null; // 大于区域的头
        Node bTail = null; // 大于区域的尾
        Node next; // in order to detach each node, we need a variable to hole the next node
        // divide nodes into three categories
        while(head != null){
            next = head.next;
            head.next = null; // detach the node;
            if(head.val < pivot){
                if(sHead == null){
                    sHead = head;
                    sTail = head;
                }else{
                    sTail.next = head;
                    sTail = head;
                }
            }else if(head.val == pivot){
                if(eHead == null){
                    eHead = head;
                    eTail = head;
                }else{
                    eTail.next = head;
                    eTail = head;
                }
            }else{
                if(bHead == null){
                    bHead = head;
                    bTail = head;
                }else{
                    bTail.next = head;
                    bTail = head;
                }
            }
            head = next;
        }
        // 小于区域的尾巴，连接等于区域的头， 等于区域的尾巴，连接大于区域的头
        if(sTail != null){ // 如何有小于区域
            sTail.next = eHead;
            // 下一步，谁可以连大于区域的头，谁就是eTail
            // 如果有等于区域，eTail就是等于区域的尾节点
            // 如果没有等于区域，eTail就是小于区域的尾节点
            eTail = eTail == null ? sTail : eTail;
        }

        // eTail -- 尽量不为空的尾巴节点
        if(eTail != null){
            eTail.next = bHead;
        }
        return sHead != null ? sHead : (eHead != null ? eHead : bHead);
    }
}
