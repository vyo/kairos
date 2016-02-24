package io.github.vyo.kairos.interpreter.structures;

import java.math.BigInteger;

public class Heap {
	
	private java.util.HashMap<BigInteger, BigInteger> heap;
	private static Heap instance = null;

	private Heap() {
		heap = new java.util.HashMap<BigInteger, BigInteger>();
	}

	public static Heap getInstanceOf() {
		if (instance == null) {

			instance = new Heap();
		}

		return instance;

	}
	
	public void put(BigInteger key, BigInteger value){
		heap.put(key, value);
	}
	
	public BigInteger get(BigInteger key){
		BigInteger b= heap.get(key);
		heap.remove(key);
		return b;
	}
}
