package TreesAlgorithm;

import java.util.*;

/*
* TreesAlgorithm
二叉树的先序、中序、后序遍历

先序:任何子树的处理顺序都是，先头节点、再左子树、然后右子树

中序:任何子树的处理顺序都是，先左子树、再头节点、然后右子树

后序:任何子树的处理顺序都是，先左子树、再右子树、然后头节点

递归序
每个结点都会经过三次

前序，第一到达打印

中序，第二次

后序，第三次
————————————————
版权声明：本文为CSDN博主「尔等同学」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_41852212/article/details/120919232
* */
public class Trees{
    private static class Node<V>{
        V val;
        Node left;
        Node right;
        public Node(V val){
            this.val = val;
        }
    }
    // recursive
    public static void preRecursive(Node head){
        if(head == null){
            return;
        }
        System.out.print(head.val + " ");
        preRecursive(head.left);
        preRecursive(head.right);
    }

    public static void inRecursive(Node head){
        if(head == null){
            return;
        }
        inRecursive(head.left);
        System.out.print(head.val + " ");
        inRecursive(head.right);
    }

    public static void postRecursive(Node head){
        if(head == null){
            return;
        }
        postRecursive(head.left);
        postRecursive(head.right);
        System.out.print(head.val + " ");
    }

    public static void f(Node head){
        if(head == null){
            return;
        }
        // 1
        f(head.left);
        // 2
        f(head.right);
        // 3
    }
//    头序遍历也是二叉树的深度遍历 Depth First Search
//    非递归遍历
//    前序遍历
//    准备一个栈，
//    1.第一步将非空根结点压栈
//    出栈并打印
//    2.先将非空右子树结点压栈
//    3.后将非空左子树结点压栈
//    出栈并打印
//    重复2，3
      public static void pre(Node head){
          System.out.println("pre-order: ");
          if(head != null){
              Stack<Node> stack = new Stack<>();
              // stack.push() return the object you are pushing
              // stack.add() always return true.
              // other than return type, they are the same, but push method for stack is recommended
              stack.add(head);
              while(!stack.isEmpty()){
                  head = stack.pop();
                  System.out.print(head.val + " ");
                  if(head.right != null){
                      stack.add(head.right);
                  }
                  if(head.left != null){
                      stack.add(head.left);
                  }
              }
          }
          System.out.println();
      }

//    这样打印出来是 头 左右
//    如果先压入左孩子结点
//    打印顺序就是 头 右 左
//    如果不打印，而是把它放进另一个栈中，那么出来的顺序则是反的
//    逆序之后即为，左右头，即后序遍历的顺序
//    后序非递归：
      public static void pos(Node head){
          System.out.println("pos-order: ");
          if(head != null){
              Stack<Node> stack1 = new Stack<>();
              Stack<Node> stack2 = new Stack<>();
              stack1.push(head);
              while(!stack1.isEmpty()){
                  head = stack1.pop();
                  stack2.push(head);
                  if(head.left != null){
                      stack1.push(head.left);
                  }
                  if(head.right != null){
                      stack1.push(head.right);
                  }
              }
              while(!stack2.isEmpty()){
                  System.out.print(stack2.pop().val + " ");
              }
          }
          System.out.println();
      }

      // 只用一个栈实现后序的打印方法
    public static void posUsingOneStack(Node head){
        System.out.println("pos-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            Node c = null;
            while(!stack.isEmpty()){
                c = stack.peek();
                if(c.left != null && head != c.left && head != c.right){
                    stack.push(c.left);
                }else if(c.right != null && head != c.right){
                    stack.push(c.right);
                }else{
                    System.out.print(stack.pop().val + " ");
                    head = c;
                }
            }
        }
        System.out.println();
    }

//    中序非递归
//    1、先依次压入左边的结点，直到最左结点为空
//    2、弹出并打印，如果有右子数，压入栈中
//    继续执行1
    public static void in(Node head){
        System.out.println("in-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            while(!stack.isEmpty() || head != null){
                if(head != null){
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.print(head.val + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    // 二叉树的宽度遍历
    public static void breathFirstSearch(Node head){
        if(head == null){
            return;
        }
        // in Java, Queues are implemented by LinkedList
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.print(cur.val + " ");
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    // 得到横向最多的节点个数(求二叉树最大宽度), 此方法也可以求最大深度
    public static int getMaxNodesNumber(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // using hashmap as a container to mark each node and its current level
        HashMap<Node, Integer> levelMap = new HashMap<>();
        // head node is at level 1
        levelMap.put(head, 1);
        // current level
        int curLevel = 1;
        // number of nodes at current level
        // we start calculating after we poll out each node from the queue
        // that's why we initialize it zero, because no node has been polled from the queue yet
        int curLevelNodes = 0;
        // the max number of nodes at whichever level
        int max = Integer.MIN_VALUE;
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            // get the current level of this node
            int curNodeLevel = levelMap.get(cur);
            // if the current node's level is the same as the level I'm calculating
            if(curNodeLevel == curLevel){
                // increase number of current level nodes by 1
                curLevelNodes++;
            }else{
                // it's not the same level, meaning we can sum up the number of nodes at previous level
                // update max value
                max = Math.max(max, curLevelNodes);
                // then we need to set up current level values, because the previous level has finished
                curLevel++;
                // the polled node is already at next level, so we set curLevelNodes = 1
                curLevelNodes = 1;
            }
            if(cur.left != null){
                // mark the current level of the node before we put it in the queue
                // cur.left is the next level of current, because it is the left child of current node
                levelMap.put(cur.left, curNodeLevel+1);
                queue.add(cur.left);
            }
            if(cur.right != null){
                // curNodeLevel will still be 2, it won't be 3. because the curNodeLevel variable was used in a method
                // it's value only changes in this method, it won't affect the original global value
                levelMap.put(cur.right, curNodeLevel+1);
                queue.add(cur.right);
            }
        }
        // it doesn't sum up the nodes' number at the last level inside while loop
        // so we need to update max value after we finish while loop
        max = Math.max(max, curLevelNodes);
        return max;
    }

    // Test
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
//        head.left.left.left = new Node(8);
//        head.left.left.right = new Node(9);
//        head.left.right.left = new Node(10);
//        head.left.right.right = new Node(11);
//        head.right.left.left = new Node(12);
//        head.right.left.right = new Node(13);
//        head.right.right.left = new Node(14);
//        head.right.right.right = new Node(15);

 //      preRecursive(head);
//        System.out.println();
 //       inRecursive(head);
//        System.out.println();
//        postRecursive(head);
 //         breathFirstSearch(head);
        System.out.println(getMaxNodesNumber(head));
    }
}
