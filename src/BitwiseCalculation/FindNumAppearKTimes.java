package BitwiseCalculation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// In an array, a number appears K times, the rest appear M times
// M > 1, K < M
// find the number appears K times
// Space complexity O(1), time complexity O(N)
public class FindNumAppearKTimes {
    public static void main(String[] args) {

        int numKinds = 8;
        int range = 10;
        int testTime = 10000;
        for(int i=0; i<testTime; i++){
            // set random k and m, k < m, they are both [1, 9]
            int a = (int)(Math.random()*9)+1;// [1,9]
            int b = (int)(Math.random()*9)+1;// [1,9]
            int k = Math.min(a, b);
            int m = Math.max(a,b);
            if(k == m){
                m++;
            }
            int[] arr = randomArray(numKinds,range,k,m);
            int ans1 = onlyKTimes(arr,k,m);
            int ans2 = test(arr,k,m);
            System.out.println(Arrays.toString(arr));
            System.out.println(ans1);
            System.out.println(ans2);
            if(ans1 != ans2){
                System.out.println("Fail!");
                break;
            }
        }

    }

    // find the number appears k times O(n)
    public static int onlyKTimes(int[] arr, int k, int m){
        //set up a temp arr with a length of 32
        int[] t = new int[32];
        // get all the numbers in arr, mapping its binary form into the temp arr
        // from 0 ~ 31, add up all the 1s in each position
        for(int num : arr){
            for(int i=0; i<32; i++){//still O(1), because it's a fixed 32 iterations loop
                // if ith digit is 1, add on to t[i]
                t[i] += (num>>i) & 1;
            }
        }
        int ans = 0;
        for(int i=0; i<32; i++) {
            if (t[i] % m == 0) {
                continue;
            }
            if (t[i] % m == k) {
                ans |= (1 << i);
            } else {
                return -1;
            }
        }
        // if 0 is the answer, we need to manually count the times it appears in the array, if it appears k times, return answer
        if(ans == 0){
            int count = 0;
            for(int num : arr){
                if(num == 0){
                    count++;
                }
            }
            if(count != k){
                return -1;
            }
        }

            // if ith bit is not 0, means the number appears k times has 1 at ith position
//            if(t[i]%m != 0){
//                // add 1 to ith position in ans
//                ans = ans | (1<<i);
//            }

        return ans;
    }

    // another way
    public static int test(int[] arr, int k, int m){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n : arr){
            if(map.containsKey(n)){
                map.put(n, map.get(n)+1);
            }else{
                map.put(n, 1);
            }
        }
        for(int n : map.keySet()){
            if(map.get(n) == k){
                return n;
            }
        }
        return -1;
    }

    //做对数器
    // create a method to generate a random number within the range( positive and negative)
    public static int generateRandomNumber(int range){
        return ((int)(Math.random()*range)+1) - ((int)(Math.random()*range)+1);
    }

    // create an array with random kinds of numbers, one number appears K times and the rest of numbers each appears m times
    public static int[] randomArray(int numKinds, int range, int k, int m){
        // the appearing times of the number we are looking for, instead of k, we are making it random
        // if random num < 0.5, times = k; if not, generate a random number smaller than m
        int times = Math.random() < 0.5 ? k : ((int)(Math.random()*(m-1))+1);

        // there should have more than 2 kinds of numbers
        numKinds = (int)(Math.random()*numKinds)+2;
        // calculate the length of the array
        // k * 1 + (numKinds-1) * m
        int[] arr = new int[times + (numKinds-1) * m];
        // generate a number appears k times
        int kNum = generateRandomNumber(range);
        // put kNum into the array
        int index = 0;
        for(;index < times; index++){
            arr[index] = kNum;
        }
        // because we have put 1 kind of num into the array, so we should decrease numKinds by 1
        numKinds--;
        //put the rest into the array
        // create a hashset
        Set<Integer> set = new HashSet<>();
        // put kNum in it first
        set.add(kNum);
        while(numKinds != 0){
            int curNum = 0;
            do{
                curNum = generateRandomNumber(range);
            }while(set.contains(curNum));//if set contains curNum, do it again
            set.add(curNum);
            // add curNum to arr m times, starts from last index position + 1
            for(int i=0; i<m; i++){
                arr[index++] = curNum;
            }
            // decrease numKinds by 1
            numKinds--;
        }

        // mix up the elements in the array
        for(int i=0; i<arr.length; i++){
            int j = (int)(Math.random()*arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
}
