package cn.kane.DatasStructures.tree;

import java.util.ArrayList;
import java.util.List;

public class MyArrayBinaryTree {

	private int capacity ;
	private Integer[] datas ;
	private int counts ;

	public MyArrayBinaryTree(int capacity){
		this.capacity = capacity ;
		this.datas = new Integer[this.capacity] ;
	}
	
	public int findMin(int rootIndex){
		int min = -1 ;
		if(rootIndex<=this.capacity && datas[rootIndex-1]!=null){
			min = datas[rootIndex-1] ;
			int leftChildIndex = 2*rootIndex ;
			if(leftChildIndex<=this.capacity && datas[leftChildIndex-1]!=null)
				min = findMin(leftChildIndex);
		}
		return min ;
	}
	
	public int findMax(int rootIndex){
		int max = -1 ;
		if(rootIndex<=this.capacity && datas[rootIndex-1]!=null){
			max = datas[rootIndex-1] ;
			int rightChildPos = 2*rootIndex +1 ;
			if(rightChildPos<=this.capacity && datas[rightChildPos-1]!=null)
				max = findMax(rightChildPos) ;
		}
		return max ;
	}
	
	public void removeIndex(int data ,int rootindex){
		int index = this.getIndex(data, 1);
		List<Integer> preOrderLst = this.preOrderList(1) ;
		preOrderLst.remove(index);
		this.counts = 0 ;
		for(int node : preOrderLst)
			this.addNode(node);
	}
	
	
	
	public boolean contains(int data,int rootIndex){
		return this.getIndex(data, rootIndex) == -1 ;
	}
	
	public int getIndex(int data,int rootIndex){
		if(rootIndex<=this.capacity && datas[rootIndex-1]!=null){
			if(data == datas[rootIndex-1])
				return rootIndex ;
			
			int childPos = 0 ;
			if(data < datas[rootIndex-1])
				childPos = 2*rootIndex ;
			else
				childPos = 2*rootIndex+1 ;

			if(childPos<=this.capacity && datas[childPos-1]!=null)
				return getIndex(data,childPos) ;
			rootIndex = -1 ;
		}
		
		return rootIndex ;
	}
	
	public void addNode(int data){
		if(this.counts==0){
			this.datas[0] = data ;
			this.counts++ ;
		}else{
			int index = 1 ;
			while(index<=this.capacity){
				if(data == datas[index-1])
					break ;
				
				int nextIndex = 0 ;
				if(data<datas[index-1]){
					nextIndex = 2*index ;
				}else{
					nextIndex = 2*index+1 ;
				}
				
				if(nextIndex>this.capacity)
					expandCapacity(2*capacity);
				
				if(datas[nextIndex-1] == null ){
					datas[nextIndex-1] = data ;
					this.counts++ ;
					break ;
				}
				
				index = nextIndex ;
			}
		}
			
	}
	
	private void expandCapacity(int newCapacity) {
		if(newCapacity<=this.capacity)
			System.out.println("new-capcity must bigger than capacity="+this.capacity);
		Integer[] largerArr = new Integer[newCapacity];
		for(int m=0;m<this.capacity;m++)
			largerArr[m] = datas[m] ; 
		this.datas = largerArr ;
		this.capacity = newCapacity ;
	}
	
	public String orderListInStr(int rootindex,int orderType){
		StringBuffer result = new StringBuffer();
		List<Integer> orderLst = null ;
		switch(orderType){
		case 1: orderLst= this.preOrderList(rootindex);break;
		case 2: orderLst= this.inOrderList(rootindex);break;
		default: orderLst= this.postOrderList(rootindex);
		}
		for(int data : orderLst){
			result.append(data).append(",");
		}
		return result.toString() ;
	}
	
	public List<Integer> preOrderList(int rootindex){
		List<Integer> results = new ArrayList<Integer>() ;
		
		if(rootindex<this.capacity && datas[rootindex-1]!=null){

			results.add(datas[rootindex-1]) ;
			
			int leftChildIndex = 2*rootindex ;
			int rightChildIndex = 2*rootindex + 1 ;
			if(leftChildIndex<=this.capacity && datas[leftChildIndex-1]!=null){
				results.addAll(preOrderList(leftChildIndex));
			}
			if(rightChildIndex<=this.capacity && datas[rightChildIndex-1]!=null){
				results.addAll(preOrderList(rightChildIndex));
			}
		}
		return results ;
	}

	public List<Integer> inOrderList(int rootindex){
		List<Integer> results = new ArrayList<Integer>() ;
		
		if(rootindex<=this.capacity && datas[rootindex-1]!=null){
			int leftChildPos = 2*rootindex ;
			int rightChildPos = 2*rootindex + 1 ;
			
			if(leftChildPos<=this.capacity && datas[leftChildPos-1]!=null)
				results.addAll(inOrderList(leftChildPos)) ;
			results.add(datas[rootindex-1]) ;
			if(rightChildPos<=this.capacity && datas[rightChildPos-1]!=null)
				results.addAll(inOrderList(rightChildPos)) ;
		}
		
		return results ;
	}
	
	public List<Integer> postOrderList(int rootindex){
		List<Integer> results = new ArrayList<Integer>() ;
		
		if(rootindex<=this.capacity && datas[rootindex-1]!=null){
			int leftChildPos = 2*rootindex ;
			int rightChildPos = 2*rootindex + 1 ;
			
			if(leftChildPos<=this.capacity && datas[leftChildPos-1]!=null)
				results.addAll(inOrderList(leftChildPos)) ;
			if(rightChildPos<=this.capacity && datas[rightChildPos-1]!=null)
				results.addAll(inOrderList(rightChildPos)) ;
			results.add(datas[rootindex-1]) ;
		}
		
		return results ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyArrayBinaryTree tree = new MyArrayBinaryTree(5) ;
		tree.addNode(12);
		tree.addNode(7);
		tree.addNode(5);
		tree.addNode(10);
		tree.addNode(15);
		System.out.println(tree.orderListInStr(1,1));
		System.out.println(tree.orderListInStr(1,2));
		System.out.println(tree.orderListInStr(1,3));
		
		System.out.println(tree.getIndex(12,1));
		System.out.println(tree.getIndex(5,1));
		System.out.println(tree.getIndex(15,1));
		
//		tree.addNode(100);
		System.out.println(tree.findMax(1));
		System.out.println(tree.findMin(1));
	}

}
