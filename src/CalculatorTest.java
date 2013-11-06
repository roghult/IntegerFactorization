import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFactorBaseAllPrime() {
		for (int i = 0; i < 100; i++) {
			long val = new Random().nextLong();
			if(val < 0) {
				val = -val;
			}
			BigInteger bi = BigInteger.valueOf(val);
			ArrayList<BigInteger> factorBase = Calculator.getFactorBase(bi);
			for (BigInteger factor : factorBase) {
				Assert.assertTrue(factor.isProbablePrime(50));
			}
		}
	}

}
