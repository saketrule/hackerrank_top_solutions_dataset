import java.util.Scanner;

/**
 * @author <a href="mailto:alexander.shuyskov@odnoklassniki.ru">Alexander Shuiskov</a>
 */
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < cases; i++) {
            int n = Integer.parseInt(sc.nextLine());
            int countOfWays = getCountOfWays(n);
            int primesUpToN = getPrimeCountUpToN(countOfWays);
            System.out.println(primesUpToN);
        }
    }

    private static int getCountOfWays(int n) {
        int countOfWays = 1;
        int groups = n / 4;
        for (int i = 1; i <= groups; i++) {
            countOfWays += getBinomialCoefficient(n - i * 3, i);
        }
        return countOfWays;
    }

    public static int getBinomialCoefficient(int n, int k) {
        int numerator = 1;
        for (int i = n - k + 1; i <= n; i++) {
            numerator *= i;
        }
        int denominator = getFactorial(k);
        return numerator / denominator;
    }

    public static int getFactorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private static int getPrimeCountUpToN(int n) {
        if (n < 2) return 0;
        boolean[] sieve = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!sieve[i]) {
                for (int j = 2 * i; j <= n; j += i) {
                    sieve[j] = true;
                }
            }
        }
        int result = 1;
        for (int i = 3; i < sieve.length; i++) {
            if (!sieve[i]) result++;
        }
        return result;
    }
}
