package hashingAlgorithms;

import java.util.UUID;

public class HashTest {
    public static void main(String[] args) {
    	System.out.println("--------JDK hashCode--------------");
        System.out.println("0".hashCode());
        System.out.println("1".hashCode());
        System.out.println("2".hashCode());
        System.out.println("5729674");
        System.out.println("5729675");
        System.out.println("5729676");
        
        System.out.println("---------MurmurHash-------------");
        MurmurHash murmurHash = new MurmurHash();
        System.out.println(murmurHash.hash("0"));
        System.out.println(murmurHash.hash("1"));
        System.out.println(murmurHash.hash("2"));
        System.out.println(murmurHash.hash("5729674"));
        System.out.println(murmurHash.hash("5729675"));
        System.out.println(murmurHash.hash("5729676"));
        
        System.out.println("--------CRC64--------------");
        CRC64 crc64 = new CRC64();
        System.out.println(crc64.hash("0"));
        System.out.println(crc64.hash("1"));
        System.out.println(crc64.hash("2"));
        System.out.println(crc64.hash("5729674"));
        System.out.println(crc64.hash("5729675"));
        System.out.println(crc64.hash("5729676"));
        
        System.out.println("--------SimpleHash--------------");
        SimpleHash simpleHash = new SimpleHash();
        System.out.println(simpleHash.hash("0"));
        System.out.println(simpleHash.hash("1"));
        System.out.println(simpleHash.hash("2"));
        System.out.println(simpleHash.hash("5729674"));
        System.out.println(simpleHash.hash("5729675"));
        System.out.println(simpleHash.hash("5729676"));

        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String randomString = UUID.randomUUID().toString();
            murmurHash.hash(randomString);
        }
        long startTime2 = System.currentTimeMillis();
        System.out.println(String.format("MurmurHash: %d ms", startTime2 - startTime1));

        for (int i = 0; i < 100000; i++) {
        	String randomString = UUID.randomUUID().toString();
            crc64.hash(randomString);
        }
        long startTime3 = System.currentTimeMillis();
        System.out.println(String.format("CRC64: %d ms", startTime3 - startTime2));
        long startTime4 = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
        	String randomString = UUID.randomUUID().toString();
        	simpleHash.hash(randomString);
        }
        long startTime5 = System.currentTimeMillis();
        System.out.println(String.format("Simple Hash: %d ms", startTime5 - startTime4));
    }
}