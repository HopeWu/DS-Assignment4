package storageEngines;

import dataset.Transaction;
import hashingAlgorithms.CRC64;
import hashingAlgorithms.HashingAlgorithm;

/**
 * An implementation of HashTable that uses open addressing with linear probing to resolve collisions.
 */
public class OpenAddressHashTableByLinearProbing extends HashTable {

    private static final int DEFAULT_LENGTH = 16;
    private static float LOAD_FACTOR = 0.75f;

    private Transaction[] table;
    private int size;

    
	public OpenAddressHashTableByLinearProbing(){
		super.hashingAlgorithm = new CRC64();
		this.length = DEFAULT_LENGTH;
        this.table = new Transaction[length];
        this.size = 0;
	}
	
	public OpenAddressHashTableByLinearProbing(float factor){
		super.hashingAlgorithm = new CRC64();
		this.length = DEFAULT_LENGTH;
        this.table = new Transaction[length];
        this.size = 0;
        OpenAddressHashTableByLinearProbing.LOAD_FACTOR=factor;
	}
	
    /**
     * Constructor for OpenAddressHashTable.
     * @param hashingAlgorithm the hashing algorithm to use for converting transaction IDs into bucket indices
     */
    public OpenAddressHashTableByLinearProbing(HashingAlgorithm hashingAlgorithm) {
        this.length = DEFAULT_LENGTH;
        super.hashingAlgorithm = hashingAlgorithm;
        this.table = new Transaction[length];
        this.size = 0;

    }

    /**
     * Retrieve the transaction from the table using linear probing.
     * @param bucketIndex the initial index to search for the transaction
     * @return the found transaction, or null if not found
     */
    @Override
    protected Transaction retrieveFromBucket(int bucketIndex) {
        for (int i = bucketIndex; i < length; i = (i + 1) % length) {
            if (table[i] == null) {
            	System.out.println("Not found");
                return null;
            }
            if (hash(table[i].getTransactionId()) == bucketIndex) {
                return table[i];
            }
        }
        return null;
    }

    /**
     * Insert the transaction into the table using linear probing.
     * @param bucketIndex the initial index to insert the transaction
     * @param tran the transaction to insert
     */
    @Override
    protected void putIntoBucket(int bucketIndex, Transaction tran) {
        if (size + 1 > length * LOAD_FACTOR) {
            resize();
        }
        for (int i = bucketIndex; i < length; i = (i + 1) % length) {
            if (table[i] == null || table[i].getTransactionId().equals(tran.getTransactionId())) {
                table[i] = tran;
                size++;
                break;
            }
        }
    }

    /**
     * Check if the hash table is empty.
     * @return true if the hash table is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Resize the hash table by doubling its length and rehashing all transactions.
     */
    private void resize() {
        length *= 2;
        Transaction[] oldTable = table;
        table = new Transaction[length];
        size = 0;

        for (Transaction transaction : oldTable) {
            if (transaction != null) {
                put(transaction.getTransactionId(), transaction);
            }
        }
    }
}