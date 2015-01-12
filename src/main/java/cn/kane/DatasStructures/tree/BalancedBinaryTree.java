package cn.kane.DatasStructures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 平衡二叉树
 * 
 * @author liangjun.zhong
 */
public class BalancedBinaryTree {

	private Node root;
	private int size;

	private static final int AS_Left_Child = -1; // 节点自己是父节点的左儿子
	private static final int AS_Right_Child = 1; // 节点自己是父节点的右儿子

	/**
	 * 插入元素建树
	 * 
	 * @param e
	 * @return
	 */
	public boolean insert(Element e) {
		if (e != null) {
			Node insertNode = insert(e, this.root);
			if (insertNode != null) {
				Stack<Integer> stack = new Stack<Integer>();
				this.backtrackingTreat(insertNode, stack); // 回溯,查找最小非平衡树,旋转调整树结构,以再次达到平衡
				return true;
			} else { // 节点已存在
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 返回插入的节点，若节点已存在则返回null
	 * 
	 * @param e
	 * @param now_root
	 * @return
	 */
	private Node insert(Element e, Node now_root) { // System.out.println("insert"+e.getValue());
		if (this.root == null) {
			this.root = new Node(e);
			this.size++;
			return this.root;
		}
		int result = e.compareTo(now_root.element);
		if (result < 0) {
			if (now_root.left != null) {
				return insert(e, now_root.left);
			} else {
				Node left = new Node(e);
				left.parent = now_root;
				left.asChild = AS_Left_Child;
				now_root.left = left;
				this.size++;
				return left;
			}
		} else if (result > 0) {
			if (now_root.right != null) {
				return insert(e, now_root.right);
			} else {
				Node right = new Node(e);
				right.parent = now_root;
				right.asChild = AS_Right_Child;
				now_root.right = right;
				this.size++;
				return right;
			}
		} else {
			now_root.frequency = now_root.frequency + 1;// 节点已存在
			return null;
		}
	}

	/**
	 * 删除元素
	 * 
	 * @param e
	 * @return
	 */
	public boolean remove(Element e) {
		if (e != null) {
			Node removeNode = this.contains(e); // 查找待删除的节点
			if (removeNode != null) {
				if (removeNode.parent == null && removeNode.left == null && removeNode.right == null) { // 删除的是只有唯一根节点的整棵树
					this.root = null;
					return true;
				} else {
					Node checkStartNode = this.remove(removeNode); // 删除节点，并返回开始回溯check最小非平衡树的开始节点
					Node imbalanceTreeRoot = this.backtrackingCheck(checkStartNode); // 最小非平衡树根节点
					if (imbalanceTreeRoot == null) { // 保持了平衡
						return true;
					} else { // 删除后失去平衡
						Node deepestNode = this.getDeepestNode(imbalanceTreeRoot); // 树的最深节点
						Stack<Integer> stack = new Stack<Integer>();
						this.backtrackingTreat(deepestNode, stack); // 回溯,查找最小非平衡树,旋转调整树结构,以再次达到平衡
						return true;
					}
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 删除节点，并返回开始回溯check最小非平衡树的开始节点
	 * 
	 * @param removeNode
	 * @return
	 */
	private Node remove(Node removeNode) {
		Node checkStartNode = null;
		if (removeNode.left == null && removeNode.right == null) { // ①如果被删除的节点是叶子节点，直接删除
			Node removeNodeParent = removeNode.parent;
			if (removeNodeParent != null && this.cutRelation(removeNodeParent, removeNode)) { // 删除的不是根节点
				checkStartNode = removeNodeParent;
			} else {
				// 删除的是根节点
			}
		} else if (removeNode.left != null && removeNode.right != null) { // ②如果被删除的节点含有两个子节点，找到左子树的最大节点，并替换该节点
			Node removeNodeParent = removeNode.parent;
			int removeNode_asChild = removeNode.asChild;
			Node removeNodeLeft = removeNode.left;
			Node removeNodeRight = removeNode.right;
			Node biggestNodeLeftChildTree = this.getBiggestNode(removeNodeLeft); // 左子树最大节点
			Node biggestNodeLeftChildTreeLeft = biggestNodeLeftChildTree.left; // 左子树最大节点左节点
			Node biggestNodeLeftChildTreeParent = biggestNodeLeftChildTree.parent;// 左子树最大节点父节点

			if (removeNodeLeft == biggestNodeLeftChildTree) { // 左子树最大节点为其根节点
				removeNode.right = null;
				removeNodeLeft.right = removeNodeRight;
				removeNodeRight.parent = removeNodeLeft;
				if (removeNodeParent != null) {// 删除的不是根节点
					if (removeNode_asChild == AS_Left_Child) {
						removeNode.parent = null;
						removeNodeParent.left = removeNodeLeft;
						removeNodeLeft.parent = removeNodeParent;
						removeNodeLeft.asChild = AS_Left_Child;
					} else {
						removeNode.parent = null;
						removeNodeParent.right = removeNodeLeft;
						removeNodeLeft.parent = removeNodeParent;
						removeNodeLeft.asChild = AS_Right_Child;
					}
				} else {// 删除的是根节点
					this.root = removeNodeLeft;
					removeNodeLeft.parent = null;
					removeNodeLeft.asChild = 0;
				}
				checkStartNode = removeNodeLeft;
			} else { // 左子树最大节点不是其根节点
				removeNode.right = null;
				biggestNodeLeftChildTree.right = removeNodeRight;
				removeNodeRight.parent = biggestNodeLeftChildTree;
				removeNode.left = null;
				biggestNodeLeftChildTree.left = removeNodeLeft;
				removeNodeLeft.parent = biggestNodeLeftChildTree;
				biggestNodeLeftChildTreeParent.right = biggestNodeLeftChildTreeLeft;
				if (biggestNodeLeftChildTreeLeft != null) {
					biggestNodeLeftChildTreeLeft.parent = biggestNodeLeftChildTreeParent;
					biggestNodeLeftChildTreeLeft.asChild = AS_Right_Child;
				}
				if (removeNodeParent != null) {// 删除的不是根节点
					if (removeNode_asChild == AS_Left_Child) {
						removeNode.parent = null;
						removeNodeParent.left = biggestNodeLeftChildTree;
						biggestNodeLeftChildTree.parent = removeNodeParent;
						biggestNodeLeftChildTree.asChild = AS_Left_Child;
					} else {
						removeNode.parent = null;
						removeNodeParent.right = biggestNodeLeftChildTree;
						biggestNodeLeftChildTree.parent = removeNodeParent;
						biggestNodeLeftChildTree.asChild = AS_Right_Child;
					}
				} else {// 删除的是根节点
					this.root = biggestNodeLeftChildTree;
					biggestNodeLeftChildTree.parent = null;
					biggestNodeLeftChildTree.asChild = 0;
				}
				checkStartNode = biggestNodeLeftChildTreeParent;
			}
		} else { // ③如果被删除的节点含有一个子节点，让指向该节点的指针指向他的儿子节点
			Node removeNodeParent = removeNode.parent;
			int removeNode_asChild = removeNode.asChild;
			Node removeNodeChild = null;
			if (removeNode.left != null) {
				removeNodeChild = removeNode.left;
			} else {
				removeNodeChild = removeNode.right;
			}
			if (removeNodeParent != null) {// 删除的不是根节点
				if (removeNode_asChild == AS_Left_Child) {
					removeNode.parent = null;
					removeNodeParent.left = removeNodeChild;
					removeNodeChild.parent = removeNodeParent;
					removeNodeChild.asChild = AS_Left_Child;
				} else {
					removeNode.parent = null;
					removeNodeParent.right = removeNodeChild;
					removeNodeChild.parent = removeNodeParent;
					removeNodeChild.asChild = AS_Right_Child;
				}
			} else {// 删除的是根节点
				this.root = removeNodeChild;
				removeNodeChild.parent = null;
				removeNodeChild.asChild = 0;
			}
			checkStartNode = removeNodeParent;
		}
		return checkStartNode;
	}

	/**
	 * 删除子节点
	 * 
	 * @param parentNode
	 * @param childNode
	 * @return
	 */
	private boolean cutRelation(Node parentNode, Node childNode) {
		if (parentNode != null && childNode != null) {
			int asChild = childNode.asChild;
			if (asChild == AS_Left_Child) {
				parentNode.left = null;
			} else {
				parentNode.right = null;
			}
			childNode.parent = null;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 回溯,查找最小非平衡树,旋转调整树结构,以再次达到平衡
	 * 
	 * @param operateNode
	 * @param stack
	 */
	private void backtrackingTreat(Node operateNode, Stack<Integer> stack) {
		if (operateNode != null) {
			Node parentNode = operateNode.parent;
			if (parentNode != null) {
				stack.push(operateNode.asChild);
				int depthChange = this.height(parentNode.left)
						- this.height(parentNode.right); // System.out.println(depthChange);
				if (depthChange == 2 || depthChange == -2) { // 最小非平衡子树
					String treatType = this.judgeTreatType(stack); // 最小非平衡树的类型
					this.convert(parentNode, treatType); // 旋转,调整树结构
				} else {
					backtrackingTreat(parentNode, stack);
				}
			}
		}
	}

	/**
	 * 回溯,查找最小非平衡树,返回最小非平衡树根节点,若保持平衡则返回null
	 * 
	 * @param checkNode
	 * @return
	 */
	private Node backtrackingCheck(Node checkNode) {
		int depthChange = this.height(checkNode.left)
				- this.height(checkNode.right);
		if (depthChange == 2 || depthChange == -2) { // 最小非平衡子树
			return checkNode;
		} else if (checkNode.parent != null) {
			return backtrackingCheck(checkNode.parent);
		} else {
			return null;
		}
	}

	/**
	 * 判断最小非平衡树的类型
	 * 
	 * @param stack
	 * @return
	 */
	private String judgeTreatType(Stack<Integer> stack) {
		Integer second = stack.pop();
		Integer third = stack.pop();
		if (second != null && third != null) {
			if (second == -1 && third == -1) {
				return "LL";
			}
			if (second == -1 && third == 1) {
				return "LR";
			}
			if (second == 1 && third == -1) {
				return "RL";
			}
			if (second == 1 && third == 1) {
				return "RR";
			}
		}
		return "error";
	}

	/**
	 * 旋转,调整树结构
	 * 
	 * @param parentNode
	 * @param treatType
	 */
	private void convert(Node parentNode, String treatType) {
		if (treatType != null && parentNode != null) {
			Node grandpaNode = parentNode.parent;// 当前最小非平衡树的父节点，若为null则最小非平衡树即为整棵树
			int asChild_parent = 0;
			if (grandpaNode != null) {
				asChild_parent = parentNode.asChild;
			}

			if (treatType.equals("LL")) {
				Node secondNode = parentNode.left;
				Node secondNodeRight = secondNode.right;
				secondNode.right = parentNode;
				parentNode.parent = secondNode;
				parentNode.asChild = AS_Right_Child;
				parentNode.left = secondNodeRight;
				if (secondNodeRight != null) {
					secondNodeRight.parent = parentNode;
					secondNodeRight.asChild = AS_Left_Child;
				}
				if (grandpaNode != null) {
					if (asChild_parent == AS_Left_Child) {
						grandpaNode.left = secondNode;
						secondNode.parent = grandpaNode;
						secondNode.asChild = AS_Left_Child;
					}
					if (asChild_parent == AS_Right_Child) {
						grandpaNode.right = secondNode;
						secondNode.parent = grandpaNode;
						secondNode.asChild = AS_Right_Child;
					}
				} else {
					this.root = secondNode;
					secondNode.parent = null;
					secondNode.asChild = 0;
				}
			}

			if (treatType.equals("LR")) {
				Node secondNode = parentNode.left;
				Node thirdNode = secondNode.right;
				Node thirdNodeLeft = thirdNode.left;
				thirdNode.left = secondNode;
				secondNode.parent = thirdNode;
				secondNode.asChild = AS_Left_Child;
				secondNode.right = thirdNodeLeft;
				if (thirdNodeLeft != null) {
					thirdNodeLeft.parent = secondNode;
					thirdNodeLeft.asChild = AS_Right_Child;
				}
				parentNode.left = thirdNode;
				thirdNode.parent = parentNode;
				thirdNode.asChild = AS_Left_Child;
				convert(parentNode, "LL");
			}

			if (treatType.equals("RL")) {
				Node secondNode = parentNode.right;
				Node thirdNode = secondNode.left;
				Node thirdNodeRight = thirdNode.right;
				thirdNode.right = secondNode;
				secondNode.parent = thirdNode;
				secondNode.asChild = AS_Right_Child;
				secondNode.left = thirdNodeRight;
				if (thirdNodeRight != null) {
					thirdNodeRight.parent = secondNode;
					thirdNodeRight.asChild = AS_Left_Child;
				}
				parentNode.right = thirdNode;
				thirdNode.parent = parentNode;
				thirdNode.asChild = AS_Right_Child;
				convert(parentNode, "RR");
			}

			if (treatType.equals("RR")) {
				Node secondNode = parentNode.right;
				Node secondNodeLeft = secondNode.left;
				secondNode.left = parentNode;
				parentNode.parent = secondNode;
				parentNode.asChild = AS_Left_Child;
				parentNode.right = secondNodeLeft;
				if (secondNodeLeft != null) {
					secondNodeLeft.parent = parentNode;
					secondNodeLeft.asChild = AS_Right_Child;
				}
				if (grandpaNode != null) {
					if (asChild_parent == AS_Left_Child) {
						grandpaNode.left = secondNode;
						secondNode.parent = grandpaNode;
						secondNode.asChild = AS_Left_Child;
					}
					if (asChild_parent == AS_Right_Child) {
						grandpaNode.right = secondNode;
						secondNode.parent = grandpaNode;
						secondNode.asChild = AS_Right_Child;
					}
				} else {
					this.root = secondNode;
					secondNode.parent = null;
					secondNode.asChild = 0;
				}
			}

		}
	}

	/**
	 * 获取树的高度
	 * 
	 * @param root
	 * @return
	 */
	public int height(Node root) {
		if (root == null)
			return 0;
		else
			return 1 + Math.max(height(root.left), height(root.right));
	}

	/**
	 * 获取树的最深节点
	 * 
	 * @param now_root
	 * @return
	 */
	public Node getDeepestNode(Node now_root) {
		if (now_root.left == null && now_root.right == null) {
			return now_root;
		} else {
			if (height(now_root.left) >= height(now_root.right)) {
				return getDeepestNode(now_root.left);
			} else {
				return getDeepestNode(now_root.right);
			}
		}
	}

	/**
	 * 获取当前树的最大节点
	 * 
	 * @param now_root
	 * @return
	 */
	public Node getBiggestNode(Node now_root) {
		if (now_root.right != null) {
			return getBiggestNode(now_root.right);
		} else {
			return now_root;
		}
	}

	/**
	 * 搜索树种是否包含某元素,返回该元素所在节点,不存在返回null
	 * 
	 * @param e
	 * @return
	 */
	public Node contains(Element e) {
		return contains(e, this.root);
	}

	private Node contains(Element e, Node now_root) {
		if (now_root == null || e == null) {
			return null;
		}
		int result = e.compareTo(now_root.element);
		if (result < 0) {
			if (now_root.left != null) {
				return contains(e, now_root.left);
			} else {
				return null;
			}
		} else if (result > 0) {
			if (now_root.right != null) {
				return contains(e, now_root.right);
			} else {
				return null;
			}
		} else {
			return now_root;
		}
	}

	/**
	 * 中序遍历该树
	 * 
	 * @return
	 */
	public List<Element> traversal() {
		List<Element> list = new ArrayList<Element>();
		return traversal(list, this.root);
	}

	private List<Element> traversal(List<Element> list, Node now_root) {
		if (now_root != null) {
			if (now_root.left != null) {
				traversal(list, now_root.left);
			}
			list.add(now_root.element);
			if (now_root.right != null) {
				traversal(list, now_root.right);
			}
		}
		return list;
	}

	/**
	 * 中序遍历该树
	 */
	public String toString() {
		return toString(this.root);
	}

	private String toString(Node n) {
		String result = "";
		if (n != null) {
			if (n.left != null) {
				result += toString(n.left);
			}
			result += n.element.getValue() + " ";
			if (n.right != null) {
				result += toString(n.right);
			}
		}
		return result;
	}

	public Node getRoot() {
		return root;
	}

	public int getSize() {
		return size;
	}

	public Element getPrepareElement(String key, int value, Object object) {
		Element e = new Element(key, value, object);
		return e;
	}

	public class Node {
		public Element element;
		public Node left;
		public Node right;
		public Node parent;
		public int asChild; // 节点自己是父节点的左二子or右儿子
		public int frequency = 1;

		public Node(Element element) {
			this.element = element;
		}
	}

	public class Element implements Comparable<Element> {
		private String key;
		private int value;
		private Object object;

		public Element(String key, int value, Object object) {
			this.key = key;
			this.value = value;
			this.object = object;
		}

		/**
		 * a.compareTo(b) if a>b return >0
		 */
		@Override
		public int compareTo(Element e) {
			int result = this.value - e.value;
			return result;
		}

		public String getKey() {
			return key;
		}

		public int getValue() {
			return value;
		}

		public Object getObject() {
			return object;
		}

	}

}