package BubbleSort;

import SortingMethodTester.Tester;

public class BubbleSort {

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 100;
        int testTime = 100000;
        for(int i=0; i<testTime; i++){
            int[] arr = Tester.generateRandomArray(maxLen,maxValue);
            int[] temp = Tester.copyArray(arr);
            bubbleSort(arr);
            if(!Tester.isSorted(arr)){
                for(int e : temp){
                    System.out.print(e + " ");
                }
                System.out.println();
                for(int e : arr){
                    System.out.print(e + " ");
                }
                System.out.println();
                System.out.println("Fail!");
                break;
            }
        }
    }

    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int n = arr.length;
        for(int i=n-1; i>=0; i--){
            boolean swapped = false;
            for(int j=0; j<i; j++){
                if(arr[j]>arr[j+1]){
                    swap(arr, j, j+1);
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
        // arr[i] and arr[j] can't point to the same address in the memory
        // otherwise the result will be zero
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
