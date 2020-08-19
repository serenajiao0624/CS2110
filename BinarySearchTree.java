import java.util.ArrayList;

/**
 * Binary Search Tree Class The head class for a binary search tree implementation.
 * 
 * @author Serena Jiao yj5qe
 * @param <Comparable> Type of data to store in the binary tree
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * A reference pointer to the root of the tree
     */
    private TreeNode<T> root;

    /**
     * Default constructor Creates a binary tree object with null root note (empty tree)
     */
    public BinarySearchTree() {
        this(null);
    }

    /**
     * Constructor Creates a binary tree object with the given node as root
     * 
     * @param newRoot The root of the tree
     */
    public BinarySearchTree(TreeNode<T> newRoot) {
        this.root = newRoot;
    }

    /**
     * Get the root of the tree
     * 
     * @return The root of the tree
     */
    public TreeNode<T> getRoot() {
        return root;
    }

    /**
     * Set the root of the tree
     * 
     * @param root The new root of this tree
     */
    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    /**
     * Find if an element exists Checks to see if the value val appears in the tree (recursively). Returns true if it
     * appears and false otherwise.
     * 
     * @param val The value to find
     * @return True if the tree contains the value, false otherwise
     */
    public boolean find(T val) {
        if (root == null)   // check if the tree is null
            return false;
        return root.find(val);
    }

    /**
     * Returns a String that represents the data held at each node starting with all the nodes of the left child followed by
     * the root then finally all the nodes of the right child.
     * 
     * @return a string that traverses the tree from the smallest to the largest
     */
    public String inOrder() {
        if (root == null)  // check if the tree is null
            return null;
        return root.inOrder();
    }

    /**
     * Returns a String that represents the data held at each node starting with all the nodes of the left child, followed
     * by all the nodes of the right child, followed by the root value.
     * 
     * @return a string that traverses the tree following the post order
     */
    public String postOrder() {
        if (root == null)   // check if the tree is null
            return null;
        return root.postOrder();
    }

    /**
     * Insert an element Inserts val into the tree where it should appear, returning true on success and false otherwise
     * 
     * @param val The value to insert
     * @return True on success, false otherwise Since BST doesn't allow for duplicates, return false when the value can be
     *         found
     */
    public boolean insert(T val) {
        if (root == null) {  // check if the tree is null
            setRoot(new TreeNode<T>(val));  // If so, set the inserted element as the root
            return true;    // return true
        }
        return root.insert(val);
    }

    /**
     * Delete an element from the tree Deletes val from the tree if it appears, returning true on success and false
     * otherwise
     * 
     * @param val The value to delete
     * @return True on success, false otherwise
     */

    public boolean delete(T val) {
        // check if the root or the value is null
        if (root == null || val == null)
            return false;  // if so, return false
        // if the value one's trying to delete does not exist
        if (find(val) == false)
            return false;  // if so, return false
        root = root.delete(val);   // call the method from the TreeNode class
        return true;
    }

    /**
     * Build from a list Build the tree from the given list, overwriting any tree data previously stored in this tree.
     * Should read from beginning to end of the list and repeatedly call insert() to build the tree.
     * 
     * @param list The list from which to build the tree
     * @return True if successfully built, false otherwise
     */
    public boolean buildFromList(ArrayList<T> list) {
        // Check if the list the null
        if (list == null)
            return false;
        // Check if there's any element in the arraylist
        if (list.size() == 0)
            return false;
        // Iterate through the arraylist, insert the element one by one
        for (T i : list) {
            if (i != null)  // check if the inserted element is null
                insert(i);
        }
        return true;
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
        return root.inOrder();   // Prints out the string in order
    }

    /**
     * The method will return an int that represents the height of the total tree.
     * 
     * @return the height of a BST
     */
    public int height() {
        if (root == null)
            return 0;
        return root.height();

    }

    /**
     * The method will return an int that represents the size of the total tree.
     * 
     * @return the size of a BST
     */
    public int size() {
        if (root == null)
            return 0;
        return root.size();
    }

}
