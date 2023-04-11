package storageEngines;
import dataset.Transaction;
import hashingAlgorithms.CRC64;
import hashingAlgorithms.HashingAlgorithm;



/**
 * Based on array and linked list.
 * @author Prateek
 *
 */
public class ClassicHashTable extends HashTable{
	public ClassicHashTable(){
		super.hashingAlgorithm = new CRC64();
	}
	
	public ClassicHashTable(HashingAlgorithm hashingAlgorithm){
		super.hashingAlgorithm = hashingAlgorithm;
	}
	
	// other instance variable if needed
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Retrieve the transaction from the bucket of bucketIndex.
	 */
	@Override
	protected Transaction retrieveFromBucket(int bucketIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Put the transaction into the bucket of bucketIndex.
	 */
	@Override
	protected void putIntoBucket(int bucketIndex, Transaction tran) {
		// TODO Auto-generated method stub
		
	}
}
