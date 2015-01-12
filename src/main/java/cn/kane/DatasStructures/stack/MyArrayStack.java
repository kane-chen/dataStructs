package cn.kane.DatasStructures.stack;

public class MyArrayStack implements MyStackInterface {

	private int capacity ;
	private int length ;
	private int[] datas ;
	
	public MyArrayStack(int capacity){
		this.capacity = capacity ;
		this.datas = new int[this.capacity];
	}
	
	@Override
	public boolean isEmpty() {
		return this.length == 0 ;
	}

	@Override
	public void clean() {
		this.length = 0 ;
	}

	@Override
	public int getSize() {
		return this.length ;
	}

	@Override
	public int getHead() throws Exception{
		if(this.isEmpty())
			throw new Exception("stack is empty");
		
		return datas[length-1];
	}

	@Override
	public void push(int data) throws Exception{
		if(this.length>=this.capacity-1)
			throw new Exception("stack is full!!");
		datas[length++] = data ;
	}

	@Override
	public int pop() throws Exception {
		if(isEmpty())
			throw new Exception("stack is empty!");
		this.length-- ;
		return datas[length];
	}

	@Override
	public void printAllDatas() {
		System.out.print("PRINT-ALL-DATAS: ");
		for(int i=0 ; i<length; i++){
			System.out.print(datas[i]);
			System.out.print(' ');
		}
		System.out.println("");
	}

	public static void main(String[] args) throws Exception{
		MyStackInterface stack = new MyArrayStack(20);
		System.out.println(stack.isEmpty());
		for(int i=0;i<10;i++){
			stack.push(i);
		}
		stack.printAllDatas() ;
		stack.pop();
		System.out.println(stack.getHead());
		stack.printAllDatas() ;
		stack.clean() ;
		stack.printAllDatas() ;
	}
}
