package algorithms;

/*
 * utilizes a polynomial function f(x) = x^2 + 1
 * the idea is if apllied the function many times, two values will be the same, and the difference between them will be a factor of n.
 * this is based on the birthday paradox, that says that in a group of 23 people, there is a 50% chance that two people have the same birthday.
 * in this case, we are looking for two values that are the same when applied the function many times.
 * does not work for numbes with big prime factors.
 */
public class PollardRho {

	public static long findFactor(long n) {
		// If n is even, 2 is a factor
		if (n % 2 == 0)
			return 2;

		// If n is 1, return 1
		if (n == 1)
			return 1;

		// Start with x = 2 and y = 2. It will 'walk' like the tortoise and the hare.
		long x = 2;
		long y = 2;
		long d = 1; // this will be the MDC between x-y and n.

		// Keep trying until we find a factor
		while (d == 1) {
			// Move x one step like the tortoise
			x = (x * x + 1) % n;

			// Move y two steps like the hare
			y = (y * y + 1) % n;
			y = (y * y + 1) % n;
			// both follows the polynomial function f(x) = x^2 + 1 mod N
			// the idea is to detect a cycle in the sequence.

			// Calculate the difference
			long diff = Math.abs(x - y);

			// Find the greatest common divisor MDC
			d = mdc(diff, n);
		}

		return d;
	}

	/**
	 * Simple MDC calculation
	 */
	private static long mdc(long a, long b) {
		while (b != 0) {
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	/**
	 * Check if a number is prime
	 * this is a simple version that jumps all multiples of 2 and 3
	 */
	private static boolean isPrime(long n) {
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;

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
		printFactors(15); // 15 = 3 × 5
		printFactors(21); // 21 = 3 × 7
		printFactors(35); // 35 = 5 × 7
		printFactors(49); // 49 = 7 × 7

		// Test with a slightly larger number
		System.out.println("\nTesting with a larger number:");
		printFactors(323); // 323 = 17 × 19
	}
}