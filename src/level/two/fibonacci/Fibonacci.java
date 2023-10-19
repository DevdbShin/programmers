package level.two.fibonacci;

public class Fibonacci {

    public static void main(String[] args) {

        Fibonacci f = new Fibonacci();
        System.out.println(f.solution(8));
    }

    public int solution(int n) {
        int [] arr = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                arr[i] = 0;
            } else if (i == 1) {
                arr[i] = 1;
            } else {
                arr[i] = (arr[i - 1] + arr[i - 2]) % 1234567;
            }
        }

        return arr[n];
        //return getFibonacci(n, arr);
    }

    public int getFibonacci(int n, int[] arr) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            arr[n] = getFibonacci(n - 1, arr) + getFibonacci(n - 2, arr) % 1234567;
            return arr[n];
        }
    }
}
