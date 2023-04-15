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
		
		HashTable classicHashTableHalfSize = new ClassicHashTable(new JavaHashcodeAlgorithm(), datasize/2);
		
		//HashTable classicHashTableFull = new ClassicHashTable(new JavaHashcodeAlgorithm(), datasize);
		
		//HashTable classicHashTableDoubleFull = new ClassicHashTable(new JavaHashcodeAlgorithm(), 2*datasize);
		
		Transaction[] data = Dataset.generate(datasize/2);
		
		System.out.println("Reached line 30");
		Database dbHalf = new Database(classicHashTableHalfSize);
		//Database dbFull = new Database(classicHashTableFull);
		//Database dbDouble = new Database(classicHashTableDoubleFull);
		
		dbHalf.save(data);
		//dbFull.save(data);
		//dbDouble.save(data);
		System.out.println("Reached line 38");

		long start, end;
		
		Random ran = new Random();
		
		start = System.currentTimeMillis();
		for ( int i = 0; i < data.length; ++i) {
			int index = ran.nextInt(datasize/2);
			dbHalf.lookup(data[index].getTransactionId());
		}
		end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	/**
	 * Randomly returning a transaction.
	 */
}
