package storageEngines;

import dataset.Transaction;
import hashingAlgorithms.CRC64;
import hashingAlgorithms.HashingAlgorithm;
import hashingAlgorithms.MurmurHash;

public class OpenAddressHashTableByDoubleHashing extends HashTable{
    private static final int INITIAL_CAPACITY = 16;
    private Transaction[] table;
    HashingAlgorithm hashingAlgorithm2; 
    
    public OpenAddressHashTableByDoubleHashing() {
        super();
        this.length = INITIAL_CAPACITY;
        this.hashingAlgorithm = new CRC64();
        this.hashingAlgorithm2 = new MurmurHash();
        this.table = new Transaction[INITIAL_CAPACITY];
    }
    /**
     * Constructor for the DoubleHashingHashTable.
     * 
     * @param hashingAlgorithm The algorithm used for hashing the transaction id into the index of the array.
     */
    public OpenAddressHashTableByDoubleHashing(HashingAlgorithm hashingAlgorithm,HashingAlgorithm hashingAlgorithm2) {
        super();
        this.length = INITIAL_CAPACITY;
        this.hashingAlgorithm = hashingAlgorithm;
        this.hashingAlgorithm2 = hashingAlgorithm2;
        this.table = new Transaction[INITIAL_CAPACITY];
    }

    /**
     * Retrieve the transaction from the table using double hashing.
     *
     * @param bucketIndex the initial index to search for the transaction
     * @return the found transaction, or null if not found
     */
    @Override
    protected Transaction retrieveFromBucket(int bucketIndex) {
    	int i = 0;
        int index;
        int targetHash = hash(table[bucketIndex].getTransactionId());
        int step = secondHash(table[bucketIndex].getTransactionId());

        do {
            index = (bucketIndex + i * step) % length;
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
     * Put the transaction into the table using double hashing.
     *
     * @param bucketIndex the initial index to put the transaction
     * @param tran the transaction to insert
     */
    @Override
    protected void putIntoBucket(int bucketIndex, Transaction tran) {
        int i = 0;
        int index;
        int hash2 = secondHash(tran.getTransactionId());
        do {
            index = (bucketIndex + i * hash2) % length;
            if (table[index] == null) {
                table[index] = tran;
                break;
            }
            i++;
        } while (i < length);
    }

    /**
     * Check if the hash table is empty.
     *
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
    
    /**
     * Calculate the second hash value used in double hashing.
     * 
     * @param bucketIndex the initial index to search for the transaction
     * @return the second hash value
     */
    private int secondHash(String transactionId) {
    	return 1 + (int) (hashingAlgorithm2.hash(transactionId) % (length - 1));
    }
}
