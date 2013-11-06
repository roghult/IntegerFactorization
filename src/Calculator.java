import java.math.BigInteger;
import java.util.ArrayList;

public class Calculator {

	private Calculator() {
	}

	public static ArrayList<BigInteger> getFactorBase(BigInteger n) {
		int factorBaseSize = getFactorBaseSize(n);

		ArrayList<BigInteger> factorBase = new ArrayList<BigInteger>(
				factorBaseSize);
		int primesFound = 0;
		BigInteger bigInteger = BigInteger.valueOf(2);

		while (primesFound < factorBaseSize) {
			if (isPrime(bigInteger) && getLegendreSymbol(n, bigInteger) == 1) {
				factorBase.add(bigInteger);
				primesFound++;
			}

			bigInteger = bigInteger.add(BigInteger.ONE);
		}

		return factorBase;
	}

	public static int getFactorBaseSize(BigInteger bi) {
		int log2N = bi.bitLength();
		double lnN = log2N * Math.log(2.0);
		double lnlnN = Math.log(lnN);
		double base = Math.exp(Math.sqrt(lnN * lnlnN));
		double exponent = Math.sqrt(2) / 4;
		double size = Math.pow(base, exponent);

		return (int) Math.ceil(size);
	}

	private static boolean isPrime(BigInteger bi) {
		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(bi) < 0; i = i
				.add(BigInteger.ONE)) {
			if (bi.remainder(i).equals(BigInteger.ZERO)) {
				return false;
			}
		}

		return true;
	}

	private static int getLegendreSymbol(BigInteger bi, BigInteger p) {
		if (bi.remainder(p).equals(BigInteger.ZERO)) {
			return 0;
		}
		BigInteger exponent = p.subtract(BigInteger.ONE);
		exponent = exponent.divide(BigInteger.valueOf(2));
		BigInteger result = bi.modPow(exponent, p);
		if (result.equals(BigInteger.ONE)) {
			return 1;
		} else if (result.equals(p.subtract(BigInteger.ONE))) {
			return -1;
		}
		return -2;
	}
}
