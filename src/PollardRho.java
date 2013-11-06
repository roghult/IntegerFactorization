import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class PollardRho implements IntegerFactorizer {

	public static final int MAX_BIT_LENGTH = 78;
	private final static int LOOPS_BEFORE_GCD = 80;
	private final static BigInteger ZERO = BigInteger.valueOf(0);
	private final static BigInteger ONE = BigInteger.valueOf(1);
	private final static Random RANDOM = new Random();
	ArrayList<BigInteger> factors;

	@Override
	public ArrayList<BigInteger> getFactors(BigInteger n) throws Exception {
		factors = new ArrayList<BigInteger>();
		factor(n);
		return factors;
	}

	public BigInteger rho(BigInteger n) {
		BigInteger divisor;
		BigInteger x = new BigInteger(n.bitLength(), RANDOM);
		BigInteger y = x;
		BigInteger z = ONE;
		do {
			for (int i = 0; i < LOOPS_BEFORE_GCD; i++) {
				x = x.multiply(x).subtract(BigInteger.valueOf(1)).mod(n);
				y = y.multiply(y).subtract(BigInteger.valueOf(1)).mod(n);
				y = y.multiply(y).subtract(BigInteger.valueOf(1)).mod(n);
				z = z.multiply(x.subtract(y)).mod(n);
			}
			divisor = z.gcd(n);
		} while ((divisor.compareTo(ONE)) == 0);

		return divisor;
	}

	public void factor(BigInteger n) throws Exception {
		if (n.compareTo(ONE) == 0) {
			return;
		}
		if (n.isProbablePrime(10)) {
			factors.add(n);
			return;
		}
		BigInteger divisor = null;
		boolean divisorFound = false;
		for (int i = 0; i < KnownPrimes.knownPrimes.length; i++) {
			if (KnownPrimes.knownPrimes[i].compareTo(n) > 0) {
				break;
			}
			if (n.mod(KnownPrimes.knownPrimes[i]).compareTo(ZERO) == 0) {
				divisor = KnownPrimes.knownPrimes[i];
				divisorFound = true;
				break;
			}
		}
		if (!divisorFound) {
			if (n.bitLength() > MAX_BIT_LENGTH) {
				throw new Exception("Number too big, skip.");
			}
			divisor = rho(n);
		}
		if (divisor.equals(n)) {
			throw new Exception("Factorization failed");
		}
		factor(divisor);
		factor(n.divide(divisor));
	}
}
