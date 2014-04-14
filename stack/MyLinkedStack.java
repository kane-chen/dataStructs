package cn.kane.DatasStructures.stack;

public class MyLinkedStack implements MyStackInterface {

	class LinkedNode{
		private int data ;
		private LinkedNode prevNode ;
		LinkedNode(int data){
			this.data = data ;
		}
	}
	
	private int length ;
	private LinkedNode topNode ;
	
	public MyLinkedStack(){
		topNode = new LinkedNode(-1) ;
	}
	
	@Override
	public boolean isEmpty() {
		return this.length == 0 ;
	}

	@Override
	public void clean() {
		this.length = 0 ;
		topNode.prevNode = null ;
	}

	@Override
	public int getSize() {
		return this.length ;
	}

	@Override
	public int getHead() throws Exception {
		if(this.isEmpty())
			throw new Exception("stack was empty!");
		return topNode.prevNode.data;
	}

	@Override
	public void push(int data) throws Exception {
		LinkedNode newNode = new LinkedNode(data) ;
		newNode.prevNode = topNode.prevNode ;
		topNode.prevNode = newNode ;
		this.length++ ;
	}

	@Override
	public int pop() throws Exception {
		if(this.isEmpty())
			throw new Exception("stack was empty!");
		LinkedNode targetNode = topNode.prevNode;
		topNode.prevNode = targetNode.prevNode ;
		this.length-- ;
		return targetNode.data ;
	}

	@Override
	public void printAllDatas() {
		System.out.print("PRINT-ALL-DATAS: ");
		LinkedNode curNode = topNode ;
		int pos = 0 ;
		while(curNode!=null && pos<this.length){
			curNode = curNode.prevNode ;
			pos++ ;
			System.out.print(curNode.data);
			System.out.print(' ');
		}
		System.out.println("");
		if(pos != this.length)
			System.out.println("stack was broken!");
	}

	public static void main(String[] args) throws Exception{
		MyLinkedStack stack = new MyLinkedStack();
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
