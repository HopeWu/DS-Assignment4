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
		this.length = DEFAULT_LENGTH;
		chainedArray = new TransactionNode[length];
		for(int i = 0; i < length; i++) {
			chainedArray[i] = null;
		}
		size = 0;
	}
	
	/**
	 *  Constructor to set the hashingAlgorithm member variable.
	 * @param hashingAlgorithm -  Reference of appt. hash algorithm
	 */
	public ClassicHashTable(HashingAlgorithm hashingAlgorithm){
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
	protected Transaction retrieveFromBucket(int bucketIndex) {
		if(chainedArray[bucketIndex] == null) {
			return null;
		}
		else {
			TransactionNode list = chainedArray[bucketIndex];
			while(list.getNext() != null) {
				if(list.getData() == null) {
					return null;
				}
				
				list.setNext(list.getNext());
			}
			return list.getData();
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
	
	private TransactionNode[] chainedArray;
	private int size;
    private static final int DEFAULT_LENGTH = 16;
}