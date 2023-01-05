public class Bai6 {
    public static void main(String[] args) {

        System.out.println("10 số đầu tiên của dãy số fibonacci: ");

        for (int i = 0; i < 10; i++) {

            System.out.print(fibonacci(i) + " ");

        }

    }

    public static int fibonacci(int n) {
        if (n < 0) return -1;
        return (n == 0 || n == 1) ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }
}
