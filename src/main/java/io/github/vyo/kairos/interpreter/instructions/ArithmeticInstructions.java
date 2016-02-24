package io.github.vyo.kairos.interpreter.instructions;

import io.github.vyo.kairos.interpreter.structures.Stack;

import java.math.BigInteger;

public class ArithmeticInstructions extends Instructions {

	private Stack stack;
	
	public ArithmeticInstructions(){
		stack = Stack.getInstanceOf();
	}
	
	private void add(){
		stack.push(stack.pop().add(stack.pop()));
	}
	
	private void sub(){
		BigInteger right = stack.pop();
		stack.push(stack.pop().subtract(right));
	}
	
	private void mul(){
//		BigInteger num1 = stack.pop();
//		BigInteger num2 = stack.pop();
//		
//		stack.push(num2);
//		stack.push(num1);
		
		
		stack.push(stack.pop().multiply(stack.pop()));
		
//		System.out.println("Computed: "+num1+" * "+num2+" = "+stack.peek());
//		System.out.println("Expected: "+num1.multiply(num2));
	}
	
	private void div(){
		BigInteger right = stack.pop();
		stack.push(stack.pop().divide(right));
	}
	
	private void mod(){
		BigInteger right = stack.pop();
		stack.push(stack.pop().mod(right));
	}
	
	@Override
	public void execute(String... instruction){
		if(instruction[0].equals("add")){
			add();
		}
		else if(instruction[0].equals("sub")){
			sub();
		}
		else if(instruction[0].equals("mul")){
			mul();
		}
		else if(instruction[0].equals("div")){
			div();
		}
		else if(instruction[0].equals("mod")){
			mod();
		}
	}

}
