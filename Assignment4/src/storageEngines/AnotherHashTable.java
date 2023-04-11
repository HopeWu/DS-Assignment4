package storageEngines;

import dataset.Transaction;
import hashingAlgorithms.CRC64;
import hashingAlgorithms.HashingAlgorithm;

public class AnotherHashTable extends HashTable {
	public AnotherHashTable(){
		super.hashingAlgorithm = new CRC64();
	}
	
	public AnotherHashTable(HashingAlgorithm hashingAlgorithm){
		super.hashingAlgorithm = hashingAlgorithm;
	}

	@Override
	protected Transaction retrieveFromBucket(int bucketIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void putIntoBucket(int bucketIndex, Transaction tran) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
