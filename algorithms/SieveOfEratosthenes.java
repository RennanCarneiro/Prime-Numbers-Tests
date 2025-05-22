package algorithms;

/*
 ideia central do codigo:
 1. começar com uma lista de todos os numeros de n até 2 e assumir que todos eles são primos.
 2. pegar o primeiro numero da lista que será 2 e eliminar todos os seus multiplos pois todos eles não serão primos.
 3. ir para o proximo numero não eliminado e eliminar todos os seus multiplos.
 4. repetir esse processo até que o numero atual seja maior que raiz de n.
 5. todos os numeros que não foram eliminados são primos.
 */
public class SieveOfEratosthenes {
	// recieve a number n as upper limit
	public static boolean[] sieve(int n) {

		// n+1 because its a need to include the number n itself or else it will not be
		// included and the last index will be n-1
		// here we build the sieve's tables itself. A list of all numbers from 2 to n
		// will be initially prime numbers.
		boolean[] isPrime = new boolean[n + 1]; // create a boolean array to store the prime status of each number
		for (int i = 2; i <= n; i++) { // Initialize all numbers as prime because, initially, we assume that all
										// numbers are prime!
			isPrime[i] = true;
		}

		// Mark multiples of each prime as non-prime
		for (int i = 2; i * i <= n; i++) { // for loop until square root of n.(I*I <= N) = math.sqrt(n), but calling the
											// square root is more expensive.
			if (isPrime[i]) {// if i is prime, then all its multiples are not prime.
				for (int j = i * i; j <= n; j += i) { // mark all multiples of i as non-prime.
					isPrime[j] = false; // marking as non-prime startng from I*I because all the numbers before are
										// already marked
				}
			}
		}

		return isPrime;
	}

	/**
	 * Prints all prime numbers up to n
	 * 
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
