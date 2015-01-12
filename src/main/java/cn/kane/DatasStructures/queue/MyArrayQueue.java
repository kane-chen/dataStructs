package cn.kane.DatasStructures.queue;

public class MyArrayQueue implements MyQueueInterface {

	private int capacity ;
	private int length ;
	private int[] datas ;
	private int headPos ;
	private int tailPos ;
	
	public MyArrayQueue(int capacity){
		this.capacity = capacity ;
		this.datas = new int[this.capacity] ;
	}
	
	@Override
	public boolean isEmpty() {
		return this.length == 0 ;
	}

	@Override
	public void clean() {
		this.length = 0 ;
		this.headPos = this.tailPos = 0 ;
	}

	@Override
	public int getSize() {
		return this.length;
	}

	@Override
	public int getHead() throws Exception {
		if(this.isEmpty())
			throw new Exception("queue was empty !") ;
		
		return datas[this.headPos];
	}

	@Override
	public void enQueue(int data) throws Exception {
		if(length>=capacity)
			throw new Exception("queue was full!!");
		datas[tailPos] = data ;
		tailPos = (++tailPos)%(capacity) ;
		this.length++ ;
	}

	@Override
	public int deQueue() throws Exception {
		if(this.isEmpty())
			throw new Exception("queue was empty!!");
		int result = datas[headPos] ;
		headPos = (++headPos)%(capacity) ;
		this.length-- ;
		return result ;
	}

	@Override
	public void printAllDatas() {
		System.out.print("PRINT-ALL-DATAS: ");
		for(int i=0; i< length ; i++){
			int realPos = (headPos+i)%(capacity) ;
			System.out.print(datas[realPos]);
			System.out.print(' ');
		}
		System.out.println("");
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		MyQueueInterface arrQueue = new MyArrayQueue(10) ;
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
