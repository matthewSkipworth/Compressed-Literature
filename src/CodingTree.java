//Matthew Skipworth

import java.io.*;
import java.util.*;
/**
 * The CodingTree class takes in a StringBuilder object and with it 
 * constructs a Huffman tree. Once the Huffman tree is constructed, 
 * the CodingTree then stores the Characters and their keys into a 
 * HashMap field.
 * 
 * @author Matthew Skipworth
 * @version 4 May 2018
 *
 */
public class CodingTree {
	Map<Character, String> codes = new HashMap<Character,String>();
	String bits;
	List<TreeNode> nodeList;
	PriorityQueue<TreeNode> HuffmanQueue;
	TreeNode HuffmanRoot;
/**
 * Class Constructor.	
 * @param message is a StringBuilder object passed in to perform the 
 * CodingTree methods on.
 */
	CodingTree(StringBuilder message) {
		
		getPriorities(message);
		createHuffmanTree(HuffmanQueue);
		createBitStrings(HuffmanRoot, "");
		//System.out.println(HuffmanRoot);
		
	}
/**
 * method getPriorities takes in a StringBuilder object and creates a 
 * PriorityQueue based on the frequency of each character in the StringBuilder.
 * @param message represents a String to pull characters and their frequencies
 * from.
 */
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
/**
 * The createHuffmanTree method merges TreeNodes together until one tree 
 * remains. The TreeNodes are chosen using a built-in PriorityQueue object. 
 * 	
 * @param theQ represents the PriorityQueue generated by the GetPriorities
 * method.
 */
	void createHuffmanTree(PriorityQueue<TreeNode> theQ) {
		while (theQ.size() > 1) {
			TreeNode left = theQ.poll();
			TreeNode right = theQ.poll();
			TreeNode newNode = new TreeNode((left.frequency + right.frequency), left, right);
			theQ.offer(newNode);
		}
		HuffmanRoot = theQ.poll();
	}
/**
 * The method createBitStrings generates keys for each character in the 
 * StringBuilder object.
 * 
 * @param node
 * @param codeString
 */
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
/**
 * The method writeMap writes the codes field to a file. I found a method
 * on StackOverFlow that printed a map to the screen and adapted the method
 * to write out to a text file.
 * 	
 * @param outputCodes represents the File to be written to.
 * @param mp represents the codes field in the CodingTree object.
 */
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
