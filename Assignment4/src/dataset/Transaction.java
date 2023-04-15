package dataset;

import java.util.Date;

/**
 * Mock banking transactions. For the demonstrating purpose, we use some naive assumptions, e.g. people names are
 * used as the identifier of a person.
 * @author haopengwu
 *
 */
public class Transaction {
	public Transaction(String transactionId, int sender, int recipient, int amount, Currency currency,
			Date date) {
		super();
		this.transactionId = transactionId;
		this.senderId = sender;
		this.recipientId = recipient;
		this.amount = amount;
		this.currency = currency;
		this.date = date;
	}
	
	/**
	 * Formatted as 
	 */
	String transactionId;
	
	/**
	 * The id of the sender
	 */
	int senderId;
	
	/**
	 * The id of the recipient
	 */
	int recipientId;
	
	/**
	 * Amount of the transaction
	 */
	int amount;
	
	/**
	 * The currency for amount.
	 */
	Currency currency;
	
	/**
	 * The date and time for this transaction.
	 */
	Date date;
	
	public String getTransactionId() {
		return transactionId;
	}

	public int getSender() {
		return senderId;
	}

	public int getRecipient() {
		return recipientId;
	}

	public int getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", senderId=" + senderId + ", recipientId=" + recipientId
				+ ", amount=" + amount + ", currency=" + currency + ", date=" + date + "]";
	}
}
