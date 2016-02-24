package io.github.vyo.kairos.application;

import java.util.Scanner;

import io.github.vyo.kairos.translator.Translator;
import io.github.vyo.kairos.weaver.Weaver;
import io.github.vyo.kairos.importer.Importer;
import io.github.vyo.kairos.interpreter.Interpreter;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if(args.length == 0){
			Main.runInteractiveSession();
		}
		else if(args[0].equals("import")){
			Importer.getInstanceOf().resolve(args[1], args[2]);
		}
		else if(args[0].equals("run")){
			Interpreter.getInstanceOf().run(args[1]);
		}
		else if(args[0].equals("translate")){
			Translator.getInstanceOf().translate(args[1]);
		}
		else if(args[0].equals("weave")){
			Weaver.getInstanceOf().weave(args[1], args[2]);
		}
		else {
			Main.runInteractiveSession();
		}

	}

	private static void runInteractiveSession(){

		String input = "";
		Scanner console = new Scanner(System.in);

		System.out.println("Welcome to Kairos, a tool for the importing, interpreting, translating,");
		System.out.println("and weaving of code written in Whitespace or WhitespaceAssembler.\n");
		System.out.println("Where would you like to go?\n");
		System.out.println("\t(1) Importer");
		System.out.println("\t(2) Interpreter");
		System.out.println("\t(3) Translator");
		System.out.println("\t(4) Weaver");
		System.out.println("\t(5) Exit");

		Integer opCode = null;
		Boolean opCodeValid = false;

		while (!opCodeValid) {
			input = console.next();
			try {
				opCode = Integer.parseInt(input);
				if (opCode < 1 || opCode > 5) {
					System.out.print("Not a valid operation number - " + input);
					continue;
				}
				opCodeValid = true;
			} catch (NumberFormatException e) {
				System.out.print("Not a valid operation number - " + input);
			}
		}

		System.out.println("\tPlease specify the file(s) you want to run this command on:");

		String[] args = console.next().split(" ");

		switch (opCode) {
			case 1:
				Importer.getInstanceOf().resolve(args[0], args[1]);
				break;
			case 2:
				Interpreter.getInstanceOf().run(args[0]);
				break;
			case 3:
				Translator.getInstanceOf().translate(args[0]);
				break;
			case 4:
				Weaver.getInstanceOf().weave(args[0], args[1]);
				break;
			case 5:
				break;
		}

		console.close();

	}
}
