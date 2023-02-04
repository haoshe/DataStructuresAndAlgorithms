package QuickSort;


import java.util.Arrays;
import java.util.Stack;

public class QuickSort {

    public static void swap(int[]arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // get the 1st index of equal area
    // it's for the 1st quickSort
    public static int partition(int[] arr, int L, int R){
        if(L > R){
            return -1;
        }
        if(L == R){
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while(index < R){
            if(arr[index] <= arr[R]){
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    // arr[L...R], 荷兰国旗问题的划分，以arr[R]做划分值
    // area < arr[R]  area == arr[R] area > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R){
        if(L > R){ // L...R L>R
            return new int[]{-1, -1};
        }
        if(L == R){
            return new int[]{L, R};
        }
        // at the start, there is an array, L is 0, R is array.length-1
        // we choose arr[R] as our partition number
        // so before everything start, we set up less at -1, more at array.length-1, index at 0

        int less = L - 1; // area < arr[R]'s right most index 右边界
        int more = R; // area > arr[R]'s  left most index 左边界
        int index = L; // area == arr[R]'s index
        while(index < more){ // 不能和 大于区域的左边界撞上
            //
            if(arr[index] == arr[R]){
                index++;
            }else if(arr[index] < arr[R]){
                // 交换arr[index]和最靠近左边界的数，然后小于边界往右移一位， 再index++
                swap(arr, less+1, index);
                less++;
                index++;
  //            swap(arr, ++less, index++);
            }else{ // > arr[R]
                // 交换arr[index]和最靠近大于边界的数，然后大于边界往左移一位， index不动
                // 为啥index不动？因为此时arr[index]是交换后的数，最靠近大于边界的数，还没被比较过
                swap(arr, index, --more);
            }
        }
        //把一直没动的arr[R]和大于边界的第一个数交换
        swap(arr, more, R);
        return new int[]{less+1, more};// area == arr[R]'s left and right index
    }



    public static void quickSort1(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R){
        if(L >= R){
            return;
        }
        // L...R partition arr[R] [<=arr[R] arr[R] >arr[R]]
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }



    //*************************************************************
    public static void quickSort2(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process2(arr, 0, arr.length-1);
    }

    public static void process2(int[] arr, int L, int R){
        if(L >= R){
            return;
        }
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    //*******************************************************************
    public static void quickSort3(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R){
        if(L >= R){
            return;
        }
        // random choose an element in the array and swap it with the right most element
        swap(arr, L + (int)(Math.random()*(R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

    //***************************************************************

    // quickSort in a non-recursive way using a stack
    // it needs a help class
    public static class Op{
        public int l;
        public int r;
        public Op(int left, int right){
            l = left;
            r = right;
        }
    }

    public static void quickSort4(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        swap(arr, (int)(Math.random()*N), N-1);
        int[] eqaulArea = netherlandsFlag(arr, 0 , N-1);
        int el = eqaulArea[0];
        int er = eqaulArea[1];
        Stack<Op> stack = new Stack<>();
        // push 小于区域的开始和结束的index
        stack.push(new Op(0, el-1));
        // push 大于区域的开始和结束的index
        stack.push(new Op(er+1, N-1));
        while(!stack.isEmpty()){
            Op op = stack.pop();
            if(op.l < op.r){
                swap(arr, op.l + (int)(Math.random()*(op.r-op.l+1)), op.r);
                eqaulArea = netherlandsFlag(arr, op.l, op.r);
                el = eqaulArea[0];
                er = eqaulArea[1];
                stack.push(new Op(op.l, el-1));
                stack.push(new Op(er+1, op.r));
            }
        }
    }

    // for test
    public static void main(String[] args) {
        int maxLength = 100;
        int maxValue = 100;
        int testTimes = 10000;
        for(int i=0; i<testTimes; i++){
            int[] arr = getRandomArray(maxLength, maxValue);
            int[] copyArr = copyOfArray(arr);
            quickSort4(arr);
            boolean isWorking = isAscending(arr);
            if(!isWorking){
                System.out.println("Fail!");
                break;
            }
        }
        System.out.println("success!");
    }

    // create a random length array contains random value numbers
    public static int[] getRandomArray(int maxLength, int maxValue){
        int[] arr = new int[(int)((maxLength+1)*Math.random())];
        for(int i=0; i<arr.length; i++){
            arr[i] = ((int)((maxValue+1)*Math.random()) - (int)((maxValue+1)*Math.random()));
        }
        return arr;
    }
    // create a copy of the random array
    public static int[] copyOfArray(int[] arr){
        int[] copy = new int[arr.length];
        for(int i=0; i<copy.length; i++){
            copy[i] = arr[i];
        }
        return copy;
    }

    public static boolean isAscending(int[] arr){
        if(arr.length < 2){
            return true;
        }
        int max = arr[0];
        for(int i=1; i<arr.length; i++){
            if(max > arr[i]){
                return false;
            }
            max = Math.max(max, arr[i]);
        }
        return true;
    }
}
