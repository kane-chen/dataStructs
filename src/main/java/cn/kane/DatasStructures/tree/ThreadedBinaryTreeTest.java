package cn.kane.DatasStructures.tree;

class TBTreeNode {
    int data;
    int LTag; // 0,1
    int RTag; // 0,1
    TBTreeNode lchild;
    TBTreeNode rchild;

    public TBTreeNode(int data) {
        this(data, null, null, 0, 0);
    }

    public TBTreeNode(int data, TBTreeNode lchild, TBTreeNode rchild, int LTag,
            int RTag) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
        this.LTag = LTag;
        this.RTag = RTag;
    }
}

class ThreadedBinaryTree {
    TBTreeNode head;
    TBTreeNode root;

    public void initTBTree() {
        head = new TBTreeNode(-1);
    }

    public void buildTBTree(int[] data) {
        head = null;
        root = new TBTreeNode(data[0]);
        for (int i = 1; i < data.length; i++) {
            TBTreeNode tmpNode = root;
            while (true) {
                if (tmpNode.data == data[i])
                    break;
                // 小于等于根节点
                if (tmpNode.data > data[i]) {
                    // 如果左孩子为空，这把当前数组元素插入到左孩子节点的位置
                    if (tmpNode.lchild == null) {
                        tmpNode.lchild = new TBTreeNode(data[i]);
                        break;
                    }
                    // 如果不为空的话，则把左孩子节点用来和当前数组元素作比较
                    tmpNode = tmpNode.lchild;
                } else // 大于根节点
                {
                    // 如果右孩子为空，这把当前数组元素插入到左孩子节点的位置
                    if (tmpNode.rchild == null) {
                        tmpNode.rchild = new TBTreeNode(data[i]);
                        break;
                    }
                    // 如果不为空的话，则把右孩子节点用来和当前数组元素作比较
                    tmpNode = tmpNode.rchild;
                }
            }
        }
    }

    // 中序遍历二叉树，并将其中序线索化
    public void inOrderThreading() {
        TBTreeNode current;
        TBTreeNode previous;

        initTBTree();// head节点的初始化,root节点为用户创建的二叉树

        head.LTag = 0;
        head.RTag = 1;
        // 二叉树为空的时候，头结点指向其本身
        if (root == null) {
            head.lchild = head.rchild = head;
        } else {
            current = root;

            head.lchild = current;
            previous = head;
            previous = inThreading(current, previous);
            System.out.println("建立线索二叉树后，previous指针的值为：" + previous.data);
            previous.RTag = 1;
            previous.rchild = head;
            head.rchild = previous;            
            System.out.println("建立线索二叉树后，最后一个节点为：" + previous.data
                    + "，对应的后继节点为：" + previous.rchild.data);
        }
    }

    // 前驱后继都是相对于头结点和叶子节点而言
    // 其中current指针指向当前访问的节点；previous节点指向刚刚访问过的节点
    private TBTreeNode inThreading(TBTreeNode current, TBTreeNode previous) {
        if (current != null) {
            TBTreeNode tmpNode = inThreading(current.lchild, previous);
            // 前驱线索
            if (current.lchild == null && current.LTag == 0) {
                current.LTag = 1;
                current.lchild = previous;
            }
            previous = tmpNode;
            // 后继线索
            if (previous.rchild == null && previous.RTag == 0) {
                previous.RTag = 1;
                previous.rchild = current;
            }

            previous = current;// 保持previous指向current的前驱
            previous = inThreading(current.rchild, previous);

            return previous;
        }
        return previous;
    }

    // 查找二叉查找树的最小节点:线索化二叉树前后的区别
    public TBTreeNode getFirstTBTNode(TBTreeNode node) {
        if (head != null) {
            while (node.lchild != head) {
                node = node.lchild;
            }
        } else {
            while (node.lchild != null) {
                node = node.lchild;
            }
        }
        return node;
    }

    // 查找二叉查找树的最大节点
    public TBTreeNode getLastTBTNode(TBTreeNode node) {
        if (head == null) {
            while (node.rchild != null) {
                node = node.rchild;
            }
        } else {
            while (node.rchild != head) {
                node = node.rchild;
            }
        }
        return node;
    }

