package cn.kane.DatasStructures.list;

public interface MyListInterface {

	boolean isEmpty() ;
	
	int getSize() ;
	
	int getDataByIndex(int index) throws Exception ;
	
	int getIndexByData(int data) ;
	
	void insertData(int data) throws Exception ;
	
	void insertDataByIndex(int index,int data) throws Exception;
	
	int update(int index,int data) throws Exception ;
	
	int removeData(int data) throws Exception ;
	
	void removeDataByIndex(int index) throws Exception ;
	
	void printAllDatas() ;
	
}
