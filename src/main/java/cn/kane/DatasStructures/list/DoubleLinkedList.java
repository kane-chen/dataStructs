package cn.kane.DatasStructures.list;

public class DoubleLinkedList implements MyListInterface{

	class DoubleLinkedNode{
		private int datas ;
		private DoubleLinkedNode prevNode ;
		private DoubleLinkedNode nextNode ;
		public DoubleLinkedNode(int data){
			this(data,null,null);
		}
		public DoubleLinkedNode(int data,DoubleLinkedNode prevNode,DoubleLinkedNode nextNode){
			this.datas = data ;
			this.prevNode = prevNode ;
			this.nextNode = nextNode ;
		}
	}

	private int length ;
	private DoubleLinkedNode headNode ;
	private DoubleLinkedNode tailNode ;
	
	public DoubleLinkedList(){
		headNode = new DoubleLinkedNode(-1);
		tailNode = new DoubleLinkedNode(-1) ;
		headNode.prevNode = null ;// cycle-linked-list,head.prevNode = tailNode;
		headNode.nextNode = tailNode ;
		tailNode.prevNode = headNode ;
		tailNode.nextNode = null ;// cycle-linked-list,tail.nextNode = headNode;
	}
	
	@Override
	public boolean isEmpty() {
		return this.length == 0 ;
	}

	@Override
	public int getSize() {
		return this.length ;
	}

	@Override
	public int getDataByIndex(int index) throws Exception {
		if(index<0 || index>length-1)
			throw new Exception("index must be [0~"+length+")");
		if(index<length/2){
			DoubleLinkedNode currentNode = headNode ;
			int pos = 0 ;
			while(null!=currentNode && pos<=index){
				currentNode = currentNode.nextNode ;
				pos++ ;
			}
			
			if(pos!=index+1)
				throw new Exception("list was broken");
			
			return currentNode.datas ;
		}else{
			DoubleLinkedNode currentNode = tailNode ;
			int pos = 0 ;
			while(null!=currentNode && pos < length-index){
				currentNode = currentNode.prevNode ;
				pos++ ;
			}
			
			if(pos!=length-index)
				throw new Exception("list was broken");
			
			return currentNode.datas ;
		}
	}

	@Override
	public int getIndexByData(int data) {
		int index = -1 ;
		DoubleLinkedNode currentNode = headNode ;
		int pos = 0 ;
		while(currentNode!=null && pos < length){
			currentNode = currentNode.nextNode ;
			if(data == currentNode.datas){
				index = pos ;
				break ;
			}
			
			pos++ ;
		}
		return index ;
	}

	@Override
	public void insertData(int data) throws Exception {
		insertDataInHead(data);
	}

	private void insertDataInHead(int data) {
		DoubleLinkedNode newNode = new DoubleLinkedNode(data) ;
		
		newNode.nextNode = headNode.nextNode ;
		headNode.nextNode.prevNode = newNode ;
		
		headNode.nextNode = newNode ;
		newNode.prevNode = headNode ;
		
		this.length++ ;
	}
	
	void insertDataInTail(int data){
		DoubleLinkedNode newNode = new DoubleLinkedNode(data) ;

		newNode.prevNode = tailNode.prevNode ;
		tailNode.prevNode.nextNode = newNode ;
		
		newNode.nextNode = tailNode ;
		tailNode.prevNode = newNode ;
		
		this.length++ ;
	}

	@Override
	public void insertDataByIndex(int index, int data) throws Exception {
		if(index<0 || index>length)
			throw new Exception("index must be [0~"+length+"]");
		if(index<length/2){
			DoubleLinkedNode prevNode = headNode;
			int pos = 0 ;
			while(prevNode!=null && pos<index){
				prevNode = prevNode.nextNode;
				pos++ ;
			}
			
			if(pos!=index)
				throw new Exception("list was broken");
			
			DoubleLinkedNode newNode = new DoubleLinkedNode(data);
			
			newNode.prevNode = prevNode ;
			newNode.nextNode = prevNode.nextNode ;
			prevNode.nextNode = newNode ;
			newNode.nextNode.prevNode = newNode ;
			
			this.length++ ;
		}else{
			DoubleLinkedNode orgIndexNode = tailNode;
			int pos = 0 ;
			while(orgIndexNode!=null && pos<length-index){
				orgIndexNode = orgIndexNode.prevNode;
				pos++ ;
			}
			
			if(pos!=length-index)
				throw new Exception("list was broken");
			
			DoubleLinkedNode newNode = new DoubleLinkedNode(data);
			
			newNode.nextNode = orgIndexNode ;
			newNode.prevNode = orgIndexNode.prevNode ;
			orgIndexNode.prevNode.nextNode = newNode ;
			orgIndexNode.prevNode = newNode ;
			
			this.length++ ;
		}
	}

