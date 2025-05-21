package algorithms;
public class MillerRabin {
    /**
     * Performs the Miller-Rabin primality test
     * @param n The number to test
     * @param k The number of iterations (higher k = more accurate)
     * @return true if n is probably prime, false if n is definitely composite
     */
    public static boolean isPrime(long n, int k) {
        // Handle base cases
        if (n <= 1 || n == 4) return false;
        if (n <= 3) return true;
        
        // Find d such that n-1 = 2^r * d
        long d = n - 1;
        while (d % 2 == 0) {
            d /= 2;
        }
        
        // Perform k iterations of the test
        for (int i = 0; i < k; i++) {
            if (!millerTest(d, n)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Helper method for Miller-Rabin test
     * @param d The odd part of n-1
     * @param n The number being tested
     * @return true if n passes this iteration of the test
     */
    private static boolean millerTest(long d, long n) {
        // Pick a random number between 2 and n-2
        long a = 2 + (long)(Math.random() * (n - 4));
        
        // Compute a^d mod n
        long x = modPow(a, d, n);
        
        if (x == 1 || x == n - 1) {
            return true;
        }
        
        // Keep squaring x while one of the following doesn't happen:
        // (i) d does not reach n-1
        // (ii) (x^2) mod n is not 1
        // (iii) (x^2) mod n is not n-1
        while (d != n - 1) {
            x = (x * x) % n;
            d *= 2;
            
            if (x == 1) return false;
            if (x == n - 1) return true;
        }
        
        return false;
    }
    
    /**
     * Computes (base^exponent) % modulus efficiently
     * @param base The base number
     * @param exponent The exponent
     * @param modulus The modulus
     * @return (base^exponent) % modulus
     */
    private static long modPow(long base, long exponent, long modulus) {
        if (modulus == 1) return 0;
        
        long result = 1;
        base = base % modulus;
        
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent = exponent >> 1;
        }
        return result;
    }
    
    public static void main(String[] args) {
        // Test some numbers
        long[] testNumbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int k = 5; // Number of iterations
        
        System.out.println("Testing numbers with k = " + k + " iterations:");
        for (long n : testNumbers) {
            System.out.println(n + " is " + (isPrime(n, k) ? "probably prime" : "composite"));
        }
        
        // Test a larger number
        long largeNumber = 1000000007; // A known prime
        System.out.println("\nTesting a larger number:");
        System.out.println(largeNumber + " is " + (isPrime(largeNumber, k) ? "probably prime" : "composite"));
    }
} 