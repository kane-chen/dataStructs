package cn.kane.DatasStructures.tree;

import java.util.Random;

public class AvlTree {

	class AvlNode{
		int datas ;
		AvlNode leftChild ;
		AvlNode rightChild ;
		int depth = 0 ;
		AvlNode(int data){
			this.datas = data ;
		}
	}
	
	private AvlNode root ;
	private int length ;
	
	public boolean isEmpty(){
		return this.length == 0 ;
	}
	
	public void printTree(int orderType){
		switch(orderType){
		case 1 : this.preOrderVisit(root) ; break ;
		case 2 : this.inOrderVisit(root) ; break ;
		default : this.postOrderVisit(root) ; 
		}
	}
	
	private void preOrderVisit(AvlNode rootNode) {
		if(null != rootNode){
			StringBuffer sb = new StringBuffer() ;
			sb.append(null==rootNode.leftChild ? "(-1)" : rootNode.leftChild.datas) ;
			sb.append("-") ;
			sb.append(rootNode.datas);
			sb.append("-") ;
			sb.append(null==rootNode.rightChild ? "(-1)" : rootNode.rightChild.datas) ;
			
			System.out.println(sb);
			
			this.preOrderVisit(rootNode.leftChild) ;
			this.preOrderVisit(rootNode.rightChild) ;
		}
	}
	private void inOrderVisit(AvlNode rootNode) {
		if(null != rootNode){
			this.inOrderVisit(rootNode.leftChild) ;

			StringBuffer sb = new StringBuffer() ;
			sb.append(null==rootNode.leftChild ? "(-1)" : rootNode.leftChild.datas) ;
			sb.append("-") ;
			sb.append(rootNode.datas);
			sb.append("-") ;
			sb.append(null==rootNode.rightChild ? "(-1)" : rootNode.rightChild.datas) ;
			System.out.println(sb);
			
			this.inOrderVisit(rootNode.rightChild) ;
		}
	}
	private void postOrderVisit(AvlNode rootNode) {
		if(null != rootNode){
			this.postOrderVisit(rootNode.leftChild) ;
			this.postOrderVisit(rootNode.rightChild) ;
			
			StringBuffer sb = new StringBuffer() ;
			sb.append(null==rootNode.leftChild ? "(-1)" : rootNode.leftChild.datas) ;
			sb.append("-") ;
			sb.append(rootNode.datas);
			sb.append("-") ;
			sb.append(null==rootNode.rightChild ? "(-1)" : rootNode.rightChild.datas) ;
			System.out.println(sb);
		}
	}

	public AvlNode insert(int data){
		this.root = this.insertNode(data,root) ;
		return  this.root;
	}
	
	private AvlNode insertNode(int data, AvlNode rootNode) {
		if(null == rootNode){
			rootNode = new AvlNode(data) ;
			this.length++ ;
		}else{
			if(data == rootNode.datas)
				;//do-nothing
			else if(data < rootNode.datas){
				rootNode.leftChild = this.insertNode(data,rootNode.leftChild) ;
				if(this.depth(rootNode.leftChild) - this.depth(rootNode.rightChild) == 2){
					if(data < rootNode.leftChild.datas) //LL
						rootNode = this.rightRotate(rootNode) ;
					else //LR
						rootNode = this.leftRightRotate(rootNode) ;
				}
			} else{//data > rootNode.datas
				rootNode.rightChild = this.insertNode(data, rootNode.rightChild) ;
				if(this.depth(rootNode.rightChild) - this.depth(rootNode.leftChild) == 2){
					if(data > rootNode.rightChild.datas)//RR
						rootNode = this.leftRotate(rootNode) ;
					else //RL
						rootNode = this.rightLeftRotate(rootNode) ;
				}
			}
				
		}
		
		rootNode.depth = Math.max(this.depth(rootNode.leftChild), this.depth(rootNode.rightChild)) + 1 ;
		return rootNode;
	}

	private AvlNode rightLeftRotate(AvlNode rootNode) {
		if(null == rootNode)
			return null;
		else{
			rootNode.rightChild = this.rightRotate(rootNode.rightChild) ;
			return this.leftRotate(rootNode) ;
		}
	}

	private AvlNode leftRightRotate(AvlNode rootNode) {
		rootNode.leftChild = this.leftRotate(rootNode.leftChild) ;
		return this.rightRotate(rootNode);
	}

	private AvlNode leftRotate(AvlNode rootNode) {
		if(null == rootNode)
			return null;
		else{
			AvlNode rightRoot = rootNode.rightChild ;
			rootNode.rightChild = rightRoot.leftChild ;
			rightRoot.leftChild = rootNode ;
			rootNode.depth = Math.max(this.depth(rootNode.leftChild), this.depth(rootNode.rightChild)) + 1 ;
			rightRoot.depth = Math.max(rootNode.depth, this.depth(rightRoot.rightChild)) ;
			return rightRoot ;
		}
	}

	private AvlNode rightRotate(AvlNode rootNode) {
		if(null == rootNode)
			return null;
		else{
			AvlNode leftRoot = rootNode.leftChild ;
			rootNode.leftChild = leftRoot.rightChild ;
			leftRoot.rightChild = rootNode ;
			rootNode.depth = Math.max(this.depth(rootNode.leftChild), this.depth(rootNode.rightChild)) + 1 ;
			leftRoot.depth = Math.max(this.depth(leftRoot.leftChild), rootNode.depth) + 1 ;
			return leftRoot ;
		}
	}

	private int depth(AvlNode node) {
		return null == node ? -1 : node.depth;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AvlTree tree = new AvlTree() ;
		
//		int[] llArr = {5,3,1} ; //LL
//		for(int num:llArr)
//			tree.insert(num);
//		
//		tree.printTree(1) ;

//		int[] rrArr = {1,3,5} ; //RR
//		for(int num:rrArr)
//			tree.insert(num);
//		
//		tree.printTree(1) ;
		
//		int[] lrArr = {1,2,4,5,6,3} ; //LR
//		for(int num : lrArr)
//			tree.insert(num) ;
//		tree.printTree(1) ;

//		int[] lrArr = {2,3,4,5,8,7} ; //RL
//		for(int num : lrArr)
//			tree.insert(num) ;
//		tree.printTree(1) ;
		Random ran = new Random();
		int length = ran.nextInt(10)+10 ;
		for(int i=0 ;i<length;i++)
			tree.insert(ran.nextInt(100)) ;
		tree.printTree(1);
		
	}

}
