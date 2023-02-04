package MergeSort;

public class BiggerThanRightTwice {

    public static int biggerTwice(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return process(arr, 0, arr.length-1);
    }

    public static int process(int[] arr, int L, int R){
        if(L == R){
            return 0;
        }
        int M = L + ((R-L)>>2);
        return process(arr, 0, M) + process(arr, M+1, R) + merge(arr, L, M, R);
    }
    public static int merge(int[] arr, int L, int M, int R){

        // [L...M]  [M+1...R]
        int res = 0;
        // 目前囊括进来的数， 是从[M+1, windowR), 左闭右开， 小的编程技巧
        // 先设置windowR = M + 1， 因为是开区间， 所以现在一个数也没有
        int windowR = M+1;
        for(int i=0; i<=M; i++){// 左边组的数
            while(windowR <= R && arr[i] > (arr[windowR]<<1)){
                windowR++;
            }
            // 从loop里出来的windowR已经到了arr[windowR]*2 >= arr[i] 的位置
            res += windowR - M - 1; // windowR - (M+1)
        }

        int[] help = new int[R-L+1];
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
        for(i = 0; i < help.length; i++){
            arr[L+i] = help[i];
        }
        return res;
    }

    // for Test
    public static void main(String[] args) {
        int[] arr = {4, 1, 1, 6, 2, 2};
        System.out.println(biggerTwice(arr));
    }
}
