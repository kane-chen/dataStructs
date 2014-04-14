package cn.kane.DatasStructures;

public class MyHashTable {

	class LinkedNode{
		int datas ;
		LinkedNode nextNode ;
		public LinkedNode(int data){
			this.datas = data ;
		}
	}
	
	private int capacity = 7 ;
	private LinkedNode[] nodes ;
	
	public MyHashTable(int capacity){
		this.capacity = capacity ;
		nodes = new LinkedNode[this.capacity] ;
	}
	
	public void put(int data){
		int pos = this.hash(data) ;
		if(null==nodes[pos]){
			nodes[pos] = new LinkedNode(data)  ;
		}else{
			LinkedNode curNode = nodes[pos] ;
			while(true){
				if(curNode.datas == data)
					break;
				if(curNode.nextNode == null)
					break ;
				else
					curNode = curNode.nextNode ;
			}
			curNode.nextNode = new LinkedNode(data) ;
		}
	}
	
	public LinkedNode get(int data){
		int pos = this.hash(data) ;
		if(null == nodes[pos])
			return null ;
		else{
			LinkedNode curNode = nodes[pos] ;
			while(curNode!=null){
				if(curNode.datas == data)
					return curNode ;
				curNode = curNode.nextNode ;
			}
			return null ;
		}
	}
	
	private int hash(int data){
		return data % this.capacity ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyHashTable hashTable = new MyHashTable(7) ;
		for(int i=1;i<=10;i++)
			hashTable.put(i) ;
		LinkedNode node9 = hashTable.get(9);
		System.out.println(node9);
		LinkedNode node2 = hashTable.get(2);
		System.out.println(node2);
	}

}
