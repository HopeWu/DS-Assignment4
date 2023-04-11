package dataset;

import java.util.Date;

/**
 * Mock banking transactions. For the demonstrating purpose, we use some naive assumptions, e.g. people names are
 * used as the identifier of a person.
 * @author haopengwu
 *
 */
public class Transaction {
	public Transaction(String transactionId, String sender, String recipient, int amount, Currency currency,
			Date date) {
		super();
		this.transactionId = transactionId;
		this.sender = sender;
		this.recipient = recipient;
		this.amount = amount;
		this.currency = currency;
		this.date = date;
	}
	
	/**
	 * Formatted as 
	 */
	String transactionId;
	
	/**
	 * The name of the sender
	 */
	String sender;
	
	/**
	 * The name of the recipient
	 */
	String recipient;
	
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

	public String getSender() {
		return sender;
	}

	public String getRecipient() {
		return recipient;
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
		return "Transaction [transactionId=" + transactionId + ", sender=" + sender + ", recipient=" + recipient
				+ ", amount=" + amount + ", currency=" + currency + ", date=" + date + "]";
	}
	
	
}
