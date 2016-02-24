package io.github.vyo.kairos.interpreter;

import io.github.vyo.kairos.interpreter.instructions.*;
import io.github.vyo.kairos.interpreter.structures.Flow;
import io.github.vyo.kairos.translator.Translator;
import io.github.vyo.kairos.importer.Importer;

public class Interpreter {

	private static Interpreter instance = null;
	private Flow flow;
	private Importer importer;
	//private Stack stack;
	//private Heap heap;
	//private IO io;
	private StackInstructions stackInstructions;
	private ArithmeticInstructions arithmeticInstructions;
	private HeapInstructions heapInstructions;
	private FlowInstructions flowInstructions;
	private IOInstructions ioInstructions;
	

	private final String[] arithmetic_instructions = { "add", "sub", "mul",
			"div", "mod" };
	private final String[] stack_instructions = { "push", "dupl", "copy",
			"swap", "pop", "slide" };
	private final String[] heap_instructions = { "store", "get" };
	private final String[] io_instructions = { "printc", "printn", "readc",
			"readn" };
	private final String[] flow_instructions = { "label", "call", "jump",
			"jzero", "jneg", "ret", "exit", };

	private Interpreter() {
		flow = Flow.getInstanceOf();
		importer = Importer.getInstanceOf();
		stackInstructions = new StackInstructions();
		arithmeticInstructions = new ArithmeticInstructions();
		heapInstructions = new HeapInstructions();
		flowInstructions = new FlowInstructions();
		ioInstructions = new IOInstructions();
		
	}

	public static Interpreter getInstanceOf() {
		if (instance == null) {

			instance = new Interpreter();
		}

		return instance;

	}

	private void execute(String... instruction) {

		
		String type = getType(instruction[0]);
		
		if(type.equals("stack")){
			stackInstructions.execute(instruction);
			flow.increaseProgramCounter();		}
		else if(type.equals("arithmetic")){
			arithmeticInstructions.execute(instruction);
			flow.increaseProgramCounter();		}
		else if(type.equals("heap")){
			heapInstructions.execute(instruction);
			flow.increaseProgramCounter();		}
		else if(type.equals("flow")){
			flowInstructions.execute(instruction);
		}
		else if(type.equals("io")){
			ioInstructions.execute(instruction);
			flow.increaseProgramCounter();
		}

	}

	private String getType(String instruction) {
		for (String s : stack_instructions) {
			if (s.equals(instruction)) {
				return "stack";
			}
		}
		for (String s : arithmetic_instructions) {
			if (s.equals(instruction)) {
				return "arithmetic";
			}
		}
		for (String s : heap_instructions) {
			if (s.equals(instruction)) {
				return "heap";
			}
		}
		for (String s : flow_instructions) {
			if (s.equals(instruction)) {
				return "flow";
			}
		}
		for (String s : io_instructions) {
			if (s.equals(instruction)) {
				return "io";
			}
		}

		return "";
	}

	public void run(String destination){
		
		if(destination.endsWith(".ws")){
			Translator.getInstanceOf().translate(destination);
			destination += 'a';
		}
		importer.resolve(destination, destination.substring(0, destination.length()-1)+'i');
		flow.read(destination.substring(0, destination.length()-1)+'i');
		
		String[] instruction = flow.getLine(flow.getProgramCounter()).split(" ");
		
		
		while(!instruction[0].equals("exit")){

//			System.out.print(flow.getProgramCounter()+": "+instruction[0]+" ");
//			if(instruction.length == 2){
//				System.out.println(instruction[1]);
//			}
//			else {
//				System.out.println();
//			}
			
			execute(instruction);
			instruction = flow.getLine(flow.getProgramCounter()).split(" ");
			
		}
	}
}
