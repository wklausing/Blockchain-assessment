package blockchainTechnologies;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		String answer = calculateHash("Klausing");
		System.out.println("Answer: " + answer);// Klausing47921167 
	}

	public static String calculateHash(String nachname) {
		String answer;
		BigInteger baseline = getBaseline();
		BigInteger intHash;
		BigInteger nonce = new BigInteger("1");
		do {
			answer = nachname + nonce;
			String hashAnswer = getSha256(answer);
			intHash = new BigInteger(hashAnswer, 16);
			System.out.println("Tried answer: " + answer + " with resulting hash: " + hashAnswer);
			nonce = nonce.add(new BigInteger("1"));
		} while (baseline.compareTo(intHash) == -1);
		return answer;
	}

	/**
	 * 
	 * @return value needs to be higher or equal to my answer.
	 */
	public static BigInteger getBaseline() {
		BigInteger base = new BigInteger("2");
		int exponent = 228;
		BigInteger baseline = base.pow(exponent);
		baseline.subtract(new BigInteger("1"));
		System.out.println("Baseline: " + baseline);
		return baseline;
	}
	
	/**
	 * 
	 * @param value is tried answer.
	 * @return SHA value of answer
	 */
	public static String getSha256(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(value.getBytes());
			return bytesToHex(md.digest());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 
	 * @param bytes
	 * @return convert byte array into Hex.
	 */
	private static String bytesToHex(byte[] bytes) {
		StringBuffer result = new StringBuffer();
		for (byte b : bytes)
			result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		return result.toString();
	}

}
