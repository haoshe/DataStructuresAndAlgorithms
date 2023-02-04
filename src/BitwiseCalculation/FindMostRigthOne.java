package BitwiseCalculation;

class FindMostRightOne {
    public static void main(String[] args) {
        int a = 7;
        int a2 = (~a+1);//-7
        System.out.println(a2);
        System.out.println(a&a2);// a & ((~a) + 1) => a & (-a)
    }
}
