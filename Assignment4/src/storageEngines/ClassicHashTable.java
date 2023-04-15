package storageEngines;

import dataset.Transaction;
import hashingAlgorithms.HashingAlgorithm;

/**
 * Chaining method based on array and linked list to resolve collisions.
 * 
 * @author Prateek Dash
 *
 */
public class ClassicHashTable extends HashTable {
	/**
	 * Constructor to set the hashingAlgorithm member variable.
	 * 
	 * @param hashingAlgorithm - Reference of appt. hash algorithm
	 */
	public ClassicHashTable(HashingAlgorithm hashingAlgorithm, int bucketlength) {
		super.hashingAlgorithm = hashingAlgorithm;
		this.bucketLength = bucketlength;
		chainedArray = new TransactionNode[bucketLength];
		for (int i = 0; i < bucketLength; i++) {
			chainedArray[i] = null;
		}
		size = 0;
	}

	/**
	 * Queries the size of the hashtable.
	 * 
	 * @return true - if hashtable is empty; false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Retrieve the transaction from the bucket of bucketIndex.
	 * 
	 * @param bucketIndex - the index of the bucket computed by the hash function.
	 */
	@Override
	protected Transaction retrieveFromBucket(int bucketIndex, String transactionId) {

		// get the bucket
		TransactionNode list = chainedArray[bucketIndex];
		while (list != null) {
			if (list.getData().getTransactionId() == transactionId) {
				return list.getData();
			}

			list = list.getNext();
		}
		return null;

	}

	/**
	 * Puts the transaction into the bucket of bucketIndex by adding the instance of
	 * TransactionNode containing Transaction data in the array index determined by
	 * the hash function. If the node already exists, then it creates a new node and
	 * 'chains' it to the existing node.
	 * 
	 * @param bucketIndex - Index of bucket as determined by hash function.
	 * @param tran        - Transaction data to be added into the bucket.
	 */
	@Override
	protected void putIntoBucket(int bucketIndex, Transaction tran) {
		// if the linked list is null, create one
		if (chainedArray[bucketIndex] == null) {
			TransactionNode newNode = new TransactionNode(tran, null);
			chainedArray[bucketIndex] = newNode;
		} else {
			// find the linked list for this bucket
			TransactionNode list = chainedArray[bucketIndex];

			// get to the end of the linked list
			while (list.getNext() != null) {
				list = list.getNext();
			}

			// add this transaction to the end of the linked list
			TransactionNode newNode = new TransactionNode(tran, null);
			list.setNext(newNode);
		}

		// increase the size of the hash table
		++size;
	}

	@Override
	public void setBucketLength(int bucketLength) {
		this.bucketLength = bucketLength;
		// reassign the chainedArray
		chainedArray = new TransactionNode[bucketLength];
	}

	/**
	 * The buckets. Each contains a linked list for the collided items who have been
	 * assigned into the this bucket.
	 */
	private TransactionNode[] chainedArray;
	/**
	 * The size of this hash table, i.e. how many items are there in this hash
	 * table.
	 */
	private int size;
}