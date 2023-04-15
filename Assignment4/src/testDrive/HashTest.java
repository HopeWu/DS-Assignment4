package testDrive;

import java.util.*;
import dataset.Dataset;
import dataset.Transaction;
import hashingAlgorithms.CRC64;
import hashingAlgorithms.HashingAlgorithm;
import hashingAlgorithms.MurmurHash;
import hashingAlgorithms.SimpleHash;

public class HashTest {
    public static void main(String[] args){
    	testHashTime(100000);   	 
    	testCollisions(100000);
    	testCollisions(1000000);
    	testCollisions(10000000);
    	testDispersion(); 
    }
	private static void testHashTime(int size) {
		String[] strs = new String[size];
        for (int i = 0; i < size; i++) {
        	strs[i] = UUID.randomUUID().toString();
        }
                
        // built-in hashCode in Java
		long hashcodeTime = hashTime(null, strs);
        System.out.println(String.format("Java hashCode: %d ms", hashcodeTime));
        
        // Simple Hash
		HashingAlgorithm simpHash = new SimpleHash();
		long simpleTime = hashTime(simpHash, strs);
        System.out.println(String.format("Simple Hash: %d ms", simpleTime));
        
        // CRC64
        HashingAlgorithm crc64 = new CRC64();
		long crc64Time = hashTime(crc64, strs);
        System.out.println(String.format("crc64: %d ms", crc64Time));
        
        // Murmur Hash
		HashingAlgorithm murmurHash = new MurmurHash();
		long murmurTime = hashTime(murmurHash, strs);
        System.out.println(String.format("Murmur Hash: %d ms", murmurTime));
						
	}
	
	/**
	 * 
	 * @param algorithm hash algorithms to be tested, call the built-in hashCode function in Java if null
	 * @param strs
	 * @return
	 */
	private static long hashTime(HashingAlgorithm algorithm, String[] strs) {
	    long startTime = System.currentTimeMillis();
	    for (String str : strs) {
	    	if (algorithm == null) {
	    		str.hashCode();
	    	} else {
	    		algorithm.hash(str);	
	    	}	        
	    }
	    long endTime = System.currentTimeMillis();
	    return endTime - startTime;
	}
	
	private static void testCollisions(int size) {
        // built-in hashCode in Java
        System.out.println("*****************Java hashCode*****************");
        testCollision(null, size);
        
        // Simple Hash
        System.out.println("*****************Simple Hash*****************");
		HashingAlgorithm simpHash = new SimpleHash();
		testCollision(simpHash, size);
        
        
        // CRC64
        System.out.println("*****************crc64*****************");
        HashingAlgorithm crc64 = new CRC64();
		testCollision(crc64, size);
        
        // Murmur Hash
        System.out.println("*****************Murmur Hash*****************");
        HashingAlgorithm murmurHash = new MurmurHash();
		testCollision(murmurHash, size);
	}
	
	private static void testCollision(HashingAlgorithm algorithm, int size) {
		Set<String> strs = new HashSet<>();
        for (int i = 0; i < size; i++){
        	strs.add(UUID.randomUUID().toString());
        }
        
        Map<Long, List<String>> values = collision(algorithm, strs);

        long maxsize = 0;
        for (Map.Entry<Long, List<String>> entry : values.entrySet()) {
            List<String> bucket = entry.getValue();
            if (bucket.size() > maxsize) {
                maxsize = bucket.size();
            }
        }

        System.out.println("the total number of strings: " + strs.size());
        System.out.println("the total number of hash values: " + values.size());
        System.out.println("collisions: " + (strs.size() - values.size()));
        System.out.println("collision rate: " + String.format("%.8f", 1.0 * (strs.size() - values.size()) / strs.size()));
	}
	
    private static Map<Long, List<String>> collision(HashingAlgorithm algorithm, Set<String> originalValues) {
        Map<Long, List<String>> result = new HashMap<>();
        for (String originVal : originalValues) {
        	long hashVal = 0;
        	if (algorithm == null) {
        		hashVal = (long) originVal.hashCode();
        	} else {
        		hashVal = algorithm.hash(originVal);
        	}
        	// if key does not exist，create a new ArrayList and add the data; otherwise, directly add the data
            List<String> bucket = result.computeIfAbsent(hashVal, k -> new ArrayList<>());
            bucket.add(originVal);
        }
        return result;
    }    
    
    private static void testDispersion() {
        ArrayList<String> tranIds = new ArrayList<>();
		Transaction[] data = Dataset.generate(100);
		for(Transaction tran: data) {
			tranIds.add(tran.getTransactionId());
		}
        // built-in hashCode in Java
        System.out.println("Java hashCode:");
        testSingleDispersion(null, tranIds);
        
        // Simple Hash
        System.out.println("Simple Hash:");
		HashingAlgorithm simpHash = new SimpleHash();
		testSingleDispersion(simpHash, tranIds);
        
        
        // CRC64
        System.out.println("crc64:");
        HashingAlgorithm crc64 = new CRC64();
        testSingleDispersion(crc64, tranIds);
        
        // Murmur Hash
        System.out.println("Murmur Hash:");
        HashingAlgorithm murmurHash = new MurmurHash();
        testSingleDispersion(murmurHash, tranIds);
	}
    
    private static void testSingleDispersion(HashingAlgorithm algorithm, ArrayList<String> tranIds) {
        int[] buckets = new int[10];
        long hashVal = 0;
        for (String tran : tranIds) {
        	if (algorithm == null) {
        		hashVal = Integer.toUnsignedLong(tran.hashCode());  
        	} else {
                hashVal = algorithm.hash(tran);
        	}    		          
          int remainder = (int) (hashVal % 10);
          buckets[remainder] += 1;
      }
      System.out.println(Arrays.toString(buckets));
	}
}
