
public class PollardRho {
    /**
     * Simple implementation of Pollard's Rho algorithm
     * This version is easier to understand and explain
     */
    public static long findFactor(long n) {
        // If n is even, 2 is a factor
        if (n % 2 == 0) return 2;
        
        // If n is 1, return 1
        if (n == 1) return 1;
        
        // Start with x = 2
        long x = 2;
        long y = 2;
        long d = 1;
        
        // Keep trying until we find a factor
        while (d == 1) {
            // Move x one step
            x = (x * x + 1) % n;
            
            // Move y two steps
            y = (y * y + 1) % n;
            y = (y * y + 1) % n;
            
            // Calculate the difference
            long diff = Math.abs(x - y);
            
            // Find the greatest common divisor
            d = gcd(diff, n);
        }
        
        return d;
    }
    
    /**
     * Simple GCD calculation
     */
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    /**
     * Check if a number is prime (simple version)
     */
    private static boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        // Check divisibility by numbers up to square root of n
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Find all prime factors of a number
     */
    public static void printFactors(long n) {
        System.out.print(n + " = ");
        
        // Keep finding factors until n becomes 1
        while (n > 1) {
            if (isPrime(n)) {
                System.out.print(n);
                break;
            }
            
            long factor = findFactor(n);
            System.out.print(factor);
            n = n / factor;
            
            if (n > 1) {
                System.out.print(" × ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Test with some simple numbers
        System.out.println("Testing Pollard's Rho with simple numbers:");
        printFactors(15);  // 15 = 3 × 5
        printFactors(21);  // 21 = 3 × 7
        printFactors(35);  // 35 = 5 × 7
        printFactors(49);  // 49 = 7 × 7
        
        // Test with a slightly larger number
        System.out.println("\nTesting with a larger number:");
        printFactors(323); // 323 = 17 × 19
    }
} 