	@Override
	public int update(int index, int data) throws Exception {
		if(index<0 || index>length-1)
			throw new Exception("index must be [0~"+length+")");
		if(index<length/2){
			DoubleLinkedNode currentNode = headNode ;
			int pos = 0 ;
			while(currentNode!=null && pos<=index){
				currentNode = currentNode.nextNode ;
				pos++;
			}
			if(pos!=index+1)
				throw new Exception("list was broken");
			int result = currentNode.datas ;
			currentNode.datas = data ;
			return result ;
		}else{
			DoubleLinkedNode currentNode = tailNode ;
			int pos = 0 ;
			while(currentNode!=null && pos<length-index){
				currentNode = currentNode.prevNode ;
				pos++;
			}
			if(pos!=length-index)
				throw new Exception("list was broken");
			int result = currentNode.datas ;
			currentNode.datas = data ;
			return result ;
		}
	}

	@Override
	public int removeData(int data) throws Exception {
		int removed = 0 ;
		DoubleLinkedNode currentNode = headNode ;
		int pos = 0 ;
		while(currentNode!=null && pos<length){
			DoubleLinkedNode targetNode = currentNode.nextNode ;
			if(data == targetNode.datas){
				currentNode.nextNode = targetNode.nextNode ;
				targetNode.nextNode.prevNode = currentNode ;
				this.length-- ;
				removed++ ;
			}else{
				currentNode = targetNode ;
			}
			pos++;
		}
		return removed ;
	}

	@Override
	public void removeDataByIndex(int index) throws Exception {
		if(index<0 || index>length-1)
			throw new Exception("index must be [0~"+length+")");
		
		if(index<length/2){
			DoubleLinkedNode currentNode = headNode ;
			int pos = 0 ;
			while(currentNode!=null && pos<=index){
				currentNode = currentNode.nextNode ;
				pos++ ;
			}
			if(pos!=index+1)
				throw new Exception("list was broken!");
			currentNode.prevNode.nextNode = currentNode.nextNode ;
			currentNode.nextNode.prevNode = currentNode.prevNode ;
			this.length-- ;
		}else{
			DoubleLinkedNode currentNode = tailNode ;
			int pos = 0 ;
			while(currentNode!=null && pos<length-index){
				currentNode = currentNode.prevNode ;
				pos++ ;
			}
			if(pos != length-index)
				throw new Exception("list was broken!");
			currentNode.prevNode.nextNode = currentNode.nextNode ;
			currentNode.nextNode.prevNode = currentNode.prevNode ;
			this.length-- ;
		}
	}

	@Override
	public void printAllDatas() {
		System.out.print("PRINT-ALL-DATAS: ");
		DoubleLinkedNode currentNode = headNode ;
		int pos = 0 ;
		while(currentNode!=null && pos<length){
			currentNode = currentNode.nextNode ;
			pos++;
			System.out.print(currentNode.datas);
			System.out.print(' ');
		}
		System.out.println("");
	}
	
	
	public static void main(String[] args) throws Exception{
		DoubleLinkedList linkedLst = new DoubleLinkedList() ;
		for(int i=0;i<=10;i++){
			linkedLst.insertDataInTail(i);
//			linkedLst.insertDataInHead(i);
		}
		linkedLst.printAllDatas();

		linkedLst.insertDataByIndex(5, 555);
		linkedLst.printAllDatas();
		
		System.out.println(linkedLst.getDataByIndex(5));
		int index = linkedLst.getIndexByData(555);
		linkedLst.removeDataByIndex(index);
		linkedLst.printAllDatas();

		System.out.println("-------------------------------");
		
		linkedLst.insertDataByIndex(5, 555);
		linkedLst.removeData(555);
		linkedLst.printAllDatas();
		
		linkedLst.insertDataByIndex(5, 555);
		linkedLst.update(5, 7777);
		linkedLst.printAllDatas();
	}
	
}
