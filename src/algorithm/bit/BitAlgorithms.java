package algorithm.bit;

/**
 * Bit manipulation tricks for DSA.
 */
public final class BitAlgorithms {

	private BitAlgorithms() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static int countSetBits(int n) {
		int count = 0;

		while (n > 0) {
			count += n & 1;
			n >>= 1;
		}

		return count;
	}

	public static boolean isPowerOfTwo(int n) {
		return n > 0 && (n & (n - 1)) == 0;
	}

	public static int singleNumber(int[] nums) {
		int xor = 0;

		for (int num : nums) {
			xor ^= num;
		}

		return xor;
	}

	public static int setBit(int n, int position) {
		return n | (1 << position);
	}

	public static int clearBit(int n, int position) {
		return n & ~(1 << position);
	}

	public static int toggleBit(int n, int position) {
		return n ^ (1 << position);
	}

	public static boolean isBitSet(int n, int position) {
		return (n & (1 << position)) != 0;
	}
}
