package cn.kane.DatasStructures.list;

class LinkedNode{
	private int data ;
	private LinkedNode next = null ;
	
	public LinkedNode(int data){
		this.setData(data) ;
		this.setNext(null) ;
	}
	public LinkedNode(int data,LinkedNode next){
		this.setData(data) ;
		this.setNext(next) ;
	}

	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public LinkedNode getNext() {
		return next;
	}
	public void setNext(LinkedNode next) {
		this.next = next;
	}
}

public class MyListWithLinked implements MyListInterface{

	private LinkedNode headNode ;
	private int length = 0 ;
	
	public MyListWithLinked(){
		headNode = new LinkedNode(-1,null) ;
	}
	
	public boolean isEmpty(){
		return this.length == 0 ;
	}
	
	public void insertDataInHead(int data){
		LinkedNode newNode = new LinkedNode(data);
		if(headNode.getNext()!=null)
			newNode.setNext(headNode.getNext());
		headNode.setNext(newNode);
		this.length++ ;
	}
	
	public void insertDataInTail(int data){
		LinkedNode lastNode = headNode ;
		while(lastNode.getNext()!=null)
			lastNode = lastNode.getNext();
		
		LinkedNode newNode = new LinkedNode(data);
		lastNode.setNext(newNode);
		this.length++ ;
	}
	
	public void insertDataByIndex(int index,int data) throws Exception{
		if(index>this.length)
			throw new Exception("index greater than length!");
		else if(index == this.length)
			this.insertDataInTail(data);
		else{
			LinkedNode nextNode = this.headNode ;
			int pos = 0 ;
			while(null != nextNode.getNext()  && pos < index){
				nextNode = nextNode.getNext() ;
				pos++;
			}
			if(null!=nextNode){
				LinkedNode newNode = new LinkedNode(data);
				newNode.setNext(nextNode.getNext());
				nextNode.setNext(newNode);
				this.length++ ;
			}
		}
	}
	
	public int removeData(int data){
		int removed = 0 ;
		LinkedNode currentNode = headNode ;
		while(currentNode!=null){
			LinkedNode nextNode = currentNode.getNext() ;
			if(null!=nextNode){
				if(data == nextNode.getData()){
					currentNode.setNext(nextNode.getNext());
					this.length-- ;
					removed++ ;
				}
			}
			currentNode = nextNode ;
		}
		
		return removed ;
	}
	
	public void removeDataByIndex(int index) throws Exception{
		if(index > this.length)
			throw new Exception("index greater than length!");
		
		LinkedNode currentNode = this.headNode ;
		int pos = 0 ;
		while(null != currentNode  &&  pos < index-1){
			currentNode = currentNode.getNext() ;
			pos++ ;
		}
		if(null!=currentNode){
			currentNode.setNext(currentNode.getNext().getNext());
			this.length-- ;
		}
	}
	
	public int getDataByIndex(int index) throws Exception{
		if(index > this.length)
			throw new Exception("index greater than length!");
		LinkedNode currentNode = this.headNode ;
		int pos = 0 ;
		while(null != currentNode  &&  pos <= index){
			currentNode = currentNode.getNext() ;
			pos++;
		}
		if(null!=currentNode){
			return currentNode.getData();
		}else{
			throw new Exception("NO SUCH ELEMENT");
		}
	}
	
	public int getIndexByData(int data){
		int index = -1 ;
		LinkedNode currentNode = this.headNode ;
		int pos = 0 ;
		while(null != currentNode){
			currentNode = currentNode.getNext() ;
			pos++ ;
			if(null!=currentNode){
				if(data == currentNode.getData()){
					index = pos ;
					break ;
				}
			}
		}
		return index ;
	}
	
	public int update(int index,int newValue) throws Exception{
		if(index > this.length)
			throw new Exception("index greater than length!");
		LinkedNode currentNode = this.headNode ;
		int pos = 0 ;
		while(null != currentNode  &&  pos < index){
			currentNode = currentNode.getNext() ;
			pos ++ ;
		}
		if(null!=currentNode){
			int oldValue = -1 ;
			LinkedNode targetNode = currentNode.getNext() ;
			if(targetNode == null){
				targetNode = new LinkedNode(newValue);
				currentNode.setNext(targetNode);
			}
			else{
				oldValue = targetNode.getData();
				targetNode.setData(newValue);
			}
			return oldValue;
		}else{
			throw new Exception("NO SUCH ELEMENT");
		}
	}
	
	public void printAllDatas(){
		LinkedNode currentNode = headNode ;
		System.out.print("PRINT-ALL-DATAS: ");
		while(currentNode!=null){
			currentNode = currentNode.getNext() ;
			if(null!=currentNode){
				System.out.print(currentNode.getData());
				System.out.print(' ');
			}
		}
		System.out.println("");
	}
	
	public static void main(String[] args) throws Exception{
		MyListWithLinked linkedLst = new MyListWithLinked() ;
		for(int i=0;i<=10;i++){
//			linkedLst.insertDataInTail(i);
			linkedLst.insertDataInHead(i);
		}
		linkedLst.printAllDatas();

		linkedLst.insertDataByIndex(5, 555);
		linkedLst.printAllDatas();
		
		System.out.println(linkedLst.getDataByIndex(5));
		int index = linkedLst.getIndexByData(555);
		linkedLst.removeDataByIndex(index);
		linkedLst.printAllDatas();

		linkedLst.insertDataByIndex(5, 555);
		linkedLst.removeData(555);
		linkedLst.printAllDatas();
		
		linkedLst.insertDataByIndex(5, 555);
		linkedLst.update(5, 7777);
		linkedLst.printAllDatas();
		
	}

	@Override
	public int getSize() {
		return this.length ;
	}

	@Override
	public void insertData(int data) throws Exception {
		this.insertDataInHead(data) ;
	}

}
