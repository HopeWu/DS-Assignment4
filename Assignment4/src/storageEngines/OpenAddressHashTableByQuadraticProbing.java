package storageEngines;

import dataset.Transaction;
import hashingAlgorithms.CRC64;
import hashingAlgorithms.HashingAlgorithm;

public class OpenAddressHashTableByQuadraticProbing extends HashTable{
	private static final int INITIAL_CAPACITY = 16;
    private Transaction[] table;
    
	public OpenAddressHashTableByQuadraticProbing(){
		super.hashingAlgorithm = new CRC64();
		this.length = INITIAL_CAPACITY;
		this.table = new Transaction[INITIAL_CAPACITY];
	}
	
    /**
     * Constructor for the QuadraticProbingHashTable.
     * @param hashingAlgorithm The algorithm used for hashing the transaction id into the index of the array.
     */
    public OpenAddressHashTableByQuadraticProbing(HashingAlgorithm hashingAlgorithm) {
        super();
        this.length = INITIAL_CAPACITY;
        this.hashingAlgorithm = hashingAlgorithm;
        this.table = new Transaction[INITIAL_CAPACITY];
    }

    /**
     * Retrieve the transaction from the table using quadratic probing.
     * @param bucketIndex the initial index to search for the transaction
     * @return the found transaction, or null if not found
     */
    @Override
    protected Transaction retrieveFromBucket(int bucketIndex) {
        int i = 0;
        int index;
        int targetHash = hash(table[bucketIndex].getTransactionId());
        do {
            index = (bucketIndex + i * i) % length;
            if (table[index] == null) {
                return null;
            }
            if (hash(table[index].getTransactionId()) == targetHash) {
                return table[index];
            }
            i++;
        } while (i < length);

        return null;
    }

    /**
     * Put the transaction into the table using quadratic probing.
     * @param bucketIndex the initial index to put the transaction
     * @param tran the transaction to insert
     */
    @Override
    protected void putIntoBucket(int bucketIndex, Transaction tran) {
        int i = 0;
        int index;
        do {
            index = (bucketIndex + i * i) % length;
            if (table[index] == null) {
                table[index] = tran;
                break;
            }
            i++;
        } while (i < length);
    }

    /**
     * Check if the hash table is empty.
     * @return true if the table is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        for (Transaction transaction : table) {
            if (transaction != null) {
                return false;
            }
        }
        return true;
    }
}
