package cn.kane.DatasStructures.queue;

public class MyLinkedQueue implements MyQueueInterface {

	class DoubleLinkedNode {
		private int data ;
		private DoubleLinkedNode prevNode ;
		private DoubleLinkedNode nextNode ;
		public DoubleLinkedNode(int data){
			this.data = data ;
		}
	}

	private int capacity ;
	private int length ;
	private DoubleLinkedNode headNode ;
	private DoubleLinkedNode tailNode ;
	
	public MyLinkedQueue(int capacity){
		this.capacity = capacity ;
		headNode = new DoubleLinkedNode(-1);
		headNode.nextNode = tailNode ;
		tailNode = new DoubleLinkedNode(-1);
		tailNode.prevNode = headNode ;
	}
	
	@Override
	public boolean isEmpty() {
		return this.length == 0 ;
	}

	@Override
	public void clean() {
		this.length = 0 ;
		this.headNode.nextNode = this.tailNode ;
		this.tailNode.prevNode = this.headNode ;
	}

	@Override
	public int getSize() {
		return this.length ;
	}

	@Override
	public int getHead() throws Exception {
		if(this.isEmpty())
			throw new Exception("queue was empty!!");
		
		return 0;
	}

	@Override
	public void enQueue(int data) throws Exception {
		if(this.length>=this.capacity)
			throw new Exception("queue was Full!!");
		DoubleLinkedNode newNode = new DoubleLinkedNode(data) ;
		DoubleLinkedNode lastNode = tailNode.prevNode ;
		lastNode.nextNode = newNode ;
		newNode.prevNode = lastNode ;
		tailNode.prevNode = newNode ;
		newNode.nextNode = tailNode ;
		this.length++ ;
	}

	@Override
	public int deQueue() throws Exception {
		if(this.isEmpty())
			throw new Exception("queue was empty!!");
		DoubleLinkedNode targetNode = headNode.nextNode ;
		headNode.nextNode = targetNode.nextNode ;
		targetNode.nextNode.prevNode = headNode ;
		this.length-- ;
		return targetNode.data;
	}

	@Override
	public void printAllDatas() {
		System.out.print("PRINT-ALL-DATAS: ");
		DoubleLinkedNode curNode = headNode ;
		int pos = 0 ;
		while(curNode!=null && pos<length){
			curNode = curNode.nextNode ;
			pos++ ;
			System.out.print(curNode.data);
			System.out.print(' ');
		}
		System.out.println("");
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		MyQueueInterface arrQueue = new MyLinkedQueue(10) ;
		System.out.println(arrQueue.isEmpty());
		for(int i=0;i<10;i++)
			arrQueue.enQueue(i) ;
		arrQueue.printAllDatas() ;
		System.out.println(arrQueue.getHead());
		for(int i=0;i<5;i++)
			arrQueue.deQueue();
		arrQueue.printAllDatas() ;
		for(int i=0;i<5;i++)
			arrQueue.enQueue(i);
		arrQueue.printAllDatas() ;
		for(int i=0;i<7;i++)
			arrQueue.deQueue();
		arrQueue.printAllDatas() ;
		
	}
	
}
