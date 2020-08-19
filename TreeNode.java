
/**
 * Binary Tree Node Tree node that has two children: left and right
 * 
 * @author Serena Jiao, yj5qe
 * @param <Comparable> The type of data this tree node stores
 */
public class TreeNode<T extends Comparable<T>> {

    /**
     * Reference pointer to the left subtree
     */
    private TreeNode<T> left;

    /**
     * Reference pointer to the right subtree
     */
    private TreeNode<T> right;

    /**
     * Data stored at this node
     */
    private T data;

    /**
     * Default Constructor Creates a binary tree node with null data and null children
     */
    public TreeNode() {
        this(null, null, null);
    }

    /**
     * Data-only Constructor Creates a binary tree node with the given data and null children
     * 
     * @param theData The data to store at this node
     */
    public TreeNode(T theData) {
        this(theData, null, null);
    }

    /**
     * Full Constructor Creates a binary tree node with the given data and child reference pointers
     * 
     * @param theData    The data to store at this node
     * @param leftChild  A reference pointer to the left subtree
     * @param rightChild A reference pointer to the right subtree
     */
    public TreeNode(T theData, TreeNode<T> leftChild, TreeNode<T> rightChild) {
        data = theData;
        left = leftChild;
        right = rightChild;
    }

    /**
     * Left Child/Subtree getter
     * 
     * @return A reference pointer to the root of the left subtree
     */
    public TreeNode<T> getLeft() {
        return left;
    }

    /**
     * Left Child/Subtree Setter
     * 
     * @param left A reference pointer to the new left subtree's root node
     */
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    /**
     * Right Child/Subtree getter
     * 
     * @return A reference pointer to the root of the right subtree
     */
    public TreeNode<T> getRight() {
        return right;
    }

    /**
     * Right Child/Subtree Setter
     * 
     * @param left A reference pointer to the new right subtree's root node
     */
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    /**
     * Get the data at this node
     * 
     * @return The data stored at this node
     */
    public T getData() {
        return data;
    }

    /**
     * Set the data at this node
     * 
     * @param data The data to be stored at this node
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * The method will return an int that represents the size of the total tree.
     * 
     * @return the size of the BST
     */
    public int size() {
        int lsize = 0;   // for calculating the left elements
        int rsize = 0;   // for calculating the right elements
        if (left != null)    // if the left class is not null
            lsize = lsize + left.size();   // call the method recursively
        if (right != null)
            rsize = rsize + right.size();
        return lsize + rsize + 1;  // add one because we need to include the root node
    }

    /**
     * The method will return an int that represents the height of the total tree.
     * 
     * @return the height of the BST
     */
    public int height() {
        int lheight = 0;   // lheight holds the height for the left subtree
        int rheight = 0;   // rheight holds the height for the right subtree
        if (left != null)  // When the tree is not empty
            lheight = left.height();  // call the method recursively
        if (right != null)
            rheight = right.height();
        if (lheight > rheight)  // find out which side is "taller" and use the number of the taller side
            return lheight + 1;  // add one for the root node
        else
            return rheight + 1;
    }

    /**
     * Determines if the value val appears within the Binary Search Tree. It should return true if it exists, false
     * otherwise.
     * 
     * @param val the value that we are finding
     * @return a boolean: whether or not the value is found in the BST
     */
    public boolean find(T val) {
        if (val == null)   // check if the value is null
            return false;
        int comp = data.compareTo(val);  // compare the value to the root node
        if (comp == 0) {  // if the value == root node, we have found it!
            return true;
        }
        // if there's no child trees, the value doesn't exist
        if (left == null && right == null)
            return false;
        // if the root node is null, return false
        if (data == null)
            return false;
        // When the value is smaller than the root node, go to the left side
        if (comp > 0) {
            {
                if (left == null)   // if it is a leaf node
                    return false;   // return false
            }
            return left.find(val);
        }
        // When the value is smaller than the root node, go to the right side
        if (comp < 0) {
            {
                if (right == null)
                    return false;
            }
            return right.find(val);
        }
        return false;
    }

