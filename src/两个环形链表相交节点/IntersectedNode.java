package 两个环形链表相交节点;
/*
* 返回相交的第一个节点
给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null。
[要求]
如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复
杂度请达到0(1)。
* */
public class IntersectedNode {

    private static class Node{
        int val;
        Node next;
    }
    // 找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node slow = head;
        Node fast = head;
        while(slow != fast){
            if(fast.next == null || fast.next.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
    // 两个链表都无环
    // 遍历第一条链表，记录最后一个节点， 且记录链表长度（X）
    // 遍历第二条链表，记录最后一个节点，且记录链表长度（Y）
    // x 与 y, 大的先走x-y步， 然后两条链表一起走，会在相交节点相遇
    public static Node noLoop(Node head1, Node head2){
        Node cur1 = head1;
        Node cur2 = head2;
        int len = 0;
        // 让链表1走到尾，并记录长度
        while(cur1.next != null){
            len++;
            cur1 = cur1.next;
        }
        // 让链表2走到尾，同时减去链表1的长度
        while(cur2.next != null){
            len--;
            cur2 = cur2.next;
        }
        // 如果长短链表的最后一个节点都不同，那么他们就不会相交
        if(cur1 != cur2){
            return null;
        }
        // len: 链表1长度减去链表2长度的值，如果为负，链表2长，反之链表1长
        cur1 = len > 0 ? head1 : head2; // 谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头变成cur2
        len = Math.abs(len);
        // 先让长链表走到和短链表同样的长度
        while(len != 0){
            len--;
            cur1 = cur1.next;
        }
        // 然后两个链表一起走，直到他们相交
        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
//    一个有环，一个无环，这种情况，两条链表不相交
//            两个都有环
//    如果两个有环链表相交，那他们一定是共用这个环
//            一共有三种情况
//    如果loop1 的内存地址等于loop2 的内存地址
//            则为第二种情况， 入环节点相同
//    如果loop 1 不等于 loop 2
//    让loop1 沿着环走，如果没有遇到 loop2 则为第一种情况
//            也就是没有相交
//    如果遇到了loop2 就是第三种情况，入环节点不同
//————————————————
//    版权声明：本文为CSDN博主「尔等同学」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/qq_41852212/article/details/120795238
    // 两个有环链表，返回第一个相交节点。如果没有返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        if(loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int len = 0;
            while(cur1.next != null){
                len++;
                cur1 = cur1.next;
            }
            while(cur2.next != null){
                len--;
                cur2 = cur2.next;
            }
            cur1 = len > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            Math.abs(len);
            while(len != 0){
                len--;
                cur1 = cur1.next;
            }
            while(cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else{ // loop1 != loop2
            cur1 = loop1.next;
            while(cur1 != loop1){
                if(cur1 == loop2){
                    return loop1; // or loop2
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    // main method
    public static Node getIntersectedNode(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if(loop1 == null || loop2 == null){
            return noLoop(head1, head2);
        }
        if(loop1 != null && loop2 != null){
            return bothLoop(head1, loop1, head2, loop2);
        }
        // 一个有环，一个没还，肯定不会相交
        return null;
    }
}
