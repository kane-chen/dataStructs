package cn.kane.DatasStructures.list;

class DataNode{
	private int datas ;
	private int nextPos ;
	public DataNode(int datas){
		this.setDatas(datas) ;
		this.setNextPos(0) ;
	}
	public DataNode(int datas,int nextPos){
		this.setDatas(datas) ;
		this.setNextPos(nextPos) ;
	}
	public int getDatas() {
		return datas;
	}
	public void setDatas(int datas) {
		this.datas = datas;
	}
	public int getNextPos() {
		return nextPos;
	}
	public void setNextPos(int nextPos) {
		this.nextPos = nextPos;
	}
}

public class StaticLinkListWithArray implements MyListInterface{
	
	private int capacity ;
	private int length ;
	private int headPos = 0 ;
	private DataNode[] datas ;
	private int freePos ;
	
	public StaticLinkListWithArray(int capacity){
		this.capacity = capacity ;
		datas = new DataNode[this.capacity] ;
		for(int i=0;i<capacity;i++){
			datas[i] = new DataNode(-1,i+1);
		}
		freePos = 0 ;
	}
	
	public boolean isEmpty(){
		return this.length==0;
	}
	
	public boolean isFull(){
		return this.length == this.capacity ;
	}
	
	public void printAllDatas(){
		int depth = 0 ;
		int currentPos = headPos ;
		System.out.print("PRINT-ALL-DATAS: ");
		while(depth<length){
			DataNode currentNode = datas[currentPos] ; 
			currentPos = currentNode.getNextPos() ;
			if(null!=currentNode){
				System.out.print(currentNode.getDatas());
				System.out.print(' ');
			}
			depth++ ;
		}
		System.out.println("");
	}
	
	public void insertData(int data) throws Exception{
		if(isFull()){
			throw new Exception("LIST FULL");
		}else{
			datas[freePos].setDatas(data);
			freePos = datas[freePos].getNextPos() ;
			this.length++ ;
		}
	}
	
	public void insertDataByIndex(int index,int data) throws Exception{
		if(index>=0 && index <length && index < capacity){
			int currentPos = freePos ;
			freePos = datas[currentPos].getNextPos();
			if(index == 0){
				headPos = currentPos ;
				datas[currentPos].setDatas(data);
				datas[freePos].setNextPos(headPos);
			}else if(index == length-1){
				datas[currentPos].setDatas(data);
			}else{
				int realPos = headPos ;
				int depth = 0 ;
				while(depth<index-1){
					realPos = datas[realPos].getNextPos() ;
					depth++;
				}
				
				DataNode prevNode = datas[realPos] ;
				DataNode newNode = datas[currentPos] ;
				newNode.setDatas(data);
				newNode.setNextPos(prevNode.getNextPos());
				prevNode.setNextPos(currentPos);
				
			}
			this.length++ ;
		}else{
			throw new Exception("index invalid");
		}
	}
	
	public int getDataByIndex(int index) throws Exception{
		if(index<0 || index>length-1)
			throw new Exception("outofindex");
		int realPos = headPos ;
		int depth = 0 ;
		while(depth<index){
			realPos = datas[realPos].getNextPos() ;
			depth++;
		}
		DataNode currentNode = datas[realPos] ;
		return currentNode.getDatas() ;
		
	}
	
	public int getIndexByData(int data){
		int index = -1 ;
		int realPos = headPos ;
		int depth = 0 ;
		while(depth<length){
			DataNode currentNode = datas[realPos] ;
			if(data == currentNode.getDatas()){
				index = depth;
				break ;
			}
			depth++;
			realPos = currentNode.getNextPos() ;
		}
		return index ;
	}
	
	public void removeDataByIndex(int index) throws Exception{
		if(index<0 || index>length-1)
			throw new Exception("outofindex");
		int realPos = headPos ;
		int depth = 0 ;
		while(depth<index-1){
			realPos = datas[realPos].getNextPos() ;
			depth++;
		}
		DataNode prevNode = datas[realPos] ;
		freePos = prevNode.getNextPos();
		prevNode.setNextPos(datas[freePos].getNextPos());
		this.length-- ;
	}
	
	public int removeData(int data) throws Exception{
		int removed = 0 ;
		int realPos = headPos ;
		int depth = 0 ;
		while(depth<length){
			realPos = datas[realPos].getNextPos() ;
			DataNode nextNode = datas[datas[realPos].getNextPos()] ;
			if(data == nextNode.getDatas()){
				DataNode prevNode = datas[realPos] ;
				freePos = prevNode.getNextPos();
				prevNode.setNextPos(datas[freePos].getNextPos());
				this.length-- ;
				removed++ ;
			}
			depth++;
		}
		return removed ;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */

	@Override
	public int getSize() {
		return this.length;
	}


	@Override
	public int update(int index, int data) throws Exception {
		if(index<0 || index > length-1)
			throw new Exception("index must be [0~"+length+")");
		int realPos = headPos ;
		for(int i=1;i<index;i++)
			realPos = datas[realPos].getNextPos() ;
		int result = datas[realPos].getDatas() ;
		datas[realPos].setDatas(data);
		return result ;
	}

	public static void main(String[] args) throws Exception {
		StaticLinkListWithArray staticLinkLst = new StaticLinkListWithArray(10);
		for(int i=0;i<5;i++){
			staticLinkLst.insertData(i);
		}
		staticLinkLst.printAllDatas();
		
		staticLinkLst.insertDataByIndex(3, 3333);
		staticLinkLst.printAllDatas();
		
		System.out.println(staticLinkLst.getIndexByData(3333));
		System.out.println(staticLinkLst.getDataByIndex(3));
		
//		staticLinkLst.removeDataByIndex(3);
		staticLinkLst.removeData(3333);
		staticLinkLst.printAllDatas();
	}

}
