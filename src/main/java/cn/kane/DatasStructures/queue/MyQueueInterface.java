package cn.kane.DatasStructures.queue;

public interface MyQueueInterface {

	boolean isEmpty() ;
	
	void clean() ;
	
	int getSize() ;
	
	int getHead() throws Exception;
	
	void enQueue(int data) throws Exception ;
	
	int deQueue() throws Exception ;
	
	void printAllDatas() ;
	
}
