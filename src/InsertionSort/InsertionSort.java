package InsertionSort;

import SortingMethodTester.Tester;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 1000;
        int testTime = 10000;

        for(int i=0; i<testTime; i++){
            int[] arr1 = Tester.generateRandomArray(maxSize, maxValue);
            int[] copy = Tester.copyArray(arr1);
            insertionSort(arr1);

            if(!Tester.isSorted(arr1)){
                for(int e : copy){
                    System.out.print(e + " ");
                }
                System.out.println();
                System.out.println("Fail!");
                break;
            }
        }


    }
    public static void insertionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        //  0 - 0 done
        // 0 - 1
        // 0 - 2
        // 0 - 3
        // 0 - n-1
        for(int i=1; i<arr.length; i++){// sort 0 - i
            for(int j=i-1; j>=0 && arr[j]>arr[j+1]; j--){// j+1 == i
                swap(arr, j, j+1);
            }
        }
    }

    private static void swap(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
