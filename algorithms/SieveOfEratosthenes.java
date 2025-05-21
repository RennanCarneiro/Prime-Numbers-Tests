package algorithms;

public class SieveOfEratosthenes {
    /**
     * Implements the Sieve of Eratosthenes algorithm to find all prime numbers up to n
     * @param n The upper limit to find prime numbers
     * @return boolean array where true indicates a prime number
     */
    // recieve a number n as upper limit
    public static boolean[] sieve(int n) {
        // create a boolean array to store the prime status of each number
        // n+1 because its a need to include the number n itself or else it will not be included and the last index will be n-1
        boolean[] isPrime = new boolean[n + 1];
        
        // Initialize all numbers as prime because, initially, we assume that all numbers are prime!
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        
        // here starts the sieve itself 
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
