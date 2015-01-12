package cn.kane.DatasStructures.tree;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedBinaryTree {

	class LinkedNode{
		int datas ;
		LinkedNode leftChild ;
		LinkedNode rightChild ;
		int leftFlag = 0 ;
		int rightFlag = 0 ;
		public LinkedNode(int data){
			this.datas = data ;
		}
	}
	
	private LinkedNode root ;
	private int length ;
	private LinkedNode headNode ;
	private LinkedNode prevNode ;
	
	public boolean isEmpty(){
		return this.length == 0 ;
	}
	
	public void addNode(int data){
		LinkedNode newNode = new LinkedNode(data);
		if(this.isEmpty()){
			root = newNode ;
			this.length++ ;
		}else{
			LinkedNode curNode = root ;
			while(root!=null){
				if(data == curNode.datas)
					break ;
				LinkedNode childNode = null ;
				if(data < curNode.datas)
					childNode = curNode.leftChild ;
				else
					childNode = curNode.rightChild ;
				
				if(null==childNode){
					if(data < curNode.datas)
						curNode.leftChild = newNode ;
					else
						curNode.rightChild = newNode ;
					
					this.length++ ;
					break ;
				}else{
					curNode = childNode ;
				}
					
			}
		}
	}
	
	public String orderListInString(int orderType){
		List<LinkedNode> result = null ;
		switch(orderType){
		case 1 : result = this.preOrderList(root) ; break ;
		case 2 : result = this.inOrderList(root) ; break ;
		default : result = this.postOrderList(root) ; break ;
		}
		
		StringBuffer sb = new StringBuffer();
		for(LinkedNode node : result)
			sb.append(node.datas).append(",");
		return  sb.toString();
	}
	
	private List<LinkedNode> preOrderList(LinkedNode rootNode) {
		List<LinkedNode> result = new ArrayList<LinkedNode>() ;
		if(null != rootNode){
			result.add(rootNode);
			if(null!=rootNode.leftChild && 0==rootNode.leftFlag)
				result.addAll(this.preOrderList(rootNode.leftChild)) ;
			if(null!=rootNode.rightChild && 0==rootNode.rightFlag)
				result.addAll(this.preOrderList(rootNode.rightChild)) ;
		}
		
		return result ;
	}
	private List<LinkedNode> inOrderList(LinkedNode rootNode) {
		List<LinkedNode> result = new ArrayList<LinkedNode>() ;
		if(null != rootNode){
			if(null!=rootNode.leftChild && rootNode.leftFlag == 0)
				result.addAll(this.inOrderList(rootNode.leftChild)) ;
			result.add(rootNode);
			if(null!=rootNode.rightChild && rootNode.rightFlag == 0)
				result.addAll(this.inOrderList(rootNode.rightChild)) ;
		}
		
		return result ;
	}
	private List<LinkedNode> postOrderList(LinkedNode rootNode) {
		List<LinkedNode> result = new ArrayList<LinkedNode>() ;
		if(null != rootNode){
			if(null!=rootNode.leftChild)
				result.addAll(this.postOrderList(rootNode.leftChild)) ;
			if(null!=rootNode.rightChild)
				result.addAll(this.postOrderList(rootNode.rightChild)) ;
			result.add(rootNode);
		}
		
		return result ;
	}

	
	public void threadBinaryTree(int orderType){
		if(this.isEmpty())
			System.out.println("tree was empty!");
		else{
			this.headNode = new LinkedNode(-1) ;
			this.prevNode = headNode ;
			switch(orderType){
			case 1: this.preOrderThreadedBinaryTree(root) ; break ;
			case 2: this.inOrderThreadedBinaryTree(root) ; break ;
			default: this.postOrderThreadedBinaryTree(root) ; break ;
			}
			this.headNode.leftFlag = 1 ;
			this.headNode.leftChild = prevNode ;
			prevNode.rightFlag = 1 ;
			prevNode.rightChild = this.headNode ;
		}
	}
	
	private void preOrderThreadedBinaryTree(LinkedNode rootNode) {
		if(rootNode != null){
			//validate-current-node
			if(rootNode.leftChild == null){
				rootNode.leftFlag = 1 ;
				rootNode.leftChild = prevNode ;
			}
			if(prevNode!=null && prevNode.rightChild==null){
				prevNode.rightFlag = 1 ;
				prevNode.rightChild = rootNode ;
			}
			prevNode = rootNode ;
			//left-child-threading
			if(rootNode.leftFlag == 0)
				this.preOrderThreadedBinaryTree(rootNode.leftChild);
			//right-child-threading
			if(rootNode.rightFlag == 0)
				this.preOrderThreadedBinaryTree(rootNode.rightChild);
		}
	}
	private void inOrderThreadedBinaryTree(LinkedNode rootNode) {
		if(rootNode != null){
			//left-child-threading
			this.inOrderThreadedBinaryTree(rootNode.leftChild);
			//current-node
			if(rootNode.leftChild == null){
				rootNode.leftFlag = 1 ;
				rootNode.leftChild = prevNode ;
			}
			if(prevNode!=null && prevNode.rightChild==null){
				prevNode.rightFlag = 1 ;
				prevNode.rightChild = rootNode ;
			}
			prevNode = rootNode ;
			//right-child-threading
			if(rootNode.rightFlag == 0)
				this.inOrderThreadedBinaryTree(rootNode.rightChild);
		}
	}
	private void postOrderThreadedBinaryTree(LinkedNode rootNode) {
		if(rootNode != null){
			//left-child-threading
			this.postOrderThreadedBinaryTree(rootNode.leftChild);
			//right-child-threading
			this.postOrderThreadedBinaryTree(rootNode.rightChild);
			//current-node
			if(rootNode.leftChild == null){
				rootNode.leftFlag = 1 ;
				rootNode.leftChild = prevNode ;
			}
			if(prevNode!=null && prevNode.rightChild==null){
				prevNode.rightFlag = 1 ;
				prevNode.rightChild = rootNode ;
			}
			prevNode = rootNode ;
		}
	}

	public List<LinkedNode> visitByThreaded(){
		List<LinkedNode> lst = new ArrayList<LinkedNode>() ;
		if(this.isEmpty())
			System.out.println("tree was empty");
		else{
			LinkedNode curNode = headNode ;
			boolean inited = false ;
			while(inited && curNode!=headNode){
				curNode = curNode.leftChild ;
				lst.add(curNode);
				inited = true ;
			}
		}
		return lst ;
	}
	
	public List<LinkedNode> visitReverseByThreaded(){
		List<LinkedNode> list = new ArrayList<LinkedNode>() ;
		LinkedNode curNode = this.headNode ;
		boolean inited = false ;
		while(inited && curNode!=headNode){
			curNode = curNode.rightChild;
			list.add(curNode);
			inited = true ;
		}
		return list ;
	}
	
	public String getOrderStringByThreaded(boolean order){
		List<LinkedNode> orderLst ;
		if(order)
			orderLst = this.visitByThreaded() ;
		else
			orderLst = this.visitReverseByThreaded() ;
		StringBuffer sb = new StringBuffer() ;
		for(LinkedNode node : orderLst)
			sb.append(node.datas).append(",");
		return sb.toString() ;
	}
	
	public LinkedNode getMin(){
		LinkedNode curNode = this.headNode.rightChild ;
		boolean inited = false ;
		while(!inited || curNode.leftFlag == 0){
			curNode = curNode.leftChild ;
			inited = true ;
		}
		return curNode ;
	}
	
	public LinkedNode getMax(){
		LinkedNode curNode = this.headNode.rightChild ;
		boolean inited = false ;
		while(!inited || curNode.rightFlag == 0){
			curNode = curNode.rightChild;
			inited = true ;
		}
		return curNode ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyLinkedBinaryTree tree = new MyLinkedBinaryTree() ;
		tree.addNode(10);
		tree.addNode(5);
		tree.addNode(7);
		tree.addNode(17);
		tree.addNode(3);
		tree.addNode(2);
		System.out.println(tree.orderListInString(1));
		System.out.println(tree.orderListInString(2));
		System.out.println(tree.orderListInString(3));
		
		tree.threadBinaryTree(2);
//		tree.threadBinaryTree(3);
		System.out.println(tree.getOrderStringByThreaded(true));
		System.out.println(tree.getOrderStringByThreaded(false));
		tree.threadBinaryTree(1);
		System.out.println(tree.getMin().datas);
		System.out.println(tree.getMax().datas);
	}

}
