package storageEngines;

import dataset.Transaction;
import hashingAlgorithms.HashingAlgorithm;

public abstract class HashTable {
	
	/**
	 * The length of the underlying array(buckets).
	 */
	int length;
	
	/**
	 * The algorithm used for hashing the transaction id into the index of the array.
	 */
	HashingAlgorithm hashingAlgorithm; 
	
	/**
	 * like get in HashMap
	 */
	public Transaction get(String transactionId) {
		int bucketIndex = hash(transactionId);
		
		return retrieveFromBucket(bucketIndex);
	}
	
	/**
	 * Retrieve the transaction from the bucket of bucketIndex.
	 */
	protected abstract Transaction retrieveFromBucket(int bucketIndex);

	/**
	 * like put in HashMap
	 */
	public void put(String transactionId, Transaction tran) {
		int bucketIndex = hash(transactionId);
		putIntoBucket(bucketIndex, tran);
	}
	
	/**
	 * Put the transaction into the bucket of bucketIndex.
	 */
	protected abstract void putIntoBucket(int bucketIndex, Transaction tran);

	/**
	 * To check if a hashtable is empty
	 */
	abstract public  boolean isEmpty();
	
	/**
	 * To hash a transaction id into an index of a bucket.
	 * @param transactionId
	 * @return
	 */
	public int hash(String transactionId){
		return hashingAlgorithm.hash(transactionId) % this.length;
	}

}
