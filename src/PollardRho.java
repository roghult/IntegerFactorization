import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class PollardRho implements IntegerFactorizer {

	private final static BigInteger ZERO = BigInteger.valueOf(0);
	private final static BigInteger ONE = BigInteger.valueOf(1);
	private final static BigInteger TWO = BigInteger.valueOf(2);
	private final static Random random = new Random();
	ArrayList<BigInteger> factors;

	@Override
	public BigInteger[] getFactors(BigInteger n) throws Exception {
		factors = new ArrayList<BigInteger>();
		factor(n);
		return factors.toArray(new BigInteger[factors.size()]);
	}

	public BigInteger rho(BigInteger n) {
		if (n.mod(TWO).compareTo(ZERO) == 0) {
			return TWO;
		}
		
		BigInteger divisor;
		BigInteger x = new BigInteger(n.bitLength(), random);
		BigInteger y = x;

		do {
			x = x.multiply(x).subtract(BigInteger.valueOf(1)).mod(n);
			y = y.multiply(y).subtract(BigInteger.valueOf(1)).mod(n);
			y = y.multiply(y).subtract(BigInteger.valueOf(1)).mod(n);
			divisor = x.subtract(y).gcd(n);
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
		BigInteger divisor = rho(n);
		if(divisor.equals(n)) {
			throw new Exception("Factorization failed");
		}
		factor(divisor);
		factor(n.divide(divisor));
	}
}
