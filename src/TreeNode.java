//Matthew Skipworth
/**
 * The TreeNode Class represents a fairly general binary tree node which 
 * I will use to build the Huffman tree.
 * 
 * @author Matthew Skipworth
 * @version 4 May 2018
 */
public class TreeNode implements Comparable<TreeNode> {
	
	int frequency;
	char data;
	//String code;
	
	TreeNode left;
	TreeNode right;
/**
 * Class Constructor.	
 * @param theChar represents the nodes data.
 * @param initialLeft represents the nodes left child.
 * @param initialRight represents the nodes right child.
 */
	public TreeNode(char theChar, TreeNode initialLeft, 
					TreeNode initialRight) {
		data = theChar;
		frequency = 1;
		left = initialLeft;
		right = initialRight;

	} 
/**
 * A second Class Constructor used to instantiate nodes which have "weight"
 * but no data.	
 * @param newFrequency represents the node's "weight".
 * @param initialLeft represents the node's left child.
 * @param initialRight represents the node's right child.
 */
	//constructor used for new tree sums.
	public TreeNode(int newFrequency, TreeNode initialLeft, 
					TreeNode initialRight) {
		frequency = newFrequency;
		left = initialLeft;
		right = initialRight;
	}
/**
 * The method increaseFrequency increases the node's frequency or "weight".	
 */
	void increaseFrequency() {
		frequency++;
	}
/**
 * int compareTo compares two nodes based on their frequency. This method is 
 * used to place each node in a PriorityQueue.	
 */
	public int compareTo(TreeNode otherNode) {
		int result = 0;
		result = this.frequency - otherNode.frequency;
		return result;
		
	}
/**
 * The method isLeaf determines whether or not the TreeNode is a leaf.	
 * @return returns a boolean value based on whether or not the TreeNode is a 
 * Leaf Node.
 */
	boolean isLeaf() {
		return ((this.left == null) && (this.right == null));
	}
}
