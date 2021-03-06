package cn.kane.DatasStructures.tree;
public class BinaryTree {
    // Root node pointer. Will be null for an empty tree.
    private Node root;

    /*
     * --Node-- The binary tree is built using this nested node class. Each node
     * stores one data element, and has left and right sub-tree pointer which
     * may be null. The node is a "dumb" nested class -- we just use it for
     * storage; it does not have any methods.
     */
    private static class Node {
        Node left;
        Node right;
        int data;

        Node(int newData) {
            left = null;
            right = null;
            data = newData;
        }
    }

    /**
     * Creates an empty binary tree -- a null root pointer.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Returns true if the given target is in the binary tree. Uses a recursive
     * helper.
     */
    public boolean lookup(int data) {
        return (lookup(root, data));
    }

    /**
     * Recursive lookup -- given a node, recur down searching for the given
     * data.
     */
    private boolean lookup(Node node, int data) {
        if (node == null) {
            return (false);
        }

        if (data == node.data) {
            return (true);
        } else if (data < node.data) {
            return (lookup(node.left, data));
        } else {
            return (lookup(node.right, data));
        }
    }

    /**
     * Inserts the given data into the binary tree. Uses a recursive helper.
     */
    public void insert(int data) {
        root = insert(root, data);
    }

    /**
     * Recursive insert -- given a node pointer, recur down and insert the given
     * data into the tree. Returns the new node pointer (the standard way to
     * communicate a changed pointer back to the caller).
     */
    private Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        } else {
            if (data <= node.data) {
                node.left = insert(node.left, data);
            } else {
                node.right = insert(node.right, data);
            }
        }

        return (node); // in any case, return the new pointer to the caller
    }

    
    /**
     Build 123 using three pointer variables.
    */
    public void build123a() {
      root = new Node(2);
      Node lChild = new Node(1);
      Node rChild = new Node(3);

      root.left = lChild;
      root.right= rChild;
    }

    /**
     Build 123 using only one pointer variable.
    */
    public void build123b() {
      root = new Node(2);
      root.left = new Node(1);
      root.right = new Node(3);
    }
     

    /**
     Build 123 by calling insert() three times.
     Note that the '2' must be inserted first.
    */
    public void build123c() {
      root = null;
      root = insert(root, 2);
      root = insert(root, 1);
      root = insert(root, 3);
    } 
    
    /**
     Returns the number of nodes in the tree.
     Uses a recursive helper that recurs
     down the tree and counts the nodes.
    */
    public int size() {
      return(size(root));
    }

    private int size(Node node) {
      if (node == null) return(0);
      else {
        return(size(node.left) + 1 + size(node.right));
      }
    } 
    
    
    /**
     Returns the max root-to-leaf depth of the tree.
     Uses a recursive helper that recurs down to find
     the max depth.
    */
    public int maxDepth() {
      return(maxDepth(root));
    }

    private int maxDepth(Node node) {
      if (node==null) {
        return(0);
      }
      else {
        int lDepth = maxDepth(node.left);
        int rDepth = maxDepth(node.right);

        // use the larger + 1
        return(Math.max(lDepth, rDepth) + 1);
      }
    }
      
    
    /**
     Returns the min value in a non-empty binary search tree.
     Uses a helper method that iterates to the left to find
     the min value.
    */
    public int minValue() {
     return( minValue(root) );
    }
     

    /**
     Finds the min value in a non-empty binary search tree.
    */
    private int minValue(Node node) {
      Node current = node;
      while (current.left != null) {
        current = current.left;
      }

      return(current.data);
    } 
    
    /**
     Prints the node values in the "inorder" order.
     Uses a recursive helper to do the traversal.
    */
    public void printTree() {
     printTree(root);
     System.out.println();
    }

    private void printTree(Node node) {
     if (node == null) return;

     // left, node itself, right
     printTree(node.left);
     System.out.print(node.data + "  ");
     printTree(node.right);
    } 
    
    /**
     Prints the node values in the "postorder" order.
     Uses a recursive helper to do the traversal.
    */
    public void printPostorder() {
     printPostorder(root);
     System.out.println();
    }

    public void printPostorder(Node node) {
      if (node == null) return;

      // first recur on both subtrees
      printPostorder(node.left);
      printPostorder(node.right);

      // then deal with the node
     System.out.print(node.data + "  ");
    } 
    
    /**
     Given a tree and a sum, returns true if there is a path from the root
     down to a leaf, such that adding up all the values along the path
     equals the given sum.

     Strategy: subtract the node value from the sum when recurring down,
     and check to see if the sum is 0 when you run out of tree.
    */
    public boolean hasPathSum(int sum) {
     return( hasPathSum(root, sum) );
    }

    boolean hasPathSum(Node node, int sum) {
      // return true if we run out of tree and sum==0
      if (node == null) {
        return(sum == 0);
      }
      else {
      // otherwise check both subtrees
        int subSum = sum - node.data;
        return(hasPathSum(node.left, subSum) || hasPathSum(node.right, subSum));
      }
    } 
    
    /**
     Given a binary tree, prints out all of its root-to-leaf
     paths, one per line. Uses a recursive helper to do the work.
    */
    public void printPaths() {
      int[] path = new int[1000];
      printPaths(root, path, 0);
    }

    /**
     Recursive printPaths helper -- given a node, and an array containing
     the path from the root node up to but not including this node,
     prints out all the root-leaf paths.
    */
    private void printPaths(Node node, int[] path, int pathLen) {
      if (node==null) return;

      // append this node to the path array
      path[pathLen] = node.data;
      pathLen++;

      // it's a leaf, so print the path that led to here
      if (node.left==null && node.right==null) {
        printArray(path, pathLen);
      }
      else {
      // otherwise try both subtrees
        printPaths(node.left, path, pathLen);
        printPaths(node.right, path, pathLen);
      }
    }

    /**
     Utility that prints ints from an array on one line.
    */
    private void printArray(int[] ints, int len) {
      int i;
      for (i=0; i<len; i++) {
       System.out.print(ints[i] + " ");
      }
      System.out.println();
    } 
}