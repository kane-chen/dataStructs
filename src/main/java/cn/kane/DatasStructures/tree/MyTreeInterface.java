package cn.kane.DatasStructures.tree;

abstract class Node{
	int datas ;
}

public interface MyTreeInterface {

	boolean isEmpty() ;
	
	void clean() ;
	
	int depth() ;
	
	Node root() ;
	
	Node parent(Node node) ;
	
	Node leftChild(Node node) ;
	
	Node rightChild(Node node) ;
	
	Node leftSibling(Node node) ;
	
	Node rightSibling(Node node) ;
	
	Node[] prevOrderList();
	
	Node[] inOrderList();
	
	Node[] postOrderList() ;
	
	Node[] levelOrderList() ;
}
