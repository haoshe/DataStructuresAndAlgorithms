package SortingMethodTester;

public class Tester {


    public static int[] generateRandomArray(int maxSize, int maxValue){
       //Math.random() -> [0,1) generate a decimal between 0(inclusive) and 1(exclusive)
        //Math.random() * N -> [0,N) a decimal between 0 and N(exclusive)
        //(int)(Math.random()*N) -> [0,N-1] an integer between 0 and N-1(inclusive)
        int[] arr = new int[(int)(Math.random()*(maxSize+1))];//random length
        for(int i=0; i<arr.length; i++){
            arr[i] = (int)(Math.random()*(maxValue+1)) - (int)(Math.random()*(maxValue+1));
        }
        return arr;
    }

    public static int[] copyArray(int[] arr){
        int[] arrCopy = new int[arr.length];
        for(int i=0; i<arrCopy.length; i++){
            arrCopy[i] = arr[i];
        }
        return arrCopy;
    }

    // the length of arr1 and arr2 must be the same
    // check if the array has been sorted
    public static boolean isSorted(int[] arr){
        if(arr.length < 2){// if there is only one element, the array must be a sorted array
            return true;
        }
        int max = arr[0];
        for(int i=0; i<arr.length; i++){
            if(max > arr[i]){// if max is larger than the later element, the array is not sorted
                return false;
            }
            max = Math.max(max, arr[i]);// update max value
        }
        return true;
    }
}
