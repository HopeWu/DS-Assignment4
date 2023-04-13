package storageEngines;

import java.util.Date;

import dataset.Dataset;
import dataset.Transaction;
import hashingAlgorithms.HashingAlgorithm;

import java.util.Currency;

public class OpenAddressHashTableByLinearProbingTest {

    public static void main(String[] args) {
        OpenAddressHashTableByLinearProbing hashTable = new OpenAddressHashTableByLinearProbing();

        Transaction[] data = Dataset.generate(10);
		for(Transaction tran: data) {
			System.out.println(tran);
			hashTable.put(tran.getTransactionId(), tran);
		}
		
		// Test put and get methods
        assert hashTable.get(data[1].getTransactionId()).equals(data[1]) : "Transaction T1 not found";

        // Test isEmpty method
        assert !hashTable.isEmpty() : "HashTable is empty";

        System.out.println("All tests passed!");
    }
}
