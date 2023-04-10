package hashingAlgorithms;

public interface HashingAlgorithm {
	/**
	 * To hash a transaction id into an index of a bucket.
	 * @param transactionId
	 * @return
	 */
	public long hash(String transactionId);
}
