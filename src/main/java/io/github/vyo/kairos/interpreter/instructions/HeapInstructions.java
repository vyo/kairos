package io.github.vyo.kairos.interpreter.instructions;

import io.github.vyo.kairos.interpreter.structures.Heap;
import io.github.vyo.kairos.interpreter.structures.Stack;

import java.math.BigInteger;

public class HeapInstructions extends Instructions {

private Heap heap;
private Stack stack;
	
	public HeapInstructions(){
		heap = Heap.getInstanceOf();
		stack = Stack.getInstanceOf();
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
