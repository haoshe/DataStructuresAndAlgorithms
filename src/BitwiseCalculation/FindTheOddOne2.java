package BitwiseCalculation;

// In an array, there are two numbers which appear odd times, the rest appear even times. Find these two numbers
public class FindTheOddOne2 {
    public static void main(String[] args) {
        int[] arr = {4,4,4,8,8,11,11,23,23,23};
        int eor = 0;
        for(int num : arr){
            eor ^= num;
        }
        // a and b are two different numbers
        // eor = a^b != 0
        // get the most right one of eor
        // eor:          00110010110111000
        // mostRightOne: 00000000000001000
        int mostRightOne = eor&(~eor+1);// get the most right 1
        int eor2 = 0;
        for(int num : arr){
            // num =           1111001111000
            // mostRightOne =  0000000001000
            if((num & mostRightOne) != 0){// only ^ numbers with the same position of the most right one
                eor2 ^= num;
            }
        }
        System.out.println(eor2 + " " + (eor^eor2));
    }
}
