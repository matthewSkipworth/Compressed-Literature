//Matthew Skipworth

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;


public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader input = null;
		//DataOutputStream output = null;
		DataOutputStream output = null;
		Writer outputCodes = null;
		
		File inFile = new File("WarAndPeace.txt");
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
	input.close();
	outputCodes.close();
	output.close();
	}

	void testCodingTree(StringBuilder newSB) {
		newSB = new StringBuilder("Hello. I am Sam.");
		CodingTree nTree = new CodingTree(newSB);
		
	}	
	
}


