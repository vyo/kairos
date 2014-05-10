package interpreter.instructions;

import java.math.BigInteger;

public class IOInstructions extends Instructions {

	private interpreter.structures.Stack stack;
	private interpreter.structures.Heap heap;
	private interpreter.structures.IO io;

	public IOInstructions() {
		stack = interpreter.structures.Stack.getInstanceOf();
		heap = interpreter.structures.Heap.getInstanceOf();
		io = interpreter.structures.IO.getInstanceOf();
	}

	public void printn() {
		System.out.print(stack.pop());
	}

	public void printc() {
		System.out.print((char)stack.pop().intValue());
	}

	public void readn() {
		heap.put(stack.pop(), new BigInteger(io.readLine()));
	}
//
//	public void readc() {
//
//		heap.put(stack.pop(), BigInteger.valueOf((int) io.readLine().charAt(0)));
//
//	}

	public void readc() {

		heap.put(stack.pop(), BigInteger.valueOf(io.read()));

	}

	@Override
	public void execute(String... instruction) {
		if (instruction[0].equals("printn")) {
			printn();
		} else if (instruction[0].equals("printc")) {
			printc();
		} else if (instruction[0].equals("readn")) {
			readn();
		} else if (instruction[0].equals("readc")) {
			readc();
		}
	}

}
