package CopyLinkedlistWithRandomPointer;

import java.util.HashMap;
import java.util.Map;

/*
* -种特殊的单链表节点类描述如下
class Node {
Int value.
Node next:
Node rand;
Node(int val){ value= val }
}
rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null.
给定一个由node节点类型组成的无环单链表的头节点head.请实现一个函数完成这个链表的复制 并返回复制的新链表的头节点。

[要求]
时间复杂度ON).额外空间复杂度O(1)
————————————————
版权声明：本文为CSDN博主「尔等同学」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_41852212/article/details/120795238
* */
class CopyLinkedlistWithSpecialPointer {

    public static class Node{
        int val;
        Node next;
        Node rand;
        public Node(int val){
            this.val = val;
        }
    }

//    建立map
//    第一遍遍历, key为老节点,value 为新的节点
//    第二遍遍历链表, 由老节点找到新的节点,并将rand指针赋值给新的节点
//    最后返回 新节点
     public static Node copyLinkedlistWithRandPointer1(Node head){
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while(cur != null){
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
     }

//    不用hash 表 ,实现拷贝
//    第一次遍历,建立新的节点插入到老节点的后方
//    第二次遍历设置rand指针,
//    如果1的rand指向 —> 3
//    则 3的拷贝节点就在 3后方
//    将 1的拷贝节点的rand 指针指向3 的后一个节点
//    最后再将拷贝的节点串联起来返回其头节点
    public static Node copyLinkedlistWithRandPointer2(Node head){
        if(head == null){
            return null;
        }
        // 1 -> 2 -> 3 -> null
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> null
        Node cur = head;
        Node next = null;
        while(cur != null){
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        // 把原节点的rand指针copy到新节点上
        cur = head;
        Node curCopy = null;
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        // split
        Node res = head.next;
        cur = head;
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyLinkedlistWithRandPointer1(head);
        printRandLinkedList(res1);
        res2 = copyLinkedlistWithRandPointer2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyLinkedlistWithRandPointer1(head);
        printRandLinkedList(res1);
        res2 = copyLinkedlistWithRandPointer2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
