package storageEngines;

import dataset.Transaction;
import hashingAlgorithms.CRC64;
import hashingAlgorithms.HashingAlgorithm;

/**
 * A HashTable implementation that uses open addressing with quadratic probing to resolve collisions.
 */
public class OpenAddressHashTableByQuadraticProbing extends HashTable{
	private static final int INITIAL_CAPACITY = 16;
	private static float LOAD_FACTOR = 0.75f;
	private int size = 0; 
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
    public OpenAddressHashTableByQuadraticProbing(HashingAlgorithm hashingAlgorithm, float factor) {
        super();
        this.length = INITIAL_CAPACITY;
        this.hashingAlgorithm = hashingAlgorithm;
        this.table = new Transaction[INITIAL_CAPACITY];
        OpenAddressHashTableByQuadraticProbing.LOAD_FACTOR=factor;
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
     * @param bucketIndex the initial index to insert the transaction
     * @param tran the transaction to be inserted
     */
    @Override
    protected void putIntoBucket(int bucketIndex, Transaction tran) {
        int i = 0;
        int index;
        do {
            index = (bucketIndex + i * i) % length;
            if (table[index] == null) {
                table[index] = tran;
                size++; // Increment the size counter when adding a new element

                // Check if resizing is needed
                if (1.0 * size / length > LOAD_FACTOR) {
                    resize();
                }

                return;
            }
            i++;
        } while (i < length);
    }
    
    /**
     * Resize the hash table when the number of elements exceeds the LOAD_FACTOR threshold.
     * This method doubles the length of the table and reinserts all the existing elements
     * into the new table using quadratic probing.
     */
    private void resize() {
        int newLength = length * 2;
        Transaction[] newTable = new Transaction[newLength];
        for (Transaction transaction : table) {
            if (transaction != null) {
                int newIndex = (int) hashingAlgorithm.hash(transaction.getTransactionId()) & Integer.MAX_VALUE % newLength;
                int i = 0;
                int probeIndex;
                do {
                    probeIndex = (newIndex + i * i) % newLength;
                    if (newTable[probeIndex] == null) {
                        newTable[probeIndex] = transaction;
                        break;
                    }
                    i++;
                } while (i < newLength);
            }
        }
        table = newTable;
        length = newLength;
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
