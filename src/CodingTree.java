//Matthew Skipworth
/**
 * 
 */

import java.io.*;
import java.util.*;

public class CodingTree {
	Map<Character, String> codes = new HashMap<Character,String>();
	String bits;
	List<TreeNode> nodeList;
	PriorityQueue<TreeNode> HuffmanQueue;
	TreeNode HuffmanRoot;
	
	CodingTree(StringBuilder message) {
		
		getPriorities(message);
		createHuffmanTree(HuffmanQueue);
		createBitStrings(HuffmanRoot, "");
		//System.out.println(HuffmanRoot);
		
	}
	void getPriorities(StringBuilder message) {
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>(); //ArrayList nodes init.
		ArrayList<TreeNode> temp = new ArrayList<TreeNode>();
		for (int i = 0; i < message.length(); i++) {
			if (temp.size() > 2) {
				boolean found = false;
				for (int j = 0; j < temp.size(); j++) {
					if (temp.get(j).data == message.charAt(i)) {
						temp.get(j).increaseFrequency();
						found = true;
					} 
				}
				if (!found) {
					temp.add(new TreeNode(message.charAt(i), null, null));
				}
			} else {
				temp.add(new TreeNode(message.charAt(i), null, null));
			}
		}
		nodeList = new ArrayList<TreeNode>(temp);
		Collections.sort(nodeList);
		HuffmanQueue = new PriorityQueue<TreeNode>(nodeList);
	}
	
	void createHuffmanTree(PriorityQueue<TreeNode> theQ) {
		while (theQ.size() > 1) {
			TreeNode left = theQ.poll();
			TreeNode right = theQ.poll();
			TreeNode newNode = new TreeNode((left.frequency + right.frequency), left, right);
			theQ.offer(newNode);
		}
		HuffmanRoot = theQ.poll();
	}
	void createBitStrings(TreeNode node, String codeString) {
//		TreeNode left = root.left;
//		TreeNode right = root.right;
		if (node.isLeaf()) {
			//System.out.println(codeString);
			codes.put(node.data, codeString);
		} else { 
			if (node.left != null) {
				createBitStrings(node.left, codeString + "0");
			}	
			if (node.right != null) {
				createBitStrings(node.right, codeString + "1");
			}
		}
	}
	//writes the map out to a file.
	//https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
	public static void writeMap(Writer outputCodes, Map mp) {
	    Iterator it = mp.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        try {
				outputCodes.write(pair.getKey() + " = " + pair.getValue() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
}
