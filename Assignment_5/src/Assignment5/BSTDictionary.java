package Assignment5;

/**
 * The Binary Search Tree Dictionary for Assignment 5.
 *
 * @param <E> Represents the data type of the element.
 * @param <K> Represents the data type of the key.
 * @author Akaash Kapoor
 * @date 4/7/2020
 */
public class BSTDictionary<E, K extends Sortable> implements Dictionary<E, K> {

    //Instance Variables
    private BSTNode<E, K> root; //the root of the tree.
    private BSTNode<E, K> currNode; //the current node its referencing.


    /**
     * Instantiates a new BST Dictionary.
     */
    public BSTDictionary() {
        this.root = null;
        this.currNode = null;
    }


    @Override
    /*
     * Searches for a node with input parameters of key.
     */
    public Object search(Sortable key) {

        if (currNode == null) { //if the current node is not available. Set the root to current node and return null. This acts like a base case.
            currNode = root;
            return null;
        } else if (key.compareTo(currNode.getKey()) == 0) { //If the value is found return the key.
            Object temp = currNode.getKey();
            currNode = root;
            return temp;
        } else if (key.compareTo(currNode.getKey()) < 0) { //if the key is less than the key of current node, adjust reference and do a recursive call.
            currNode = currNode.getLeft();
            return search(key);
        } else { //if the key is greater than current node key, adjust the current node reference and do a recursive call.
            currNode = currNode.getRight();
            return search(key);
        }//end if
    }//end method

    @Override
    /*
     * Inserts a node at the leaves of the Binary Search Tree.
     */
    public void insert(K key, E element) {
        if(root == null){
            root = new BSTNode<>(key, element, null, null);
        } else {
            recursiveInsert(root, key, element);
        }
    }//end method

    private void recursiveInsert(BSTNode curr, K key, E element){

         if(curr.getLeft()==null) {
            curr.setLeft(new BSTNode<>(key,element, null, null));
        } else if (curr.getRight() == null){
            curr.setRight(new BSTNode<>(key,element, null, null));
        } else if (key.compareTo(curr.getKey()) < 0){
            recursiveInsert(curr.getLeft(), key, element);
        } else {
            recursiveInsert(curr.getRight(), key, element);
        }
    }

    @Override
    /*
     * This method deletes a node from the Binary Search Tree.
     */
    public void delete(K key) {
        this.root = recursiveDeleteNode(root, key); //calls a helper method for a recursive method.
    }//end method

    //Helper method to delete() method.
    private BSTNode<E, K> recursiveDeleteNode(BSTNode<E, K> rootNode, Sortable key) {
        //Throws an illegal argument exception if the key is not valid.
        if (key == null) {
            throw new IllegalArgumentException("Invalid Key");
        }//end if

        //Base case: If the rootNode is null, then return a null value.
        if (rootNode == null) {
            return null;
        }//end if

        //If the key is less than the key of the root, then recursive call the left side of the node. This searches for the key on the left side of the tree
        if (key.compareTo(rootNode.getKey()) < 0) {
            rootNode.setLeft(recursiveDeleteNode(rootNode.getLeft(), key));
        } else if (key.compareTo(rootNode.getKey()) > 0) { //If the key is greater than the key of the root, recursive call the right side of the node. This searches for the key on the right side of the tree.
            rootNode.setRight(recursiveDeleteNode(rootNode.getRight(), key));
        } else {
            //After finding the node to delete (the same key) evaluate set conditions.
            if (rootNode.getLeft() == null && rootNode.getRight() == null) { //if the node to delete has no children, return null. This ensures that the recursive calls in the previous statements set a null value.
                return null;
            } else if (rootNode.getLeft() != null && rootNode.getRight() != null) { //if the node has two children, more intricate steps need.
                BSTNode<E, K> temp = findMinTraversal(rootNode.getRight()); //helper method is called to find the leftmost minimum node on the right side of the tree.
                rootNode.key = temp.getKey(); //copies key to root node.
                rootNode.setElement(temp.getElement()); //copies element to root node.
                recursiveDeleteNode(rootNode.getRight(), temp.getKey()); //tries to recursively delete the temp node. Will have at most one child node.

            } else {
                //If root node only has one child, these conditions will evaluate if the child is on the left or right branch.
                if (rootNode.getLeft() != null) {
                    rootNode = rootNode.getLeft();
                } else {
                    rootNode = rootNode.getRight();
                } //end if

            }//endif
        }//endif
        return rootNode;
    }//end helper method

    //Helper method for finding the leftmost minimum node on the right side of the tree.
    private BSTNode<E, K> findMinTraversal(BSTNode<E, K> minCurrNode) {
        while (minCurrNode.getLeft() != null) { //iterate through the loop until a contender is found.
            minCurrNode = minCurrNode.getLeft();
        }//end while
        return minCurrNode;
    }//end helper method


    @Override
    /*
     *This method prints the tree in inorder way.
     */
    public void printTree() {
        recursivePrintTree(root);//calls the recursive helper method.
    }

    //Helper method for recursive printing the tree results.
    private void recursivePrintTree(BSTNode<E, K> curr) {
        //Base case: if the current Node is null, that terminates the in order process of printing the tree.
        if (curr == null) {
            return;
        }
        //Inorder printing. first the left most nodes, then the sub root nodes, then the right most nodes.
        recursivePrintTree(curr.getLeft());
        System.out.println(curr.key);
        recursivePrintTree(curr.getRight());
    }//end helper method.

    @Override
    /*
     *Prints the depth of the tree.
     */
    public int depth() {
        return recursiveMaxDepth(root);//recursive helper method.
    }

    //Helper method for printing the max depth using recursion.
    private int recursiveMaxDepth(BSTNode<E, K> curr) {
        //Base case: If the current node is null, you've reached the bottom of the tree.
        if (curr == null) {
            return 0;
        } else {
            int leftSide = recursiveMaxDepth(curr.getLeft()); //tally up the left side and right side depth.
            int rightSide = recursiveMaxDepth(curr.getRight());
            if (leftSide > rightSide) {
                return leftSide + 1;
            } else {
                return rightSide + 1;
            }//end if
        }//end if
    }//end helper method.

}//end class
