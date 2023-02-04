package MergeSort;

public class DescendingPairs {

    public static int getDescendingPairs(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return process(arr, 0, arr.length-1);
    }

    public static int process(int[] arr, int L, int R){
        if(L == R){
            return 0;
        }
        int M = L + ((R-L)>>1);
        return process(arr, L, M) + process(arr, M+1, R) + merge(arr, L, M, R);
    }


    public static int merge(int[] arr, int L, int M, int R){
        int[] help = new int[R-L+1];
        int i = help.length-1;
        int p1 = M;
        int p2 = R;
        int res = 0;
        while(p1>=L && p2>M){
            res += arr[p1]>arr[p2] ? (p2-M) : 0;
            //help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
            help[i--] = arr[p1]<arr[p2] ? arr[p2--] : arr[p1--];
        }
        while(p1>=L){
            help[i--] = arr[p1--];
        }
        while(p2>M){
            help[i--] = arr[p2--];
        }
        for(i=0; i<help.length; i++){
            arr[L+i] = help[i];
        }
        return res;
    }

    // for Test
    public static void main(String[] args) {
        int[] arr = {3,1,1,1};
        System.out.println(getDescendingPairs(arr));
    }

}
