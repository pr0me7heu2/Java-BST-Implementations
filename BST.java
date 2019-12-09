/*
 * Name:
 * GWU email:
 * 
 */

package bstree;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class BST {

    // Root of Binary Search Tree 
    Node root;

    /* Class Node is a inner class that contains the definition of a node in a tree:
     *  left and right children of current node and
     *  key value randomly generated binary tree java
     */
    class Node {
        int key;
        Node left, right;

        public Node(int value) {
            key = value;
            left = null;
            right = null;
        }

        // returns the smallest value in the node
        int minValue() {
            Node temp = this;
            // minimum value will always be the left most node
            while(temp.left != null) {
                temp = temp.left;
            }
            return temp.key;
        }

        // returns the largest value in the BST
        int maxValue() {
            Node temp = this;
            // minimum value will always be the right most node
            while(temp.right != null) {
                temp = temp.right;
            }
            return temp.key;
        }
    }

    // Constructor for the Binary Search Tree
    BST() {
        root = null;
    }

    // This method mainly calls addRec() 
    void addKey(int key) {
        root = addRec(root, key);
    }

    /* A recursive function to insert a new key in BST */
    Node addRec(Node root, int key) {
        if (root == null) {
            return new Node(key);
        } else if (key < root.key) {
            root.left = addRec(root.left, key);
        } else if (key > root.key) {
            root.right = addRec(root.right, key);
        }
        // key already exists
        return root;

    }


    /* A iterative function to insert a new key in BST */
    void add(int key) {
        // create new node containing key
        Node newNode = new Node(key);

        // if list is empty
        if (root == null)  {
            root = newNode;
            return;
        }

        // nodePtr points to root
        Node nodePtr = root;

        // prevNode will point to previous node
        // as we traverse the tree
        Node prevNode = null;

        // notePtr continues down tree to the left if key is smaller
        // than current node key or to the right if larger
        while (nodePtr != null) {
            prevNode = nodePtr;
            nodePtr = (key < nodePtr.key) ? nodePtr.left : nodePtr.right;
        }

        // once nodePtr is null we have found spot for new node
        if (key < prevNode.key) {
            prevNode.left = newNode;
        }
        else if (key > prevNode.key) {
            prevNode.right = newNode;
        }
        // if key already exists
        else
            return;
    }


    // This method mainly calls removeRec() 
    void removeKey(int key) {
        root = removeRec(root, key);
    }

    /* A recursive function to remove a key from BST */
    Node removeRec(Node root, int key) {

        // empty list or not in list
        if (root == null) {
            return root;
        }
        else if (key < root.key) {
            root.left = removeRec(root.left, key);
        }
        else if (key > root.key) {
            root.right = removeRec(root.right, key);
        }
        // key found
        else {
            // node is leaf
            if (root.left == null  && root.right == null) {
                return null;
            }
            // one and only one sub tree
            else if (root.left == null) {
                return root.right;
            }
            else if (root.right == null) {
                return root.left;
            }

            // two sub trees

            // find inorder predecessor and replace
            root.key = (root.left).maxValue();

            // delete from old location
            root.left = removeRec(root.left, root.key);
        }

        return root;
    }

    /* A iterative function to remove a key from BST */
    void remove(int key) {

        // if list is empty
        if (root == null)  {
            return;
        }

        // nodePtr points to root
        Node nodePtr = root;

        // prevNode will point to previous node
        // as we traverse the tree
        Node prevNode = null;

        // notePtr continues down tree to the left if key is smaller
        // than current node key or to the right if larger
        while (nodePtr.key != key) {
            prevNode = nodePtr;
            nodePtr = (key < nodePtr.key) ? nodePtr.left : nodePtr.right;
            if (nodePtr == null) {
                System.out.println("ERROR: " + key + " already deleted and/or does not exist.");
                return;
            }
        }

        // if a key is a leaf
        if (nodePtr.left == null  && nodePtr.right == null) {
            // delete right left subtree
            if (key < prevNode.key) {
                prevNode.left = null;
            }
            // or left subtree of parent node
            else {
                prevNode.right = null;
            }
            return;
        }
        // one and only one sub tree
        else if (nodePtr.left == null) {
            if (key < prevNode.key) {
                prevNode.left = nodePtr.right;
            }
            // or left subtree of parent node
            else {
                prevNode.right = nodePtr.right;
            }
            return;
        }
        else if (nodePtr.right == null) {
            if (key < prevNode.key) {
                prevNode.left = nodePtr.left;
            }
            // or left subtree of parent node
            else {
                prevNode.right = nodePtr.left;
            }
            return;
        }

        // if two subtrees
        // find and replace key with inorder predecessor
        int oldKey = nodePtr.key;
        nodePtr.key = (nodePtr.left).maxValue();

        // inorder predecessor is the maximum value of the left
        // subtree and will have (at most) one subtree

        // use similar iterative loop to find node that holds this key
        key = nodePtr.key;
        prevNode = nodePtr;

        if (key < oldKey) {
            nodePtr = nodePtr.left;
        }
        else {
            nodePtr = nodePtr.right;
        }

        while (nodePtr.key != key){
            prevNode = nodePtr;
            nodePtr = (key < nodePtr.key) ? nodePtr.left : nodePtr.right;
        }

        // now use above for case in which there is one or zero subtrees

        // if a key is a leaf
        if (nodePtr.left == null  && nodePtr.right == null) {
            // delete right left subtree
            if (key < prevNode.key) {
                prevNode.left = null;
            }
            // or left subtree of parent node
            else {
                prevNode.right = null;
            }
            return;
        }
        // one and only one sub tree
        else if (nodePtr.left == null) {
            if (key < prevNode.key) {
                prevNode.left = nodePtr.right;
            }
            // or left subtree of parent node
            else {
                prevNode.right = nodePtr.right;
            }
            return;
        }
        else if (nodePtr.right == null) {
            if (key < prevNode.key) {
                prevNode.left = nodePtr.left;
            }
            // or left subtree of parent node
            else {
                prevNode.right = nodePtr.left;
            }
            return;
        }
    }

    // returns true if the Node containing the key is a leaf, false otherwise.
    boolean isLeaf(Node root, int key) {
        // if empty list or if key is not in list
        if (root == null) {
            return false;
        } else if (key < root.key) {
            return isLeaf(root.left, key);
        } else if (key > root.key) {
            return isLeaf(root.right, key);
        }
        // if key exists; return true if no subtrees
        return (root.right==null && root.left==null);
    }

    // returns the smallest value in the BST
    int minValue() {

        if (root == null) {
            throw new NullPointerException("Can not find the smallest value in an empty tree.");
        }
        Node temp = root;

        // minimum value will always be the left most node
        while(temp.left != null) {
            temp = temp.left;
        }

        return temp.key;
    }

    // returns the largest value in the BST
    int maxValue() {
        if (root == null) {
            throw new NullPointerException("Can not find the largest value in an empty tree.");
        }
        Node temp = root;

        // minimum value will always be the right most node
        while(temp.right != null) {
            temp = temp.right;
        }

        return temp.key;
    }


    /*
     * returns the height of a tree:
     * the number of nodes along the longest path from the root node down to the
     * farthest leaf node.
     */
    int height(Node root) {
        if (root == null) {
            return 0;
        }
        else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            // calculate the height of left subtree and right
            // subtree and take the max + 1

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // Prints the traversal of the BST using iteratively implemented inOrder
    void inOrder() {
        if (root == null) {
            return;
        }

        // create stack to track progression down the tree
        Stack<Node> nodeStack = new Stack<Node>();
        Node current = root;

        // end when at leaf and no more nodes on stack
        while (current != null || nodeStack.size() != 0) {

            // traverse the tree until we get to the furthest left node
            while (current != null) {
                // save node before heading further left (so can later go right)
                nodeStack.push(current);
                current = current.left;
            }

            // back up one to a non-null node
            current = nodeStack.pop();

            // print non-null key
            System.out.print(current.key + " ");

            // enter right subtree
            current = current.right;

        }
    }

    // Prints the traversal of the BST using recursively implemented inOrder
    void inOrderRec(Node root) {
        if (root.left != null) {
            inOrderRec(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inOrderRec(root.right);
        }
    }

    // Prints the preOrder traversal of the BST.
    void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    // Prints the postOrder traversal of the BST.
    void postOrder(Node root) {
        if (root != null) {
            preOrder(root.left);
            preOrder(root.right);
            System.out.print(root.key + " ");
        }
    }

    // Prints the levelOrder traversal of the BST.
    // this function uses a Queue to do the level order traversal
    void levelOrder(Node root) {
        Queue<Node> nodeQueue = new LinkedList<Node>();
        Stack<Node> nodeStack = new Stack<Node>();
        if (root == null) {
            return;
        }

        // add root to queue
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            Node temp = nodeQueue.poll();
            nodeStack.push(temp);

            if (temp.left != null) {
                nodeQueue.add(temp.left);
            }

            if (temp.right != null) {
                nodeQueue.add(temp.right);
            }

            // tracks nodes any one particular level
            // prevents moving down the tree until THAT level is printed
            while (!nodeStack.empty()) {
                temp = nodeStack.peek();
                System.out.print(temp.key + " ");
                nodeStack.pop();
            }
        }
        return;
    }
}

//    Originally implemented the following in a couple of seconds only
//     to realize that this would only work with a balanced BST and there
//     was a need for a stack to keep track of the nodes at any particular
//     level otherwise you would recursively move down the left subtree while
//     ignoring the right.

//    private void add_to_queue(Node root, Queue queue) {
//        if (root == null) {
//            return;
//        }
//        else {
//            if(root.left != null) {
//                queue.add(root.left);
//            }
//            if(root.right != null) {
//                queue.add(root.right);
//            }
//            add_to_queue(root.left, queue);
//            add_to_queue(root.right, queue);
//        }
//    }
