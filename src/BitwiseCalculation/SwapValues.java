package BitwiseCalculation;

// swap two values without using any extra variable
public class SwapValues {
    public static void main(String[] args) {
        int a = 17;
        int b = 6;
        swapValues(a,b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
    public static void swapValues(int a, int b){

    }
}