    // 查找节点的前驱节点
    public TBTreeNode getPredecessor(TBTreeNode node) {
        if (node.lchild != null) {
            return getLastTBTNode(node.lchild);// 左子树的最大值
        }
        TBTreeNode parent = getParent(node);
        while (parent != null && node == parent.lchild) {// 向上找到最近的一个节点，其父亲节点的右子树包涵了当前节点或者其父亲节点为空
            node = parent;
            parent = getParent(parent);
        }
        return parent;
    }

    // 查找节点的后继节点
    public TBTreeNode getSuccessor(TBTreeNode node) {
        if (node.rchild != null) {
            return getFirstTBTNode(node.rchild);// 右子树的最小值
        }
        TBTreeNode parent = getParent(node);
        if (parent == null)
            return null;
        while (parent != null) {
            if (parent.lchild == node) {
                return parent; // 为左子树情况，后继为父节点
            } else {
                node = parent; // 否则递归
                parent = getParent(parent);
            }
        }
        return parent;
    }

    // 求出父亲节点，在定义节点类BSTreeNode的时候，没有申明父亲节点，所以这里专门用parent用来输出父亲节点（主要是不想修改代码了，就在这里加一个parent函数吧）
    public TBTreeNode getParent(TBTreeNode node) {
        TBTreeNode p = root;
        TBTreeNode tmp = null;
        while (p != null && p.data != node.data) {// 最后的p为p.data等于k.data的节点，tmp为p的父亲节点
            if (p.data > node.data) {
                tmp = p;// 临时存放父亲节点
                p = p.lchild;
            } else {
                tmp = p;// 临时存放父亲节点
                p = p.rchild;
            }
        }
        return tmp;
    }

    /**
     * 线索化的递归遍历二叉树
     */
    public void inOrderReaversal() {
        TBTreeNode node;
        if (head != null) {
            node = head.lchild; // node表示head头指针指向的root节点
            // 空树或者遍历结束 node==head
            while (node != head) {
                // 访问左子树
                while (node.LTag == 0)
                    node = node.lchild;
                System.out.print(node.data + "   ");
                while (node.RTag == 1 && node.rchild != head) {
                    // 访问叶子节点的后继
                    node = node.rchild;
                    System.out.print(node.data + "   ");
                }
                // 访问完叶子节点的后继后，访问右子树
                node = node.rchild;
            }
        }
    }

    /**
     * 未线索化的中序递归遍历二叉树
     */
    public void traversalTBTree() {
        traversalTBTree(root);
        System.out.println();
    }

    private void traversalTBTree(TBTreeNode node) {
        if (node != null) {
            traversalTBTree(node.lchild);
            System.out.print(node.data + "  ");
            traversalTBTree(node.rchild);
        }
    }
}

public class ThreadedBinaryTreeTest {
    public static void main(String[] args) {
        ThreadedBinaryTree tbTree = new ThreadedBinaryTree();
        /***********************************************************************
         * 初始化操作
         **********************************************************************/
        int[] data = { 2, 8, 7, 4, 9, 3, 1, 6, 7, 5 }; // { 8, 7, 1, 6, 4, 5,
                                                        // 10, 3, 2, 9 };
        tbTree.buildTBTree(data);
        System.out.println("########################################");
        System.out.println("未进行线索化前，二叉树中序遍历结果:");
        tbTree.traversalTBTree();
        System.out.println(tbTree.head == null);
        System.out.println("未进行线索化前，二叉树中第一个节点和最后一个节点值分别为："
                + tbTree.getFirstTBTNode(tbTree.root).data + "   "
                + tbTree.getLastTBTNode(tbTree.root).data);

        /***********************************************************************
         * 中序线索化操作
         **********************************************************************/
        System.out.println("########################################");
        System.out.println("线索化后，二叉树遍历结果:");
        tbTree.inOrderThreading();
        tbTree.inOrderReaversal();
        System.out.println();
        System.out.println("线索化后，head头指针的左子节点和后继节点分别为："
                + tbTree.head.lchild.data + "   " + tbTree.head.rchild.data);
        System.out.println("线索化后，二叉树中第一个节点和最后一个节点值分别为："
                + tbTree.getFirstTBTNode(tbTree.root).data + "   "
                + tbTree.getLastTBTNode(tbTree.root).data);

    }
}