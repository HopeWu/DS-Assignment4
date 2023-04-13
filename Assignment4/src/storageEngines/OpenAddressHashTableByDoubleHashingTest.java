package storageEngines;

import dataset.Dataset;
import dataset.Transaction;

public class OpenAddressHashTableByDoubleHashingTest {

	public static void main(String[] args) {
		OpenAddressHashTableByDoubleHashing hashTable = new OpenAddressHashTableByDoubleHashing();

        Transaction[] data = Dataset.generate(10);
		for(Transaction tran: data) {
			System.out.println(tran);
			hashTable.put(tran.getTransactionId(), tran);
		}
		
		// Test put and get methods
        assert hashTable.get(data[1].getTransactionId()).equals(data[1]) : "Transaction T1 not found";
        assert hashTable.get(data[3].getTransactionId()).equals(data[3]) : "Transaction T1 not found";

        // Test isEmpty method
        assert !hashTable.isEmpty() : "HashTable is empty";

        System.out.println("All tests passed!");
	}

}
