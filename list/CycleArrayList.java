package cn.kane.DatasStructures.list;

public class CycleArrayList {

	private int[] datas ;
	private int capacity ;
	private int length = 0 ;
	
	public CycleArrayList(){
		this.capacity = 100 ;
		datas = new int[this.capacity] ;
	}
	
	public CycleArrayList(int capacity){
		this.capacity = capacity ;
		datas = new int[this.capacity];
	}
	
	public void cleanList(){
		datas = null ;
		length = 0 ;
	}
	
	public boolean isEmpty(){
		return length == 0 ;
	}
	
	public boolean isFull(){
		return length == capacity-1 ;
	}
	
	public int getSize(){
		return this.length ;
	}
	
	public int getDataByIndex(int index) throws Exception{
		if( index < 0 || index > length-1)
			throw new Exception("outOfIndexException");
		return datas[index] ;
	}
	
	public int getIndexByData(int data){
		for(int i=0 ; i<length ; i++){
			if(datas[i] == data)
				return i ;
		}
		return -1 ;
	}
	
	public void insertData(int data) throws Exception{
		if(this.isFull())
			updateListCapacity(2*this.capacity);
		datas[length++] = data ;
	}
	
	public void insertDataByIndex(int index,int data) throws Exception{
		if(this.isFull())
			updateListCapacity(2*this.capacity);
		if(index>this.capacity-1)
			throw new Exception("index greater than capacity!");
		if(index<length-1){
			for(int i=length;i>index;i--){
				datas[i] = datas[i-1] ;
			}
		}
		datas[index] = data ;
		if(length<index)
			this.length = index+1 ;
		else{
			this.length++ ;
		}
	}

	public void update(int index,int data) throws Exception{
		if( index < 0 || index > length-1)
			throw new Exception("outOfIndexException");
		datas[index] = data ;
	}
	
	public int getOldAndSetNew(int index,int data) throws Exception {
		int oldData = getDataByIndex(index);
		this.update(index, data);
		return oldData ;
	}
	
	public void removeData(int data) throws Exception{
		int index = getIndexByData(data);
		removeDataByIndex(index);
	}
	
	public void removeDataByIndex(int index) throws Exception{
		if(index<0 || index>this.length)
			throw new Exception("outOfIndexException");
		for(;index<length-1;index++){
			datas[index] = datas[index+1] ;
		}
		datas[this.length] = 0 ;
		this.length-- ;
	}
	
	public void printAllDatas(){
		System.out.print("ALL-DATAS: ");
		for(int i =0 ;i<length ;i++){
			System.out.print(datas[i]);
			System.out.print(' ');
		}
		System.out.println("");
	}
	
	private void updateListCapacity(int newCapacity) throws Exception {
		if(newCapacity>0){
			int[] newDatas = new int[newCapacity];
			for(int i = 0; i<(capacity<newCapacity ? capacity : newCapacity);i++){
				newDatas[i] = datas[i] ;
			}
			this.capacity = newCapacity ;
			this.datas = newDatas;
		}else{
			throw new Exception("capacity cannot less than 0.");
		}
			
	}
	
	public MyListWithArray trimToSize(){
		MyListWithArray newArrayLst = new MyListWithArray(this.length) ;
		for(int i=0 ;i <this.length;i++){
			try {
				newArrayLst.insertData(this.getDataByIndex(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newArrayLst ;
	}
	
	public CycleArrayList union(CycleArrayList otherLst) throws Exception{
		if(null==otherLst)
			return this ;
		else{
			CycleArrayList unionArray = new CycleArrayList(this.length) ;
			for(int i=0 ;i <this.length;i++){
				unionArray.insertData(this.getDataByIndex(i));
			}
			
			for(int i=0 ; i<otherLst.getSize();i++){
				int data = otherLst.getDataByIndex(i) ;
				if(unionArray.getIndexByData(data) == -1)
					unionArray.insertData(data);
			}
			
			return unionArray ;
		}
	}
	
	public static void main(String[] args) throws Exception{
		MyListWithArray arrayLst = new MyListWithArray(10) ;
		System.out.println("EMPTY:"+arrayLst.isEmpty());
		
		for(int i=0;i<15;i++){
			arrayLst.insertData(i);
		}
		
		arrayLst.printAllDatas() ;
		
		System.out.println("the tenth data is "+arrayLst.getDataByIndex(9));
		System.out.println(arrayLst.getOldAndSetNew(9, 1000));
		System.out.println("the tenth data is "+arrayLst.getDataByIndex(9));
		arrayLst.removeDataByIndex(8);
		arrayLst.removeData(7);
		arrayLst.printAllDatas() ;
		arrayLst.insertDataByIndex(7, 7777);
		arrayLst.printAllDatas() ;
		arrayLst.insertDataByIndex(19, 2222222);
		arrayLst.printAllDatas() ;
		
		MyListWithArray newArrayLst = new MyListWithArray(10) ;
		for(int i=0;i<=10;i++){
			newArrayLst.insertData(i);
		}
		
		newArrayLst = newArrayLst.union(arrayLst);
		newArrayLst.printAllDatas() ;
		
	}
	
}
