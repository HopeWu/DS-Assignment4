package storageEngines;

import dataset.Transaction;

/**
 * Singly Linked List Node Representation for Transaction data.
 * 
 * @author Prateek Dash
 */
public class TransactionNode {
	
	/**
	 * Parameterized constructor to populate member variables.
	 * 
	 * @param data - Reference to Transaction class.
	 * @param next - Reference to TransactionNode class.
	 */
	public TransactionNode(Transaction data, TransactionNode next) {
		this.setData(data);
		this.next = next;
	}
	
	/**
	 * Retrives the transaction data from the node.
	 *
	 * @return Transaction - Reference to transaction class.
	 */
	public Transaction getData() {
		return data;
	}
	
	/**
	 * Sets the transaction data member variable
	 * @param data - Reference to Transaction class.
	 */
	public void setData(Transaction data) {
		this.data = data;
	}
	
	/**
	 * Retrieves the Transaction Node's reference to the
	 * corresponding node.
	 * 
	 * @return Reference to transaction node.
	 */
	public TransactionNode getNext() {
	    return next;
	}
	
	/**
	 * Populates the next member variable with the
	 * TransactionNode. 
	 * 
	 * @param next - Reference to the corresponding
	 * 		 TransactionNode.
	 */
	public void setNext(TransactionNode next) {
	    this.next = next;
	}
	
	private Transaction data;
	private TransactionNode next;
}