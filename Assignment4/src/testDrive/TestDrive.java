package testDrive;

import dataset.Dataset;
import dataset.Transaction;
import hashingAlgorithms.CRC64;
import storageEngines.ClassicHashTable;
import database.Database;

public class TestDrive {
	public static void main(String[] args) {		

//		// get the testing data
//		Transaction[] transactions = Dataset.generate(10);
//		
//		// save the transactions into the database
//		Database database = new Database(new ClassicHashTable(new CRC64(), 1000));
//		database.save(transactions);
//		
//		// lookup one transaction by its id
//		Transaction tran = database.lookup(transactions[0].getTransactionId());
//		System.out.println(tran);	
		HashTest.run();

	}
}
