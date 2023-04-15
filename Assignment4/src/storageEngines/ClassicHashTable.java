package storageEngines;

import dataset.Transaction;
import hashingAlgorithms.CRC64;
import hashingAlgorithms.HashingAlgorithm;

/**
 * Chaining method based on array and linked list to resolve collisions.
 * @author Prateek Dash
 *
 */
public class ClassicHashTable extends HashTable {
	
	/**
	 * Default Constructor: Initializes the member variables
	 * of super class and the current class.
	 * 
	 */
	public ClassicHashTable(){
		super.hashingAlgorithm = new CRC64();
		this.bucketLength = DEFAULT_LENGTH;
		chainedArray = new TransactionNode[bucketLength];
		for(int i = 0; i < bucketLength; i++) {
			chainedArray[i] = null;
		}
		size = 0;
	}
	
	/**
	 *  Constructor to set the hashingAlgorithm member variable.
	 * @param hashingAlgorithm -  Reference of appt. hash algorithm
	 */
	public ClassicHashTable(HashingAlgorithm hashingAlgorithm){
		this();
		super.hashingAlgorithm = hashingAlgorithm;
	}
	
	/**
	 * Queries the size of the hashtable.
	 * @return true - if hashtable is empty; false otherwise. 
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Retrieve the transaction from the bucket of bucketIndex.
	 * @param bucketIndex - the index of the bucket computed by
	 *  	  the hash function.
	 */
	@Override
	protected Transaction retrieveFromBucket(int bucketIndex, String transactionId) {
		if(chainedArray[bucketIndex] == null) {
			return null;
		}
		else {
			TransactionNode list = chainedArray[bucketIndex];
			while(list != null) {
				if(list.getData().getTransactionId() == transactionId) {
					return list.getData();
				}
				
				list = list.getNext();
			}
			return null;
		}
	}

	/**
	 * Puts the transaction into the bucket of bucketIndex by adding the
	 * instance of TransactionNode containing Transaction data in the 
	 * array index determined by the hash function. If the node already
	 * exists, then it creates a new node and 'chains' it to the existing
	 * node.
	 * 
	 * @param bucketIndex - Index of bucket as determined by hash function.
	 * @param tran - Transaction data to be added into the bucket.
	 */
	@Override
	protected void putIntoBucket(int bucketIndex, Transaction tran) {
		if(chainedArray[bucketIndex] == null) {
			TransactionNode newNode = new TransactionNode(tran, null);
			chainedArray[bucketIndex] = newNode;
		}
		else {
			TransactionNode list = chainedArray[bucketIndex];
			boolean found = false;
			while(list.getNext() != null) {
				if(list.getData() == tran) {
					list.setData(tran);
					found = true;
					break;
				}
				
				list.setNext(list.getNext());
			}
			
			if(!found) {
				TransactionNode newNode = new TransactionNode(tran, null);
				list.setNext(newNode);
			}
		}
		++size;
	}
	@Override
	public void setBucketLength(int bucketLength) {
		this.bucketLength = bucketLength;
		// reassign the chainedArray
		chainedArray = new TransactionNode[bucketLength];
	}
	
	/**
	 * The buckets. Each contains a linked list for the collided items who have been assigned into the this bucket.
	 */
	private TransactionNode[] chainedArray;
	/**
	 * The size of this hash table, i.e. how many items are there in this hash table.
	 */
	private int size;
	/**
	 * The default length of the bucket in use.
	 */
    private static final int DEFAULT_LENGTH = 16;
}