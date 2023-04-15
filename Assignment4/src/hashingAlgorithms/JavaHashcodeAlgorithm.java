package hashingAlgorithms;

/**
 * A hashing algorithm using the Java's object's hashcode function.
 * @author haopengwu
 *
 */
public class JavaHashcodeAlgorithm implements HashingAlgorithm{

	@Override
	public long hash(String transactionId) {
		if (transactionId == null || transactionId == "")
			return 0;
		return transactionId.hashCode();
	}
}
