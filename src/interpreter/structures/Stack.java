package interpreter.structures;

import java.math.BigInteger;

public class Stack {

	private java.util.Stack<BigInteger> stack;
	private static Stack instance = null;
//	final private int MAX_SEQUENCE_LENGTH = 100000000;

	private Stack() {
		stack = new java.util.Stack<BigInteger>();
	}

	public static Stack getInstanceOf() {
		if (instance == null) {

			instance = new Stack();
		}

		return instance;

	}
	
	public BigInteger pop(){
		return stack.pop();
	}
	
	public BigInteger peek(){
		return stack.peek();
	}
	
	public void push(BigInteger b){
//		if(b.compareTo(new BigInteger("2").pow(MAX_SEQUENCE_LENGTH)) == -1){
			stack.push(b);
//		}
//		else {
//			stack.push(b.mod(new BigInteger("2").pow(MAX_SEQUENCE_LENGTH)));
//		}
		
	}
}
