package dataset;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

/**
 * To generate the mocking data for the program. In this case, it's banking transactions.
 * @author haopengwu
 *
 */
public class Dataset {
	static long now = System.currentTimeMillis();

	/**
	 * To create random number to make fake data.
	 */
	static Random rand = new Random();
	
	/**
	 * The size of the dataset
	 */
	static int datasize = 0;
	
	/**
	 * No need to create an instance of this.
	 */
	private Dataset() {
	}

	public static Transaction[] generate(int size) {
		Transaction[] transactions = new Transaction[size];
		datasize = size;
		for(int i = 0; i < transactions.length; ++i) {
			transactions[i] = Dataset.randomOneTransaction();
		}
		return transactions;
	}

	/**
	 * Randomly generate one transaction.
	 * @return generated Transaction instance.
	 */
	private static Transaction randomOneTransaction() {
		// to generate the date of the transaction
		Date date = new Date(rand.nextLong(now - 100000000000L, now));
		// to generate the amount of the transaction
		int amount = rand.nextInt(10000);
		// to generate the currency for the amount
		Currency cur = Currency.values()[rand.nextInt(4)];
		// to generate the id of the sender, no specific reason for the range 
		int senderId = rand.nextInt(datasize);
		// to generate the id of the recipient
		int recipientId = rand.nextInt(datasize);
		// to generate the transactionId
		String transactionId = String.format("T%s%d", date.getTime()/1000L, rand.nextInt(1000,10000));
		
		return new Transaction(transactionId, senderId, recipientId, amount, cur, date);
	}

	public static void main(String[] args) {
		//		Transaction[] data = Dataset.generate(datasize);
		Transaction[] data = Dataset.generate(100);
		for(Transaction tran: data) {
			System.out.println(tran);
		}

	}
}
