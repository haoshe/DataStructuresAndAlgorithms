package MergeSort;
/*
* 在一个数组中，一个数组左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。
举例：[1,3,4,2,5]
1左边比1小的数︰没有
3左边比3小的数:1
4左边比4小的数:1、3
2左边比2小的数:1
5左边比5小的数: 1、3、4、2
所以数组的小和为1+1+3+1+1+3+4+2=16
*
* 基本思路：

左组的数小于右组的数时，产生小和，左指针右移；
左组的数等于右组时，直接拷贝右组，不产生小和；
左组的数大于右组时，直接拷贝右移，不产生小和
*
* 小和产生的时候就是merge的时候
————————————————
版权声明：本文为CSDN博主「cutercorley」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/CUFEECR/article/details/123041542
* */
public class SmallSum {

    public static int smallSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return process(arr, 0, arr.length-1);
    }

    public static int process(int[] arr, int L, int R){
        if(L == R){
            return 0;
        }
        // ((R-L)>>1) has to be written this way
        int M = L + ((R - L)>>1);
        return process(arr,L,M) + process(arr, M+1, R) + merge(arr, L, M, R);
    }

    public static int merge(int[] arr, int L, int M, int R){

        // create a help array
        int[] help = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        while(p1<=M && p2<=R){
            res += arr[p2] > arr[p1] ? (R-p2+1)*arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1<=M){
            help[i++] = arr[p1++];
        }
        while(p2<=R){
            help[i++] = arr[p2++];
        }
        for(i=0; i<help.length; i++){
            arr[L+i] = help[i];
        }
        return res;
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // for test
    public static int smallSumTester(int[] arr){
        int res = 0;
        for(int i=1; i<arr.length; i++){
            for(int j=0; j<i; j++){
                res += arr[j]<arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int)(Math.random()*(maxSize+1))];
        for(int i=0; i<arr.length; i++){
            arr[i] = (int)(Math.random()*(maxValue+1)) - (int)(Math.random()*(maxValue+1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr){
        if(arr==null){
            return;
        }
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    // for test
    public static int[] copyArray(int[] arr){
        int[] copy = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            copy[i] = arr[i];
        }
        return copy;
    }

    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for(int i=0; i<testTime; i++){
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != smallSumTester(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fail!");
    }
}
