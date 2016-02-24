package io.github.vyo.kairos.weaver;

public class Weaver {

	private static Weaver instance = null;
	
	private Weaver() {}

	public static Weaver getInstanceOf() {
		if (instance == null) {

			instance = new Weaver();
		}

		return instance;

	}
	
	public String weave(String code, String carrier){
		
		StringBuffer codeBuffer = new StringBuffer(code);
		StringBuffer carrierBuffer = new StringBuffer(carrier);
		
		int index = 0;
		char nextChar;
		
		while(index < codeBuffer.length()){
			nextChar = codeBuffer.charAt(index);
		}
		
		
		return "";
	}
	
}
