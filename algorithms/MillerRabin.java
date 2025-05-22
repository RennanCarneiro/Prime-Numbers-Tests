package algorithms;

/*
 * the miller rabin algorithm will test if a number n could be prime, based on modular arithmetic's properties and factorization of n-1.
 *  n - 1 = 2^r * d, r = number of times 2 divides n-1. d is the odd part of n-1. a is a random number between 2 and n-2.
 * if a^d = 1 mod n or a^(2^i) * d = -1 mod n for 0 <= i < r, then n is prime. If not, n is composite and this means that n is a strong liar.
 */
public class MillerRabin {
    
    public static boolean isPrime(long n, int k) {
        // Handle base cases
        if (n <= 1 || n == 4) return false; // n<1 not prime. 
        //n == 4 can fool the algorithm because 4 - 1  = 3, and a = 2, so 2^3 = 8, and 8 mod 4 = 0 making the algorithm think that 4 is prime.
        if (n <= 3) return true; 
        //miller rabin doesnt work really well with small numbers so we can just make things faster and avoiding false results by returning true for 2 and 3.

        // here we factorize n-1 into 2^r * d, where d is odd.
        //this needs to be done because the whole algorithm is based on this factorization to verify if n is prime in power of a^d 
        //for ex: n=17, 17-1 = 16 = 2^4 * 1, so r=4 and d=1.
        long d = n - 1;
        while (d % 2 == 0) {
            d /= 2;
        }
        
        // Perform k iterations of the test
        //if any of the k tests returns false, then n is composite.
        //if all k tests returns true, then n is probably prime.
        for (int i = 0; i < k; i++) { 
            if (!millerTest(d, n)) {// !millerTest is a function that returns false if n is composite and true if n is prime.
                return false;
            }
        }
        return true;
    }
    
    
    private static boolean millerTest(long d, long n) { //miller will test how n behaves with different values of a.
        // Pick a random number between 2 and n-4
        long a = 2 + (long)(Math.random() * (n - 4));
        // long a = (long)(Math.random() * (n - 2)); IS WRONG because it could be 0 or 1 and exclude the n-2 that is one of the valid limits.
        //this comes from the little fermat's theorem, that says that if n is prime, then a^(n-1) = 1 mod n for all a coprime to n.
        long x = modPow(a, d, n);
        // Compute a^d mod n
        //if x = 1 or x = n-1 mod n, then n passes the test and is probably prime.
        if (x == 1 || x == n - 1) { 
        // x==1 means a^d = 1 mod n, this is acceptable and n could be a prime. x == n-1 means a^d = -1 mod n, this is also acceptable and n could be a prime.
        //anything else means that we need to keep elevating x to the power of 2 until we find 1 or n-1.
        //if any of the following happens, then n is composite.
            return true;
        }
        
        while (d != n - 1) { // this is the loop that will verify if any value type of x = a^2^i * d mod n is n-1.	
            x = (x * x) % n; //it will elevate x with every step. this is like x = a^(2^i) * d mod n
            d *= 2; //this update the value of d to be used in the next iteration. this is like  2^r * d = n-1
            if (x == 1) return false;//this means that a^2^i * d = 1 mod n, so n is composite.
            if (x == n - 1) return true;//this means that a^2^i * d = -1 mod n, so n is prime.
        }
        //if the loop ends and we didnt find 1 or n-1, then n is composite.
        return false;
    }
    
   
    private static long modPow(long base, long exponent, long modulus) {
        //instead of using base^exponent mod modulus, we will use a more efficient method to avoid overflow and speed up the computation.
        //this method is called modular exponentiation and it is a method that allows us to compute the result of a^b mod c efficiently.
        if (modulus == 1) return 0;//per definition any number mod 1 is 0. this evades any div by 0.
        
        long result = 1;
        base %= modulus;
        
        while (exponent > 0) {// the idea here is any exponent can be written as a sum of powers of 2. 
            if (exponent % 2 == 1) {// if the exponent is odd then the least significant bit is 1.
                result = (result * base) % modulus; //we will multiply the result by the base and take the modulus of the result.
            }
            base = (base * base) % modulus;//we will precompute the base for the next iteration.
            exponent >>= 1; //this is like exponent = exponent / 2. this is used to "shift" the exponent right to left decreasing the exponent with every iteration.
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