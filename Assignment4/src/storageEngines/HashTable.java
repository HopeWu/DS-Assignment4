package storageEngines;

import dataset.Transaction;
import hashingAlgorithms.HashingAlgorithm;

public abstract class HashTable {
	
	/**
	 * Equivalent to get operation in HashMap.
	 * @param - transaction id from Dataset.
	 * @return - Retrieves the transaction data from
	 * 			 the hashtable.
	 */
	public Transaction get(String transactionId) {
		int bucketIndex = hash(transactionId);
		
		return retrieveFromBucket(bucketIndex, transactionId);
	}
	
	/**
	 * Retrieve the transaction from the bucket of bucketIndex.
	 * 
	 * @param bucketIndex - Index of the bucket computed from 
	 * 		  the hash function.
	 * @return - Transaction data stored in the given hashed index.
	 */
	protected abstract Transaction retrieveFromBucket(int bucketIndex, String transactionId);

	/**
	 * Equivalent to the put operation in HashMap.
	 * 
	 * @param transactionId - id from the dataset class to be hashed.
	 * @param tran - transaction information to be stored in the hashtable.
	 */
	public void put(String transactionId, Transaction tran) {
		int bucketIndex = hash(transactionId);
		putIntoBucket(bucketIndex, tran);
	}
	
	/**
	 * Stores the transaction data into the bucket for the given bucket index
	 * computed using hash function.
	 * 
	 * @param bucketIndex - Hashed index of the transactionid.
	 * @param tran - Transaction data to be stored in the hash table
	 */
	protected abstract void putIntoBucket(int bucketIndex, Transaction tran);

	/**
	 * To check if a hashtable is empty.
	 * @return true - if hashtable is empty; false otherwise.
	 */
	abstract public boolean isEmpty();
	
	/**
	 * Performs hashing of transaction id into an index of a bucket using
	 * choice of hashing algorithm(s).
	 * 
	 * @param transactionId - id from the dataset to be hashed.
	 * @return hash index of the corresponding transaciton id.
	 */
	public int hash(String transactionId){
		return (int) (hashingAlgorithm.hash(transactionId) & Integer.MAX_VALUE) % this.bucketLength;
	}
	
	/**
	 * The length of the underlying array(buckets).
	 */
	int bucketLength;
	
	public int getBucketLength() {
		return bucketLength;
	}

	public void setBucketLength(int bucketLength) {
		this.bucketLength = bucketLength;
	}

	/**
	 * The algorithm used for hashing the transaction id into the index of the array.
	 */
	HashingAlgorithm hashingAlgorithm; 
}