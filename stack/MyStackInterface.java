package cn.kane.DatasStructures.stack;

public interface MyStackInterface {

	boolean isEmpty() ;
	
	void clean() ;
	
	int getSize() ;
	
	int getHead() throws Exception;
	
	void push(int data) throws Exception;
	
	int pop() throws Exception;
	
	void printAllDatas();
}
