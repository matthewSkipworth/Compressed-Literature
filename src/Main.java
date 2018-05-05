//Matthew Skipworth

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
/**
 * The class Main drives the Compressed-Literature program.
 * @author Matthew Skipworth
 * @version 4 May, 2018
 */

public class Main {
/**
 * The main method drives the program. Each program requires a main method.
 * @param args represents an array of String arguments which may be passed
 * into the terminal. This feature is not used in this program.
 * @throws IOException if no file is found.
 */
	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();
		BufferedReader input = null;
		//DataOutputStream output = null;
		DataOutputStream output = null;
		Writer outputCodes = null;
		
		File inFile = new File("WarAndPeace.txt");
		//File inFile = new File("HuckFinn.txt");
		File outFile = new File("output.txt");
		File codes = new File("codes.txt");

		try {
			input = new BufferedReader(new FileReader(inFile));
			//output = new DataOutputStream(new FileOutputStream(outFile));
			output = new DataOutputStream(new FileOutputStream(outFile));
			outputCodes = new BufferedWriter(new FileWriter(codes));
         
		} catch (IOException e) {
			System.out.println("File not found.");
		}
		List<Character> TreeNodeList = new ArrayList();
		StringBuilder charString = new StringBuilder();
		while (input.ready()) {
			try {
				char newChar = (char) input.read();
				charString.append(newChar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CodingTree newTree = new CodingTree(charString);
			newTree.writeMap(outputCodes, newTree.codes);
		
		StringBuilder bitString = new StringBuilder();
		
		for (int i = 0; i < charString.length(); i++) {
			char tempChar = charString.charAt(i);
			bitString.append(newTree.codes.get(tempChar));
		}
		for (int i = 0; i < (8 - (bitString.length() % 8)); i++) {
			bitString.append('0');
		}
		for (int i = 0; i < bitString.length() - 8; i += 8) {
			StringBuilder byteString = new StringBuilder();
			int tempSum = 0;
			for (int j = i; j < i + 8; j++) {
				if (bitString.charAt(j) == '1') {
					tempSum = tempSum << 1;
					tempSum = tempSum | 1;
				} else {
					tempSum = tempSum << 1;
				}
			}
			char tempChar = (char) tempSum;
			output.write(tempChar);
		}
		long endTime   = System.nanoTime();	
		long totalTime = endTime - startTime;
	System.out.println("The file to be compressed is " + inFile.length()/1000 
						+ "KB.");
	System.out.println("The compressed file is " + outFile.length()/1000 
						+ "KB.");
	double compRatio = (100.0 *outFile.length()/inFile.length());
	System.out.printf("The input file was compressed to %.4f", compRatio);
	System.out.println("% of its original size.");
	System.out.printf("The running time of this program is %.4f",  
						(1.0*totalTime / 1000000000));
	System.out.println("seconds.");
	input.close();
	outputCodes.close();
	output.close();
	//testCodingTree("Hello, I am Sam.");
	}
/**
 * testCodingTree tests the CodingTree class' methods.
 * @param newSB represents a message to be encoded.
 */
	static void testCodingTree(String newString) {
		StringBuilder newSB = new StringBuilder(newString);
		CodingTree nTree = new CodingTree(newSB);
		for (int i = 0; i < nTree.nodeList.size(); i++) {
			System.out.print(nTree.nodeList.get(i).data + 
						" : " + nTree.nodeList.get(i).frequency
						+ ", ");
		}
		nTree.getPriorities(newSB);
		nTree.createHuffmanTree(nTree.HuffmanQueue);
		System.out.println();
		nTree.createBitStrings(nTree.HuffmanRoot, "");
		System.out.println(nTree.codes);
		
	}	
	
}


