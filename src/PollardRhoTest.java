import java.math.BigInteger;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PollardRhoTest {

	@Test
	public void testAllFactorsArePrime() throws Exception {
		PollardRho factorizer = new PollardRho();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			BigInteger n = new BigInteger(100, random);
			BigInteger[] factors = factorizer.getFactors(n);
			for (int j = 0; j < factors.length; j++) {
				Assert.assertTrue(factors[j].isProbablePrime(50));
			}
		}
	}
	
	@Test
	public void testFactorsAreCorrect() throws Exception {
		PollardRho factorizer = new PollardRho();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			BigInteger n = new BigInteger(100, random);
			BigInteger[] factors = factorizer.getFactors(n);
			BigInteger product = BigInteger.valueOf(1);
			for (int j = 0; j < factors.length; j++) {
				product = product.multiply(factors[j]);
			}
			Assert.assertEquals(n, product);
		}
	}

}
