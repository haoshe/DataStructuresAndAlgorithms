package BitwiseCalculation;

// there is only one element in an array which appears odd times, find that number
public class FindTheOddOne {
    public static void main(String[] args) {
        int[] arr = {4,3,3,5,4,8,3,8,5};
        int eor = 0;
        for(int e : arr){
            eor ^= e;
        }
        System.out.println(eor);
    }
}
