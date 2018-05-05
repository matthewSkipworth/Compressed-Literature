//Matthew Skipworth
public class TreeNode implements Comparable<TreeNode> {
	
	int frequency;
	char data;
	//String code;
	
	TreeNode left;
	TreeNode right;
	
	public TreeNode(char theChar, TreeNode initialLeft, TreeNode initialRight) {
		data = theChar;
		frequency = 1;
		left = initialLeft;
		right = initialRight;

	} 
	//constructor used for new tree sums.
	public TreeNode(int newFrequency, TreeNode initialLeft, TreeNode initialRight) {
		frequency = newFrequency;
		left = initialLeft;
		right = initialRight;
	}
	void increaseFrequency() {
		frequency++;
	}
	public int compareTo(TreeNode otherNode) {
		int result = 0;
		result = this.frequency - otherNode.frequency;
		return result;
		
	}
	boolean isLeaf() {
		return ((this.left == null) && (this.right == null));
	}
}
