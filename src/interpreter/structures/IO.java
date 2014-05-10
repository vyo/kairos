package interpreter.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IO {
	private static IO instance = null;
	private BufferedReader in;

	private IO() {
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	public static IO getInstanceOf() {
		if (instance == null) {

			instance = new IO();
		}

		return instance;

	}

	public BufferedReader getUnderlying(){
		return in;
	}
	
	public String readLine(){
		try {
			return in.readLine();
		} catch (IOException e) {
			return "0";
		}
	}
	
	public int read(){
//		try {
//			return in.read();
//		} catch (IOException e) {
//			return 0;
//		}
		String input = readLine();
		if(input.equals("")){
			return 10;
		}
		else if (input.startsWith("0x")){
			return Integer.parseInt(input.substring(2), 16);
		}
		return (int)input.charAt(0);
	}
}
