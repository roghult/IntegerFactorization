import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	private static final int MAX_BIT_LENGTH = 76;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		IntegerFactorizer factorizer = new PollardRho();
		while (br.ready()) {
			line = br.readLine();
			BigInteger bi = new BigInteger(line);
			BigInteger[] factors;
			if (bi.bitLength() < MAX_BIT_LENGTH) {
				try {
					factors = factorizer.getFactors(bi);
					for (BigInteger factor : factors) {
						System.out.println(factor.toString());
					}
				} catch (Exception e) {
					System.out.println("fail");
				}
			} else {
				System.out.println("fail");
			}
			System.out.println();
		}
	}

}