    /**
     * Inserts the value val at the appropriate place in the tree. Return true if insert succeeded, false otherwise.
     * 
     * @param val the value we're inserting
     * @return a boolean whether or not the insertion is successful
     */
    public boolean insert(T val) {
        // Decide which side the value should go
        int comp = data.compareTo(val);
        if (comp > 0) {  // value is smaller, goes to the left side
            if (left == null) {  // if the left node doesn't exist
                left = new TreeNode<T>(val);  // make a new node
                return true;
            } else   // else, keep searching
                return left.insert(val);
        }
        if (comp < 0) { // value is larger, goes to the right side
            if (right == null) { // if the right node doesn't exist
                right = new TreeNode<T>(val);  // make a new node
                return true;
            } else  // else, keep searching
                return right.insert(val);
        }
        return false;
    }

    /**
     * Delete an element from the tree Deletes val from the tree if it appears, returning true on success and false
     * otherwise
     * 
     * @param val The value to delete
     * @return True on success, false otherwise
     */
    public TreeNode<T> delete(T val) {
        if (val == null)
            return this;
        // When the root node is the one that is deleted
        if (data.compareTo(val) == 0) {
            // when the root node is the only node
            if (right == null && left == null) {
                return null;   // the tree becomes null
            }
            // when the left node is null
            if (right != null && left == null) {
                return right;
            }
            // when the right node is null
            if (left != null && right == null) {
                return left;
            }
            // when both sides have child, we find the next largest in the right subtree
            // this will replace the node
            TreeNode<T> replace = right;
            while (replace.left != null) {
                // find the element that is the closest to the root node.
                // To do that, we need to find the lowest left node
                replace = replace.left;
            }
            // delete the node before we move that to the root node
            delete(replace.data);
            data = replace.data; // set it as the new node
            return this;
        } else if (val.compareTo(data) < 0) {
            left = left.delete(val); // call the delete method recursively
            return this;
        } else {
            right = right.delete(val); // call the delete method recursively
            return this;
        }
    }

    /**
     * Returns a String that represents the data held at each node starting with all the nodes of the left child followed by
     * the root then finally all the nodes of the right child.
     * 
     * @return a string that traverses the tree from the smallest to the largest
     */
    public String inOrder() {
        String in_order_left = "";
        String in_order_right = "";
        if (left != null)
            in_order_left = in_order_left + left.inOrder();
        if (right != null)
            in_order_right = in_order_right + right.inOrder();
        return in_order_left + "(" + data + ")" + in_order_right;
    }

    /**
     * Returns a String that represents the data held at each node starting with all the nodes of the left child, followed
     * by all the nodes of the right child, followed by the root value.
     * 
     * @return a string that traverses the tree following the post order
     */
    public String postOrder() {
        String in_order_left = "";
        String in_order_right = "";
        if (left != null)
            in_order_left = in_order_left + left.postOrder();
        if (right != null)
            in_order_right = in_order_right + right.postOrder();
        return in_order_left + in_order_right + "(" + data + ")";
    }

    /**
     * Main method testing
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // Create a BST with 12345678910
        // this will be used for testing
        int rootnode = 7;
        TreeNode<Integer> left_lowl = new TreeNode<Integer>(2, new TreeNode<Integer>(1), null);
        TreeNode<Integer> left_lowr = new TreeNode<Integer>(5, new TreeNode<Integer>(4), new TreeNode<Integer>(6));
        TreeNode<Integer> left = new TreeNode<Integer>(3, left_lowl, left_lowr);
        TreeNode<Integer> right = new TreeNode<Integer>(9, new TreeNode<Integer>(8), new TreeNode<Integer>(10));
        TreeNode<Integer> root = new TreeNode<Integer>(rootnode, left, right);
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(root);

        System.out.println(tree.size());   // test the size, prints 10
        System.out.println(tree.height());  // test height, prints 4
        System.out.println(tree.find(7));  // test if 7 is in the tree, prints true
        System.out.println(tree.insert(11));   // test if 11 can be inserted, prints true
        System.out.println(tree.inOrder());   // test inOrder method, prints (1)(2)(3)(4)(5)(6)(7)(8)(9)(10)(11)
        System.out.println(tree.postOrder()); // test postOrder method, prints (1)(2)(4)(6)(5)(3)(8)(11)(10)(9)(7)
        System.out.println(tree.delete(8));  // delete an element
        System.out.println(tree.toString());   // print out the tree to check if it's been deleted

    }

}
