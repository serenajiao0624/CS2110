import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class HW7Tests {
    // JUNIT Testing for HW7
    // Serena Jiao, yj5qe

    /**
     * Testing for getting the size of the total tree
     */
    @Test(timeout = 100)
    public void testSize() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        assertEquals(0, tree.size()); // no element, should be 0
        tree.insert(3);
        tree.insert(2); // inserting two elements
        assertEquals(2, tree.size());
    }

    /**
     * Testing for getting the height of the total tree
     */
    @Test(timeout = 100)
    public void testHeight() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();  // create a new tree
        assertEquals(0, tree.height());  // no element, should be 0
        tree.insert(3);
        tree.insert(4);
        tree.insert(10);  // inserting three elements
        assertEquals(3, tree.height());
    }

    /**
     * Testing for finding an element
     */
    @Test(timeout = 100)
    public void testFind() {
        // First make a tree with some elements
        TreeNode<Integer> root = new TreeNode<Integer>(7);
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(root);
        tree.insert(4);
        tree.insert(3);
        assertTrue(tree.find(4));  // 4 exists
        assertTrue(tree.find(3));   // 3 exists
        assertFalse(tree.find(2));  // 2 doesn't exist

    }

    /**
     * Testing for inserting an element
     */
    @Test(timeout = 100)
    public void testInsert() {
        // Start with an empty tree
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        assertTrue(tree.insert(2));
        assertTrue(tree.insert(10));
        assertFalse(tree.insert(10));  // duplicates
    }

    /**
     * Testing for inOrder method
     */
    @Test(timeout = 100)
    public void testInOrder() {
        // Create a new tree with some elements
        TreeNode<Integer> root = new TreeNode<Integer>(7);
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(root);
        tree.insert(4);
        tree.insert(3);
        assertEquals("(3)(4)(7)", tree.inOrder());
        tree.insert(2);
        tree.insert(1);
        assertEquals("(1)(2)(3)(4)(7)", tree.inOrder());

    }

    /**
     * Testing for postOrder method
     */
    @Test(timeout = 100)
    public void testPostOrder() {
        // Create a new tree with some elements
        TreeNode<Integer> root = new TreeNode<Integer>(7);
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(root);
        tree.insert(4);
        tree.insert(3);
        assertEquals("(3)(4)(7)", tree.postOrder());  // test if it is in post order
        tree.insert(2);
        tree.insert(1);
        assertEquals("(1)(2)(3)(4)(7)", tree.postOrder());
    }

    /**
     * Test toString method for printing trees
     */
    @Test(timeout = 100)
    public void testToString() {
        // Create a new tree with some elements
        TreeNode<Integer> root = new TreeNode<Integer>(7);
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(root);
        tree.insert(4);
        tree.insert(3);
        assertEquals("(3)(4)(7)", tree.toString());  // see if the tree is printed in order
        tree.insert(2);
        tree.insert(1);
        assertEquals("(1)(2)(3)(4)(7)", tree.toString());
    }

    /**
     * Testing the method for building a tree from an arraylist
     */
    @Test(timeout = 100)
    public void testBuildFromList() {
        // First make an empty tree and an empty arraylist
        BinarySearchTree<Integer> test_tree = new BinarySearchTree<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        assertFalse(test_tree.buildFromList(list));   // false because the list is empty
        list.add(1);
        list.add(4);
        list.add(11);
        list.add(20);
        list.add(3);
        list.add(5);
        list.add(12);
        test_tree.buildFromList(list);
        assertEquals("(1)(3)(4)(5)(11)(12)(20)", test_tree.toString());
        assertTrue(test_tree.buildFromList(list));
    }

    /**
     * Testing the deleting method
     */
    @Test(timeout = 100)
    public void testDelete() {
        // Make a tree
        TreeNode<Integer> root = new TreeNode<Integer>(7);
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(root);
        assertFalse(tree.delete(9));  // false because 9 doesn't exist
        tree.insert(4);
        tree.insert(3);    // inserting some elements
        assertTrue(tree.delete(4));
        tree.insert(11);
        assertTrue(tree.delete(11));
        assertEquals("(3)(7)", tree.toString());  // print out the tree to check if the elements are deleted
    }
}
