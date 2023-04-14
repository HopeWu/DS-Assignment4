package hashingAlgorithms;

public class SimpleHash implements HashingAlgorithm {
	
	/**
	 * Generates the hash key for the given transaction id.
	 * 
	 * @param transactionId - id from the dataset
	 * @return hash code for the given transactionId
	 */
	@Override
	public long hash(String transactionId) {
		if (transactionId == null || transactionId.length() == 0) return 0;	
		int length = String.valueOf(transactionId).length();
		
		// char to int conversion
		int[] newId = new int[length];
		for(int i = 0 ; i < length; i++) {
			newId[i] = transactionId.charAt(i);
		}
		
		int finalId = 0;
		for(int i : newId) {
		  finalId += i;
		}
				
		long hashId = finalId % length;
		return hashId;
	}
}