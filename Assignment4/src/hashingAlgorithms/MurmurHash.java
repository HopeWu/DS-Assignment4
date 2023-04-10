package hashingAlgorithms;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Yan
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

        ByteBuffer buffer = ByteBuffer.wrap(key);
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buffer.order();
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buffer.remaining() * m);

        long k;
        while (buffer.remaining() >= 8) {
            k = buffer.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buffer.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            finish.put(buffer).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buffer.order(byteOrder);
        return h;
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