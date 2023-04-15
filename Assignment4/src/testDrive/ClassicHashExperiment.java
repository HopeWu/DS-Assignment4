package testDrive;

import java.util.Random;

import database.Database;
import dataset.Dataset;
import dataset.Transaction;
import hashingAlgorithms.JavaHashcodeAlgorithm;
import storageEngines.ClassicHashTable;
import storageEngines.HashTable;

/**
 * This class contains experiments on the ClassicHashTable with varying sizes of the underlying array.
 * These experiments will use the default Java's object hashcode algorithm to do the hashing.
 * @author haopengwu
 *
 */
public class ClassicHashExperiment {
	public static void main(String[] args) {
		int datasize = 10;
		
		HashTable classicHashTableHalfSize = new ClassicHashTable(new JavaHashcodeAlgorithm());
		classicHashTableHalfSize.setBucketLength(datasize/2);
		
		HashTable classicHashTableFull = new ClassicHashTable(new JavaHashcodeAlgorithm());
		classicHashTableFull.setBucketLength(datasize);
		
		HashTable classicHashTableDoubleFull = new ClassicHashTable(new JavaHashcodeAlgorithm());
		classicHashTableDoubleFull.setBucketLength(2*datasize);
		
		Transaction[] data = Dataset.generate(datasize);
		
		Database dbHalf = new Database(classicHashTableHalfSize);
		Database dbFull = new Database(classicHashTableFull);
		Database dbDouble = new Database(classicHashTableDoubleFull);
		
		dbHalf.save(data);
		dbFull.save(data);
		dbDouble.save(data);

		long start, end;
		
		Random ran = new Random();
		
		start = System.currentTimeMillis();
		for ( int i = 0; i < datasize/10; ++i) {
			int index = ran.nextInt(datasize);
			dbHalf.lookup(data[index].getTransactionId());
		}
		dbHalf.lookup(data[0].getTransactionId());
		end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	/**
	 * Randomly returning a transaction.
	 */
}
