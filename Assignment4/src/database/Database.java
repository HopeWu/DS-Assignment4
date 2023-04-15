package database;

import dataset.Transaction;
import storageEngines.ClassicHashTable;
import storageEngines.HashTable;

/**
 * Database for saving and retrieving transactions. Each database has an engine that powers it for the saving and 
 * looking up.
 * @author haopengwu
 *
 */
public class Database {
	private HashTable hashTable = null;
	
	public Database(HashTable hashTable) {
		super();
		this.hashTable = hashTable;
	}
	
	/**
	 * To save a bunch of transactions into the database.
	 * @param transactions
	 */
	public void save(Transaction[] transactions) {
		for(Transaction tran: transactions) {
			this.save(tran);
		}
	}
	
	/**
	 * To save one transactions into the database.
	 * @param transaction
	 */
	public void save(Transaction transaction) {
		this.hashTable.put(transaction.getTransactionId(), transaction);
	}
	
	/**
	 * To lookup a transaction by its transaction id. It uses hashing table to achieve fast lookup, O(1) time.
	 * @param string
	 * @return
	 */
	public Transaction lookup(String string) {
		return this.hashTable.get(string);
	}
}
