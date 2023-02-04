package 链表快慢指针;
/*
* 笔试可以不在意空间复杂度，一切为了时间复杂度

面试时，时间复杂度最重要，但要节省空间

::快慢指针::

1)输入链表头节点，奇数长度返回中点，偶数长度返回上中点

2)输入链表头节点，奇数长度返回中点，偶数长度返回下中点

3)输入链表头节点，奇数长度返回中点前一个，偶数长度返回.上中点前一个

4)输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
————————————————
版权声明：本文为CSDN博主「尔等同学」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_41852212/article/details/120795238
* */
public class SlowAndFastPointers {

    public static class Node{
        public int val;
        Node next;
    }

    // 1)输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public static Node midOrUpperMidNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midOrUpperMidNode2(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public static Node midOrLowerMidNode(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //3)输入链表头节点，奇数长度返回中点前一个，偶数长度返回.上中点前一个
    public static Node preMidOrPreUpperMidNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 4)输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node preMidOrPreLowerMidNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
