package hashingAlgorithms;

/**
 * A class implementing the crc64 hash function with low collision rate
 *
 */
public class CRC64 implements HashingAlgorithm {
	
    private static final long INITIAL = 0xFFFFFFFFFFFFFFFFL;
    private static long[] table = new long[256];
    private static final long POLY64 = 0x95AC9329AC4BC9B5L;

    static {
        long k;
        for (int i = 0; i < 256; i++) {
            k = i;
            for (int j = 0; j < 8; j++) {
                long m = ((int) k & 1) != 0 ? POLY64 : 0;
                k = (k >> 1) ^ m;
            }
            table[i] = k;
        }
    }

    /**
     * Calculates the hash value of a given array of bytes
     * @param key byte[] An array of bytes
     * @return long the calculated hash values
     */
    private long hash(byte[] key) {
        long crc = INITIAL;
        for (int i = 0, n = key.length; i < n; i++) {
            crc = table[(((int) crc) ^ key[i]) & 0xff] ^ (crc >> 8);
        }
        return crc;
    }

    /**
     * Converts the calculated hash value to unsigned long
     * @param value the initially calculated hash value
     * @return converted unsigned long value
     */
    public long convertToUnsigned(long value) {
        if (value >= 0){
            return value;
        }
        return value & 0x7fffffffffffffffL;
    }

    /**
     * Gets the unsigned long integer hash value
     * @param transactionId the original value
     * @return hash value
     */
	@Override
	public long hash(String transactionId) {
        if (transactionId == null || transactionId.length() == 0) {
            return 0;
        }
//        Encodes the key into a sequence of bytes
        byte[] bytes = transactionId.getBytes();
        Long hashVal = hash(bytes);
        return convertToUnsigned(hashVal);
	}
}