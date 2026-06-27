package algorithm.math;

/**
 * Math algorithms — gcd, sieve, fast power, prime check.
 */
public final class MathAlgorithms {

	private MathAlgorithms() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static int gcd(int a, int b) {
		a = Math.abs(a);
		b = Math.abs(b);

		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}

		return a;
	}

	public static int lcm(int a, int b) {
		return Math.abs(a / gcd(a, b) * b);
	}

	public static long fastPower(long base, long exp, long mod) {
		long result = 1;
		base %= mod;

		while (exp > 0) {
			if ((exp & 1) == 1) {
				result = (result * base) % mod;
			}
			base = (base * base) % mod;
			exp >>= 1;
		}

		return result;
	}

	public static boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		}
		if (n <= 3) {
			return true;
		}
		if (n % 2 == 0 || n % 3 == 0) {
			return false;
		}

		for (int i = 5; i * i <= n; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}

		return true;
	}

	public static boolean[] sieveOfEratosthenes(int n) {
		boolean[] isPrime = new boolean[n + 1];

		for (int i = 2; i <= n; i++) {
			isPrime[i] = true;
		}

		for (int p = 2; p * p <= n; p++) {
			if (isPrime[p]) {
				for (int i = p * p; i <= n; i += p) {
					isPrime[i] = false;
				}
			}
		}

		return isPrime;
	}
}
