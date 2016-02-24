package io.github.vyo.kairos.interpreter.instructions;

import java.math.BigInteger;

import io.github.vyo.kairos.interpreter.structures.Stack;
import io.github.vyo.kairos.translator.Translator;

public class StackInstructions extends Instructions {

	private Stack stack;

	public StackInstructions() {
		stack = Stack.getInstanceOf();
	}

	public void push(BigInteger b) {
		stack.push(b);
	}

	public void duplicate() {
		stack.push(stack.peek());
	}

	public void copy(int n) {
		BigInteger copy;
		java.util.Stack<BigInteger> temp = new java.util.Stack<BigInteger>();

		for (int i = 0; i < n; i++) {
			temp.push(stack.pop());
		}
		copy = temp.peek();
		while (!temp.isEmpty()) {
			stack.push(temp.pop());
		}
		stack.push(copy);
	}

	public void swap() {
		BigInteger first = stack.pop();
		BigInteger second = stack.pop();
		stack.push(first);
		stack.push(second);
	}

	public void pop() {
		stack.pop();
	}

	public void slide(int n) {
		BigInteger temp = stack.pop();

		for (int i = 0; i < n; i++) {
			stack.pop();
		}

		stack.push(temp);
	}

	@Override
	public void execute(String... instruction) {

		if (instruction[0].equals("push")) {
			try {
				push(new BigInteger(instruction[1]));
			} catch (NumberFormatException nfe) {
				if (instruction[1].startsWith("0x")) {
					push(new BigInteger(instruction[1].substring(2), 16));
				}
				else if (instruction[1].startsWith("Ox")) {
					push(new BigInteger(instruction[1].substring(2), 16));
				}
				else if (instruction[1].startsWith("b") && Character.isDigit(instruction[1].charAt(1))) {
					push(new BigInteger(instruction[1].substring(1), 2));
				} else {
					
					String temp = "";
					for(int i = 1; i < instruction.length-1; i++){
						temp += instruction[i];
						temp += " ";
					}
					temp += instruction[instruction.length-1];
					
					push(new BigInteger(Translator.toDecimal(Translator
							.toBinaryLabel(temp))));
				}
			}

		} else if (instruction[0].equals("dupl")) {
			duplicate();
		} else if (instruction[0].equals("copy")) {
			copy(Integer.parseInt(instruction[1]));
		} else if (instruction[0].equals("swap")) {
			swap();
		} else if (instruction[0].equals("pop")) {
			pop();
		} else if (instruction[0].equals("slide")) {
			slide(Integer.parseInt(instruction[1]));
		}
	}

}
