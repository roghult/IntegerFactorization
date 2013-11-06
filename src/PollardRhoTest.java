import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class PollardRhoTest {

	@Test
	public void testAllFactorsArePrime() throws Exception {
		PollardRho factorizer = new PollardRho();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			BigInteger n = new BigInteger(PollardRho.MAX_BIT_LENGTH, random);
			ArrayList<BigInteger> factors = factorizer.getFactors(n);
			for (int j = 0; j < factors.size(); j++) {
				Assert.assertTrue(factors.get(j).isProbablePrime(50));
			}
		}
	}

	@Test
	public void testFactorsAreCorrect() throws Exception {
		PollardRho factorizer = new PollardRho();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			BigInteger n = new BigInteger(PollardRho.MAX_BIT_LENGTH, random);
			ArrayList<BigInteger> factors = factorizer.getFactors(n);
			BigInteger product = BigInteger.valueOf(1);
			for (int j = 0; j < factors.size(); j++) {
				product = product.multiply(factors.get(j));
			}
			Assert.assertEquals(n, product);
		}
	}

}
