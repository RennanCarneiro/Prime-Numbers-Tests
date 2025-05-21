public class SieveOfEratosthenes {
    /**
     * Implements the Sieve of Eratosthenes algorithm to find all prime numbers up to n
     * @param n The upper limit to find prime numbers
     * @return boolean array where true indicates a prime number
     */
    public static boolean[] sieve(int n) {
        // Create array to store prime status
        boolean[] isPrime = new boolean[n + 1];
        
        // Initialize all numbers as prime
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        
        // Mark multiples of each prime as non-prime
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                // Mark all multiples of i as non-prime
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        return isPrime;
    }
    
    /**
     * Prints all prime numbers up to n
     * @param n The upper limit
     */
    public static void printPrimes(int n) {
        boolean[] primes = sieve(n);
        System.out.println("Prime numbers up to " + n + ":");
        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Test the implementation with n = 50
        printPrimes(50);
    }
}
