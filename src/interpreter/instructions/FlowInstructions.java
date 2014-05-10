package interpreter.instructions;

import java.math.BigInteger;

import interpreter.structures.Flow;
import interpreter.structures.Stack;

public class FlowInstructions extends Instructions {

	private Flow flow;
	private Stack stack;
	
	public FlowInstructions(){
		flow = Flow.getInstanceOf();
		stack = Stack.getInstanceOf();
	}
	
	private void jump(String label) {
		flow.setProgramCounter(flow.getLabelPosition(label));
	}
	
	private void ret(){
		flow.setProgramCounter(flow.getReturnAddress());
	}
	
	@Override
	public void execute(String... instruction){
		if(instruction[0].equals("jump")){
			jump(instruction[1]);
		}
		else if(instruction[0].equals("call")){
			flow.setReturnAddress(flow.getProgramCounter()+1);
			jump(instruction[1]);
		}
		else if(instruction[0].equals("ret")){
			ret();
		}
		else if(instruction[0].equals("jneg")){
			
			if(stack.peek().signum() == -1){
				jump(instruction[1]);
			}
			else{
				flow.increaseProgramCounter();
			}
			stack.pop();

		}
		else if(instruction[0].equals("jzero")){
			
			if(stack.peek().equals(BigInteger.ZERO)){
				jump(instruction[1]);
			}
			else{
				flow.increaseProgramCounter();
			}
			stack.pop();
		}
		else if(instruction[0].equals("label")){
			flow.increaseProgramCounter();
		}
	}

}
