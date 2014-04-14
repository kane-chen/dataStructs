package cn.kane.DatasStructures.stack;

public class DoubleStackOfSharedArray {

	private int capacity ;
	private int[] datas ;
	private int top1 ;//stack1's top-position
	private int top2 ;//stack2's top-position
	
	public DoubleStackOfSharedArray(int capacity){
		this.capacity = capacity ;
		this.datas = new int[this.capacity] ;
		this.top1 =  0 ;
		this.top2 = this.capacity-1 ;
	}
	
	public boolean isEmpty(boolean order) {
		if(order)
			return top1 ==  0 ;
		else
			return top2 == capacity-1 ;
	}

	public void clean(boolean order) {
		if(order)
			this.top1 = 0 ;
		else
			this.top2 = this.capacity -1 ;
	}

	public int getSize(boolean order) {
		if(order)
			return top1 ;
		else
			return this.capacity-1-top2;
	}

	public int getHead(boolean order) throws Exception {
		if(isEmpty(order))
			throw new Exception("stack is empty!");
		if(order)
			return datas[top1-1] ;
		else
			return datas[this.capacity-1-top2] ;
	}

	public void push(int data,boolean order) throws Exception {
		if(this.top1 == top2+1)
			throw new Exception("double-stack was full!!!");
		if(order)
			datas[top1++] = data ;
		else
			datas[top2--] = data ;
	}

	public int pop(boolean order) throws Exception {
		if(this.isEmpty(order))
			throw new Exception("stack was empty!!!");
		if(order)
			return datas[--top1] ;
		else
			return datas[++top2] ;
	}

	public void printAllDatas() {
		System.out.print("PRINT-ALL-DATAS: ");
		for(int i =0 ;i<this.capacity;i++){
			System.out.print(datas[i]);
			System.out.print(' ');
		}
		System.out.println("");
	}

	
	public static void main(String[] args) throws Exception{
		DoubleStackOfSharedArray sharedStack = new DoubleStackOfSharedArray(15);
		System.out.println(sharedStack.isEmpty(false));
		for(int i=0;i<6;i++){
			sharedStack.push(i, true);
			sharedStack.push(i, false);
		}
		sharedStack.printAllDatas() ;
		System.out.println(sharedStack.getHead(true));
		System.out.println(sharedStack.pop(true));
		sharedStack.printAllDatas();
		for(int i=11;i<15;i++)
			sharedStack.push(i, true);
		sharedStack.printAllDatas();
	}
}
