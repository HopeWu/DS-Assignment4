package hashingAlgorithms;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


/**
 * 
 * A class implementing the MurMurHash function with low collision rate 
 * 
 */
public class MurmurHash implements HashingAlgorithm {

    /**
     * Calculates the hash value of a given array of bytes
     * @param key byte[] An array of bytes
     * @return long the calculated hash values
     */
    private long hash(byte[] key) {

        ByteBuffer bytebuffer = ByteBuffer.wrap(key);
        // random seed
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = bytebuffer.order();
        bytebuffer.order(ByteOrder.LITTLE_ENDIAN);

        long n = 0xc6a4a7935bd1e995L;
        int s = 47;

        long l = seed ^ (bytebuffer.remaining() * n);

        long i;
        while (bytebuffer.remaining() >= 8) {
            i = bytebuffer.getLong();

            i *= n;
            i ^= i >>> s;
            i *= n;

            l ^= i;
            l *= n;
        }

        if (bytebuffer.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            finish.put(bytebuffer).rewind();
            l ^= finish.getLong();
            l *= n;
        }

        l ^= l >>> s;
        l *= n;
        l ^= l >>> s;

        bytebuffer.order(byteOrder);
        return l;
    }

    /**
     * Converts the calculated hash value to unsigned long
     * @param value the initially calculated hash value
     * @return converted unsigned long value
     */
    public static Long convertToUnsigned(long value) {
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