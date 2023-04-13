package storageEngines;
import dataset.Dataset;
import dataset.Transaction;
import hashingAlgorithms.CRC64;
import hashingAlgorithms.HashingAlgorithm;

/**
 * Chaining method based on array and linked list to resolve collisions.
 * @author Prateek Dash
 *
 */
public class ClassicHashTable extends HashTable{
	public ClassicHashTable(){
		super.hashingAlgorithm = new CRC64();
		this.length = DEFAULT_LENGTH;
		this.chainedArray = new SingleTaskNode[length];
		for(int i = 0; i < length; i++) {
			chainedArray[i] = null;
		}
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
	 */
	@Override
	protected Transaction retrieveFromBucket(int bucketIndex) {
		if(chainedArray[bucketIndex] == null) {
			return null;
		}
		else {
			SingleTaskNode list = chainedArray[bucketIndex];
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
	 * Put the transaction into the bucket of bucketIndex.
	 */
	@Override
	protected void putIntoBucket(int bucketIndex, Transaction tran) {
		if(chainedArray[bucketIndex] == null) {
			SingleTaskNode newNode = new SingleTaskNode(tran, null);
			chainedArray[bucketIndex] = newNode;
		}
		else {
			SingleTaskNode list = chainedArray[bucketIndex];
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
				SingleTaskNode newNode = new SingleTaskNode(tran, null);
				list.setNext(newNode);
			}
		}
		++size;
	}
	
	private SingleTaskNode chainedArray[];
	private int size;
    private static final int DEFAULT_LENGTH = 16;
}