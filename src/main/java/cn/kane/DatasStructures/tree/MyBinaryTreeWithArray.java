package cn.kane.DatasStructures.tree;

import java.util.ArrayList;
import java.util.List;

public class MyBinaryTreeWithArray {

	class ArrayNode extends Node{
		int leftChildArrayNodePos = -1 ;
		int rightChildArrayNodePos = -1 ;
		ArrayNode(int data){
			this.datas = data ;
		}
		ArrayNode(int data,int leftChildArrayNodePos,int rightChildArrayNodePos){
			this.datas = data ;
			this.leftChildArrayNodePos = leftChildArrayNodePos ;
			this.rightChildArrayNodePos = rightChildArrayNodePos ;
		}
	}

	private int capacity = 1024 ;
	private ArrayNode[] nodes ;
	private int length ;
	
	public MyBinaryTreeWithArray(int capacity){
		this.capacity = capacity ;
		nodes = new ArrayNode[this.capacity] ;
		
//		for(int i=0;i<(int)(Math.log(maxData)/Math.log(2));i++){
//			int nodecounts = (int)Math.pow(2, i) ;
//			for(int j=0;j<nodecounts;j++){
//				int pos = nodecounts+j ; 
//				nodes[pos].datas = nodecounts+j ;
//				if(2*pos<=capacity)
//					nodes[pos].leftChildArrayNodePos = 2*pos ;
//				if(2*pos+1<=capacity)
//					nodes[pos].rightChildArrayNodePos = 2*pos+1 ;
//			}
//		}
//		root = nodes[0] ;
	}
	
	public boolean isEmpty(){
		return this.length == 0 ;
	}
	
	
	private void addNode(int data){
		ArrayNode newNode = new ArrayNode(data);
		if(this.isEmpty()){
			nodes[0] = newNode ;
			this.length++ ;
		}else{
			int curIndex = 0 ;
			while(nodes[curIndex]!=null){
				if(nodes[curIndex].datas == data)
					break ;
				int childIndex = -1 ;
				if(data<nodes[curIndex].datas){
					childIndex = nodes[curIndex].leftChildArrayNodePos ;
				}else{
					childIndex = nodes[curIndex].rightChildArrayNodePos ;
				}
				
				if(childIndex == -1){
					if(this.length >= this.capacity)
						this.expandsCapacity(2*this.capacity) ;
					if(data<nodes[curIndex].datas){
						nodes[curIndex].leftChildArrayNodePos = this.length ;
					}else{
						nodes[curIndex].rightChildArrayNodePos = this.length ;
					}
					nodes[this.length] = newNode ;
					this.length++ ;
					break ;
				}else{
					curIndex = childIndex ;
				}
			}
		}
	}

	public String orderList(int orderType){
		StringBuffer sb = new StringBuffer() ;
		List<Integer> result = null ;
		switch(orderType){
		case 1:result = this.preOrderList(0) ; break ;
		case 2:result = this.inOrderList(0) ; break ;
		default : result = this.postOrderList(0) ; break ;
		}
		for(int data:result)
			sb.append(data).append(",");
		return sb.toString();
	}
	
	private List<Integer> preOrderList(int rootIndex){
		List<Integer> result = new ArrayList<Integer>() ;
		if(rootIndex<this.length && this.nodes[rootIndex]!=null){
			ArrayNode curNode = this.nodes[rootIndex] ;
			result.add(curNode.datas) ;
			
			int left = curNode.leftChildArrayNodePos ;
			int right = curNode.rightChildArrayNodePos ;
			if(left<this.length && left!=-1)
				result.addAll(this.preOrderList(left));
			if(right<this.length && right!=-1)
				result.addAll(this.preOrderList(right));
		}
		return result ;
	}

	private List<Integer> inOrderList(int rootIndex){
		List<Integer> result = new ArrayList<Integer>() ;
		if(rootIndex<this.length && this.nodes[rootIndex]!=null){
			ArrayNode curNode = this.nodes[rootIndex] ;
			
			int left = curNode.leftChildArrayNodePos ;
			int right = curNode.rightChildArrayNodePos ;
			if(left<this.length && left!=-1)
				result.addAll(this.inOrderList(left));
			result.add(curNode.datas) ;
			if(right<this.length && right!=-1)
				result.addAll(this.inOrderList(right));
		}
		return result ;
	}

	private List<Integer> postOrderList(int rootIndex){
		List<Integer> result = new ArrayList<Integer>() ;
		if(rootIndex<this.length && this.nodes[rootIndex]!=null){
			ArrayNode curNode = this.nodes[rootIndex] ;
			
			int left = curNode.leftChildArrayNodePos ;
			int right = curNode.rightChildArrayNodePos ;
			if(left<this.length && left!=-1)
				result.addAll(this.postOrderList(left));
			if(right<this.length && right!=-1)
				result.addAll(this.postOrderList(right));
			result.add(curNode.datas) ;
		}
		return result ;
	}
	
	private void expandsCapacity(int newCapacity) {
		if(newCapacity<this.capacity)
			System.out.println("newCapacity cannot less than capacity="+this.capacity);
		ArrayNode[] biggerArr = new ArrayNode[newCapacity] ;
		for(int i=0;i<this.capacity;i++)
			biggerArr[i] = nodes[i] ;
		
		this.capacity = newCapacity ;
		this.nodes = biggerArr ;
	}
	
	public static void main(String[] args){
		MyBinaryTreeWithArray tree = new MyBinaryTreeWithArray(10) ;
		tree.addNode(10);
		tree.addNode(5);
		tree.addNode(7);
		tree.addNode(17);
		tree.addNode(3);
		tree.addNode(2);
		System.out.println(tree.orderList(1));
		System.out.println(tree.orderList(2));
		System.out.println(tree.orderList(3));
	}
}
