package testDrive;

import dataset.Dataset;
import dataset.Transaction;
import hashingAlgorithms.*;
import hashingAlgorithms.HashingAlgorithm;
import storageEngines.*;

/**
 * This test class compares the performance of OpenAddressHashTable and
 * QuadraticProbingHashTable with different load factors.
 */
public class ConflictSolutionComparisonTest {

    private static final int STEP_COUNT = 10;
    private static final int RUNS = 3;
    private static final float INITIAL_LOAD_FACTOR = 0.1f;
    private static final float LOAD_FACTOR_STEP = 0.1f;
    private static final int INITIAL_DATA_SIZE = 1000;
    private static final int DATA_SIZE_STEP = 1000;
    private static final Transaction[] data = Dataset.generate(10000);

    public static void LoadFactorComparison(HashingAlgorithm hash) {
        
        // Instantiate a custom hashing algorithm, e.g., CRC32
        HashingAlgorithm hashingAlgorithm = hash;
        
        System.out.println("Data generation finished");

        for (int i = 0; i < STEP_COUNT; i++) {
            float currentLoadFactor = INITIAL_LOAD_FACTOR + i * LOAD_FACTOR_STEP;
            System.out.println("Testing with load factor: " + currentLoadFactor);

            double linearTime = 0;
            double quadraticTime = 0;

            for (int run = 0; run < RUNS; run++) {
                HashTable linear = new OpenAddressHashTableByLinearProbing(hashingAlgorithm, currentLoadFactor);
                HashTable quadratic = new OpenAddressHashTableByQuadraticProbing(hashingAlgorithm, currentLoadFactor);
        		
                long startTime, elapsedTime;

                // Test OpenAddressHashTableByLinearProbing
                startTime = System.nanoTime();
                for(Transaction tran: data) {
        			linear.put(tran.getTransactionId(), tran);
        		}
                elapsedTime = System.nanoTime() - startTime;
                linearTime += elapsedTime / 1e6;

                // Test OpenAddressHashTableByQuadraticProbing
                startTime = System.nanoTime();
                for(Transaction tran: data) {
        			quadratic.put(tran.getTransactionId(), tran);
        		}
                elapsedTime = System.nanoTime() - startTime;
                quadraticTime += elapsedTime / 1e6;
            }

            double linearAverageTime = linearTime / RUNS;
            double quadraticAverageTime = quadraticTime / RUNS;

            System.out.println("OpenAddressHashTable average time: " + linearAverageTime + " ms");
            System.out.println("QuadraticProbingHashTable average time: " + quadraticAverageTime + " ms");

            System.out.println("------------------------------");
        }
    }
    
    /**
     * This test class compares the performance of OpenAddressHashTable and
     * QuadraticProbingHashTable with different data sizes.
     */
    public static void DataSizeComparison(HashingAlgorithm hash) {
    	// Instantiate a custom hashing algorithm, e.g., CRC32
        HashingAlgorithm hashingAlgorithm = hash;

        for (int i = 0; i < STEP_COUNT; i++) {
            int currentDataSize = INITIAL_DATA_SIZE + i * DATA_SIZE_STEP;
            System.out.println("Testing with data size: " + currentDataSize);

            double linearTime = 0;
            double quadraticTime = 0;

            for (int run = 0; run < RUNS; run++) {
                HashTable linear = new OpenAddressHashTableByLinearProbing(hashingAlgorithm, 0.6f);
                HashTable quadratic = new OpenAddressHashTableByQuadraticProbing(hashingAlgorithm, 0.6f);

                long startTime, elapsedTime;

                // Test OpenAddressHashTable
                startTime = System.nanoTime();
                for (int j = 0; j < currentDataSize; j++) {
                	linear.put(data[j].getTransactionId(), data[j]);
                }
                elapsedTime = System.nanoTime() - startTime;
                linearTime += elapsedTime / 1e6;

                // Test QuadraticProbingHashTable
                startTime = System.nanoTime();
                for (int j = 0; j < currentDataSize; j++) {
                	quadratic.put(data[j].getTransactionId(), data[j]);
                }
                elapsedTime = System.nanoTime() - startTime;
                quadraticTime += elapsedTime / 1e6;
            }

            double openAddressAverageTime = linearTime / RUNS;
            double quadraticProbingAverageTime = quadraticTime / RUNS;

            System.out.println("OpenAddressHashTable average time: " + openAddressAverageTime + " ms");
            System.out.println("QuadraticProbingHashTable average time: " + quadraticProbingAverageTime + " ms");

            System.out.println("------------------------------");
        }
    }
	public static void main(String[] args) {
		LoadFactorComparison(new CRC64());
		DataSizeComparison(new CRC64());
	}
}
