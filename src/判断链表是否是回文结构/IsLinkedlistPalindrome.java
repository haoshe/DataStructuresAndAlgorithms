package 判断链表是否是回文结构;

import java.util.List;
import java.util.Stack;

/*
* ::给定一个单链表的头节点head,请判断该链表是否为回文结构。::
1)哈希表方法特别简单(笔试用)

2)改原链表的方法就需要注意边界了( 面试用)

利用栈,先遍历一遍压栈,然后在遍历一遍,同时与栈顶元素对比

用更少的空间?

快慢指针求出中点,和上中点

将后半部分压栈,再次出栈与前半部分对比

常数空间?

先快慢指针定位中点和末尾,将中点的next指针指向null

将后半部分指针逆序,然后头尾依次对比,当有一个的指针指向为null,即为回文结构

中间有一个不一样则为非回文

注意返回结果前,需要把后半部分逆序回来.
————————————————
版权声明：本文为CSDN博主「尔等同学」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_41852212/article/details/120795238
* */
public class IsLinkedlistPalindrome {

    public static class ListNode{
        int val;
        ListNode next;
    }

    // 使用容器
    public static boolean isPalindrome(ListNode head){
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while(head != null){
            if(head.val != stack.pop().val){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // O(1) extra space
    public static boolean isPalindrome2(ListNode head){
        // find mid point if list size is odd, upper mid if list size is even
        if(head == null || head.next == null){
            return true;
        }
        ListNode n1 = head;
        ListNode n2 = head;
        while(n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // n1 will be the middle point
        n2 = n1.next;
        n1.next = null;
        ListNode n3 = null;
        // reverse the right part
        while(n2 != null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        } // when while loop finishes, both n2 and n3 will be null, n1 will be the last element of the list
        n3 = n1;
        n2 = head;
        boolean res = true;
        //
        while(n1 != null && n2 != null){
            if(n1.val != n2.val){
                // remember the result but don't return because we need to recover the right part
                res = false;
                break;
            }
            n1 = n1.next;// left to mid
            n2 = n2.next;// right to mid
        }   // both n1 and n2 will be null when while loop finishes

        // n3 is the last element, so n1 will be the second last element(remember this part of list has been reversed)
        n1 = n3.next;
        // let n3.next be null, start changing the direction of the pointer
        n3.next = null;
        // recover right part
        while(n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

}
