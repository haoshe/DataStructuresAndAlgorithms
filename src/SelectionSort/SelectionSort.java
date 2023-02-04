package SelectionSort;

import SortingMethodTester.Tester;

public class SelectionSort {

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 100;
        int testTime = 100000;
        for(int i=0; i<testTime; i++){
            int[] arr = Tester.generateRandomArray(maxLen, maxValue);
            int[] temp = Tester.copyArray(arr);
            selectionSort(arr);
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
            }
        }
    }

    public static void selectionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int n = arr.length;
        // 0 ~ n-1
        // 1 ~ n-1
        // 2 ~ n-1
        for(int i=0; i<n; i++){
            // 0 ~ n-1
            // 1 ~ n-1
            // 2 ~ n-1
            // i ~ n-1
            int minValueIndex = i;
            for(int j=i+1; j<n; j++){
                minValueIndex = arr[j]<arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
