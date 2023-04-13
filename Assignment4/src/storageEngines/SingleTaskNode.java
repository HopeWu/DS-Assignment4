package storageEngines;

import dataset.Transaction;

public class SingleTaskNode{
	
	public SingleTaskNode() {}

	private Transaction data;

	private SingleTaskNode next;

	// get the importance of this node
	public Transaction getData() {
		return data;
	}

	// set the importance of this node
	public void setData(Transaction data) {
		this.data = data;
	}
	
	// constructor
	public SingleTaskNode(Transaction data, SingleTaskNode next) {
	    this.setData(data);
	    this.next = next;
	}

	// get the next node of this node
	public SingleTaskNode getNext() {
	    return next;
	}

	// set the next node of this node
	public void setNext(SingleTaskNode next) {
	    this.next = next;
	}
}