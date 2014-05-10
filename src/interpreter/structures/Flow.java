package interpreter.structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Flow {

	private ArrayList<String> code;
	private HashMap<String, Integer> labels;
	private int programCounter;
	private java.util.Stack<Integer> returnAddresses;
	private static Flow instance = null;
//	private boolean importing;
//	private ArrayList<String> imports;
//	private String path;
//	private String baseFile;
//	private String currentFile;

	private Flow() {
		code = new ArrayList<String>();
		programCounter = 0;
		returnAddresses = new java.util.Stack<Integer>();
		setReturnAddress(programCounter);
		labels = new HashMap<String, Integer>();
//		importing = false;
//		imports = new ArrayList<String>();
//		path = "";
//		baseFile = "";
//		currentFile = "";
	}

	public static Flow getInstanceOf() {
		if (instance == null) {

			instance = new Flow();
		}

		return instance;

	}

	public void read(String destination) {

	
		FileReader fileReader = null;

		try {
//			if (destination.endsWith(".ws")) {
//				Translator.getInstanceOf().translate(destination);
//				fileReader = new FileReader(destination + 'a');
//			} else {
				fileReader = new FileReader(destination);
//			}

			BufferedReader reader = new BufferedReader(fileReader);

			String instruction = reader.readLine();
			
			while (instruction != null) {

				if (instruction.equals("")) {
					instruction = reader.readLine();
					continue;
				} else if (instruction.startsWith("/")) {
					instruction = reader.readLine();
					continue;
				}

				instruction = instruction
						.substring(0, instruction.length() - 1);

//				if (instruction.startsWith("import")) {
//					
//					String importFile = instruction.split(" ")[1];
//
//					if(!importing){
//						String[] file = destination.split("\\\\");
//						for(int i = 0; i< file.length-1; i++){
//							path += file[i];
//						}
//						path += "\\";
//						baseFile = file[file.length-1].split("\\.")[0];
//						currentFile = baseFile;
//						importing = true;
//						
//						code.add("jump "+baseFile);
//						programCounter++;
//					}
//					
//					if(!imports.contains(importFile)){
//						imports.add(importFile);
//						String temp = currentFile;
//						currentFile = importFile;
//						read(path+importFile+".wsa");
//						currentFile = temp;
//					}
//					instruction = reader.readLine();
//					continue;
//				}
//				
//				if(baseFile.equals(currentFile)){
//					importing = false;
//				}

				code.add(instruction);

				if (instruction.split(" ")[0].equals("label")) {
					labels.put(instruction.split(" ")[1], programCounter);
				}
				programCounter++;

				instruction = reader.readLine();

			}

//			if (!importing){
				programCounter = 0;
//			}
			
			

//			for (int i = 0; i < code.size(); i++) {
//				System.out.println(i + ": " + code.get(i));
//			}

			reader.close();
			fileReader.close();

		} catch (IOException ioe) {
//			System.out.println("Shit happened while loading "+destination+" as part of "+baseFile);
		}

	}

	public void increaseProgramCounter() {
		programCounter++;
	}

	public void setProgramCounter(int i) {
		programCounter = i;
	}

	public int getProgramCounter() {
		return programCounter;
	}

	public String getLine(int i) {
		return code.get(i);
	}

	public void addLine(String line) {
		code.add(line);
	}

	public int getLabelPosition(String label) {
		return labels.get(label);
	}

	public int getReturnAddress() {
		return returnAddresses.pop();
	}

	public void setReturnAddress(int returnAddress) {
		returnAddresses.push(returnAddress);
		
	}

}
