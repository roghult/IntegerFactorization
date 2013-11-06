import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		IntegerFactorizer factorizer = new PollardRho();
		while (br.ready()) {
			line = br.readLine();
			BigInteger bi = new BigInteger(line);
			ArrayList<BigInteger> factors;
			try {
				factors = factorizer.getFactors(bi);
				for (BigInteger factor : factors) {
					System.out.println(factor.toString());
				}
			} catch (Exception e) {
				System.out.println("fail");
			}
			System.out.println();
		}
	}

}