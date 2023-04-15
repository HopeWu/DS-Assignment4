package testDrive;

import java.util.Random;

import database.Database;
import dataset.Dataset;
import dataset.Transaction;
import hashingAlgorithms.JavaHashcodeAlgorithm;
import storageEngines.ClassicHashTable;
import storageEngines.HashTable;

/**
 * This class contains experiments on the ClassicHashTable with varying sizes of
 * the underlying array. These experiments will use the default Java's object
 * hashcode algorithm to do the hashing.
 * 
 * @author haopengwu
 *
 */
public class ClassicHashExperiment {
	public static void main(String[] args) {
		long start, end;
		int datasize = 10000;

		System.out.println(System.currentTimeMillis());
		HashTable classicHashTableHalfSize = new ClassicHashTable(new JavaHashcodeAlgorithm(), datasize / 2);

//		HashTable classicHashTableFull = new ClassicHashTable(new JavaHashcodeAlgorithm(), datasize);
//		
//		HashTable classicHashTableDoubleFull = new ClassicHashTable(new JavaHashcodeAlgorithm(), 2*datasize);

		start = System.currentTimeMillis();
		Transaction[] data = Dataset.generate(datasize);
		end = System.currentTimeMillis();
		
		System.out.println((end-start)/datasize);

		Database dbHalf = new Database(classicHashTableHalfSize);
		System.out.println(System.currentTimeMillis());
//		Database dbFull = new Database(classicHashTableFull);
//		Database dbDouble = new Database(classicHashTableDoubleFull);

		dbHalf.save(data);
//		dbFull.save(data);
//		dbDouble.save(data);

		Random ran = new Random();

		start = System.currentTimeMillis();
		System.out.println(start);

		for (int i = 0; i < datasize; ++i) {
			int index = ran.nextInt(datasize);
			System.out.println(dbHalf.lookup(data[index].getTransactionId()));
			;
		}
		dbHalf.lookup(data[0].getTransactionId());

		end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println(end - start);
	}

	/**
	 * Randomly returning a transaction.
	 */
}
