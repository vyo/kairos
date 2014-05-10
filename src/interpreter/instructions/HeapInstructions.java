package interpreter.instructions;

import java.math.BigInteger;

public class HeapInstructions extends Instructions {

private interpreter.structures.Heap heap;
private interpreter.structures.Stack stack;
	
	public HeapInstructions(){
		heap = interpreter.structures.Heap.getInstanceOf();
		stack = interpreter.structures.Stack.getInstanceOf();
	}

	private void store(){
		BigInteger value = stack.pop();

		heap.put(stack.pop(), value);
	}
	
	private void get(){
		stack.push(heap.get(stack.pop()));
	}
	
	@Override
	public void execute(String... instruction){
		if(instruction[0].equals("store")){
			store();
		}
		else if(instruction[0].equals("get")){
			get();
		}
	}
}
