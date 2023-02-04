package MergeSort;

import SortingMethodTester.Tester;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int maxLength = 10000;
        int range = 10000;
        // for some reason, testTime can't be a large number if using mergeSort recursively
        int testTimes = 10;
        for(int i=0; i<testTimes; i++){
            int[] arr = Tester.generateRandomArray(maxLength,range);
            int[] copy = Tester.copyArray(arr);
            mergeSort(arr,0,arr.length-1);
            if(!Tester.isSorted(arr)){
                System.out.println(Arrays.toString(copy));
                System.out.println("Fail!");
                System.out.println(Arrays.toString(arr));
                break;
            }
        }

//         int[] arr = randomArray(maxLength, range);
//        //int[] arr = {3,-1,0,-23};
//         //mergeSort(arr, 0, arr.length-1);
//        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
    }


    // mergeSort in a recursive way
    public static void mergeSort(int[] arr, int L, int R){
        if(L == R){
            return;
        }
        int M = L + ((R - L)>>1);
        mergeSort(arr, L, M);
        mergeSort(arr, M+1, R);
        merge(arr, L, M, R);
    }

    public static void merge(int[] arr, int L, int M, int R){
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while(p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= M){
            help[i++] = arr[p1++];
        }
        while(p2 <= R){
            help[i++] = arr[p2++];
        }
        for(i=0; i<help.length; i++){
            arr[L+i] = help[i];
        }
    }

    // mergeSort method in an iterative way
    public static void mergeSort2(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        // 步长: 1, 2, 4, 8, 16, ....
        int mergeSize = 1;
        while(mergeSize < N){
            // 当前左组的，第一个位置
            int L = 0;
            while(L < N){
                // 当前左组的最后一个位置
                int M = L + mergeSize -1;
                if(M >= N){
                    break;
                }
                // 右组的结束位置，如果右组的结束位置大于数组最后一个下标值，那么结束位置就是最后一个下标值
                int R = Math.min(M + mergeSize, N-1);
                // L ... M  M+1 .... R
                merge(arr, L, M, R);
                // 更新左组的第一个位置
                L = R + 1;
            }
            // 防止溢出, 当N接近Integer_MAX_VALUE时，如果mergeSize >= N/2, 那么下一个mergeSize * 2 就会变成负数
            if(mergeSize > N/2){
                break;
            }
            mergeSize <<= 1;
        }

    }

}


