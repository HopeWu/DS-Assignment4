package hashingAlgorithms;

public class CRC64 implements HashingAlgorithm {
	
    private static final long INITIALCRC = 0xFFFFFFFFFFFFFFFFL;
    private static long[] crcTable = new long[256];
    private static final long POLY64REV = 0x95AC9329AC4BC9B5L;

    static {
        long part;
        for (int i = 0; i < 256; i++) {
            part = i;
            for (int j = 0; j < 8; j++) {
                long x = ((int) part & 1) != 0 ? POLY64REV : 0;
                part = (part >> 1) ^ x;
            }
            crcTable[i] = part;
        }
    }

    /**
     * Calculates the hash value of a given array of bytes
     * @param key byte[] An array of bytes
     * @return long the calculated hash values
     */
    private long hash(byte[] key) {
        long crc = INITIALCRC;
        for (int k = 0, n = key.length; k < n; ++k) {
            crc = crcTable[(((int) crc) ^ key[k]) & 0xff] ^ (crc >> 8);
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
     * Gets unsigned long integer hash value
     * @param key
     * @return
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