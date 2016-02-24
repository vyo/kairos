package io.github.vyo.kairos.translator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Translator {

    private static Translator instance = null;

    // stack
    static final String stack = " ";

    static final String push = stack + " "; // push number onto stack
    static final String dupl = stack + "\n "; // duplicate top of stack
    static final String copy = stack + "\t  "; // copy item nth item onto stack
    static final String swap = stack + "\n\t"; // swap two top items of stack
    static final String pop = stack + "\n\n"; // discard top item of stack
    static final String slide = stack + "\t\n"; // slide n items off the stack,
    // keep top

    // arithmetic
    static final String math = "\t "; // uses top two items on stack
    // top item is right hand operand
    static final String add = math + "  ";
    static final String sub = math + " \t";
    static final String mul = math + " \n";
    static final String div = math + "\t ";
    static final String mod = math + "\t\t";

    // heap
    static final String heap = "\t\t"; // push address then value to store
    // push address to retrieve item to top
    // consumes value or value and address
    static final String store = heap + " ";
    static final String get = heap + "\t";

    // flow control
    static final String flow = "\n";

    static final String label = flow + "  "; // create a label
    static final String call = flow + " \t"; // call subroutine
    static final String jump = flow + " \n"; // unconditional
    static final String jzero = flow + "\t "; // jump if top of stack is zero
    static final String jneg = flow + "\t\t"; // jump if top of stack is
    // negative
    static final String ret = flow + "\t\n"; // return from subroutine
    static final String exit = flow + "\n\n"; // exit program

    // io
    static final String io = "\t\n";

    static final String printc = io + "  "; // print char at top of stack
    static final String printn = io + " \t"; // print number at top of stack
    static final String readc = io + "\t "; // store char in heap address given
    // by top of stack, consumes top of
    // stack
    static final String readn = io + "\t\t"; // store number in heap given by
    // top of stack, consumes top of
    // stack

    static final String[] instructionsWS = {push, dupl, copy, swap, pop,
            slide, add, sub, mul, div, mod, store, get, label, call, jump,
            jzero, jneg, ret, exit, printc, printn, readc, readn};
    static final String[] instructionsWSA = {"push", "dupl", "copy", "swap",
            "pop", "slide", "add", "sub", "mul", "div", "mod", "store", "get",
            "label", "call", "jump", "jzero", "jneg", "ret", "exit", "printc",
            "printn", "readc", "readn"};

    private Translator() {
    }

    public static Translator getInstanceOf() {
        if (instance == null) {

            instance = new Translator();
        }

        return instance;

    }

    /**
     * Convert a number from binary to decimal.
     *
     * @param s A string representing a number in binary
     * @return A string representing a number in decimal
     */
    public static String toDecimal(String s) {

        boolean negative = false;

        if (s.charAt(0) == '1') {
            negative = true;
            s = s.substring(1);
        }

        BigInteger n = new BigInteger("0");

        for (int i = s.length() - 1; i > -1; i--) {
            if (s.charAt(i) == '1') {
                n = n.add(new BigInteger("2").pow(s.length() - 1 - i));
            }
        }

        if (negative) {
            return '-' + n.toString();
        }
        return n.toString();
    }

    /**
     * Converting a number from decimal to binary.
     *
     * @param s A string representing a number in decimal
     * @return A string representing a number in binary
     */
    public static String toBinary(String s) {

        boolean negative = false;
        if (s.charAt(0) == '-') {
            negative = true;
            s = s.substring(1);
        }

        String n = "";

        BigInteger temp = new BigInteger(s);

        while (!temp.equals(BigInteger.ONE) && !temp.equals(BigInteger.ZERO)) {
            if (temp.testBit(0)) {
                n = '1' + n;
                temp = temp.clearBit(0);
            } else {
                n = '0' + n;
            }
            temp = temp.divide(BigInteger.valueOf(2));
        }
        if (temp.testBit(0)) {
            n = '1' + n;
        }

        if (negative) {
            return '1' + n;
        }
        return '0' + n;
    }

    /**
     * Converts a whitespace label to a string Encoding: ASCII
     *
     * @param s A string consisting of 0s and 1s representing char encodings
     * @return A string consisting of chars
     */
    public static String toStringLabel(String s) {

        String temp = "";
        int l = s.length() % 8;
        if (l != 0) {
            for (int i = 0; i < 8 - l; i++) {
                s = '0' + s;
            }
        }

        for (int i = 0; i < s.length(); i += 8) {
            temp += (char) Integer.parseInt(s.substring(i, i + 8), 2);
        }

        char[] array = temp.toCharArray();
        temp = "";

        // check for character non-conform to the ascii encoding
        for (char c : array) {
            if ((int) c < 33 || (int) c > 126) {
                temp += '$';
                temp += String.valueOf((int) c);
            } else {
                temp += c;
            }
        }

        return temp;

    }

    /**
     * Converts a string to a whitespace label Encoding: ASCII (+sign bit since
     * Whitespace has only signed values)
     *
     * @param s A string consisting of chars
     * @return A string consisting of zeros and ones representing char encodings
     */
    public static String toBinaryLabel(String s) {

        String temp = "";

        for (char c : s.toCharArray()) {
            String temp2 = Integer.toBinaryString(c);
            for (int i = 0; i < 8 - temp2.length(); i++) {
                temp += '0';
            }
            // if (Integer.signum((int)c) == -1){
            // temp += '1';
            // }
            // else {
            // temp += '0';
            // }
            temp += temp2;
        }

        return temp;
    }

    /**
     * Converts all 0s and 1s in a string to spaces and tabs
     *
     * @param s A string consisting of 0s and 1s.
     * @return A string consisting of spaces tabs
     */
    public static String toWhitespaceEncoding(String s) {
        String t = "";

        for (char c : s.toCharArray()) {
            if (c == '1') {
                t += '\t';
            } else {
                t += ' ';
            }
        }

        return t;
    }

    /**
     * Converts all spaces and tabs in a string to 0s and 1s
     *
     * @param s A string consisting of spaces and tabs.
     * @return A string consisting of 0s and 1s
     */
    public static String toBinaryEncoding(String s) {
        String t = "";

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                t += '0';
            } else if (c == '\t') {
                t += '1';
            }
        }

        return t;
    }

    /**
     * Makes whitespace visible
     *
     * @param s A string consisting of ' ', '\t' ,'\n'
     * @return A string consisting of [Space], [Tab], [LF]
     */
    public static String enlighten(String s) {

        String temp = "";

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                temp += "[Space]";
            } else if (c == '\t') {
                temp += "[Tab]";
            } else if (c == '\n') {
                temp += "[LF]";
            }
        }

        return temp;
    }

    public void translate(String arg) {

        FileReader reader;
        FileWriter writer;
        File source = new File(arg);
        File destination;

        if (arg.endsWith("a")) {
            destination = new File(arg.substring(0, arg.length() - 1));
        } else {
            destination = new File(arg + 'a');
        }

        if (destination.exists()) {
            destination.delete();
        }

        try {

            reader = new FileReader(source);
            writer = new FileWriter(destination);

            if (arg.endsWith(".wsa")) {

                toWS(arg);

            } else if (arg.endsWith(".ws")) {

                toWSA(arg);

            }
            reader.close();
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Conversion failed - could not process files.");
        } finally {

        }

    }

    private void toWS(String wsaFile) {
        FileReader reader;
        FileWriter writer;
        File source = new File(wsaFile);
        File destination;

        try {

            destination = new File(wsaFile + 'a');
            if (destination.exists()) {
                destination.delete();
            }

            reader = new FileReader(source);
            writer = new FileWriter(destination);

            StringBuffer buffer = new StringBuffer("");

            int in = reader.read();

            while (in != -1) {

                if ((char) in == ' ' || (char) in == '\t' || (char) in == '\n') {
                    buffer.append((char) in);
                }

                for (int i = 0; i < instructionsWS.length; i++) {
                    if (buffer.toString().equals(instructionsWS[i])) {
                        writer.append(instructionsWSA[i]);

                        String temp = "";

                        if (i == 0 || i == 2 || i == 5) {
                            char c = (char) reader.read();
                            while (c != '\n') {
                                temp += c;
                                c = (char) reader.read();
                            }
                            temp = Translator.toBinaryEncoding(temp);
                            temp = Translator.toDecimal(temp);
                            temp = " " + temp;

                        } else if (i == 13 || i == 14 || i == 15 || i == 16
                                || i == 17) {
                            char c = (char) reader.read();
                            while (c != '\n') {
                                temp += c;
                                c = (char) reader.read();
                            }
                            temp = Translator.toBinaryEncoding(temp);
                            temp = Translator.toStringLabel(temp);
                            temp = " " + temp;

                        }

                        temp += ";\n";
                        writer.append(temp);

                        buffer = new StringBuffer("");

                        break;
                    }
                }

                in = reader.read();
            }
            reader.close();
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Conversion failed - could not process files.");
        } finally {

        }
    }

    private void toWSA(String wsFile) {
        FileReader reader;
        FileWriter writer;
        File source = new File(wsFile);
        File destination;

        try {

            destination = new File(wsFile.substring(0, wsFile.length() - 1));
            if (destination.exists()) {
                destination.delete();
            }

            reader = new FileReader(source);
            writer = new FileWriter(destination);

            StringBuffer buffer = new StringBuffer("");

            if (wsFile.endsWith("a")) {
                destination = new File(wsFile.substring(0, wsFile.length() - 1));
            } else {
                destination = new File(wsFile + 'a');
            }

            if (destination.exists()) {
                destination.delete();
            }

            int in = reader.read();

            while (in != -1) {

                buffer.append((char) in);

                if (buffer.toString().equals("/")) {
                    buffer = new StringBuffer();
                    in = reader.read();
                    while ((char) in != '\n') {
                        in = reader.read();
                    }
                    in = reader.read();
                    continue;
                } else if (buffer.toString().equals("\n")) {
                    buffer = new StringBuffer();
                    in = reader.read();
                    continue;
                }

                for (int i = 0; i < instructionsWSA.length; i++) {

                    if (buffer.toString().equals(instructionsWSA[i])) {
                        writer.append(instructionsWS[i]);

                        String temp = "";
                        char c;

                        if (i == 0 || i == 2 || i == 5) {
                            reader.read(); // read the space between
                            // instruction and value
                            c = (char) reader.read();
                            while (c != ';') {
                                temp += c;
                                c = (char) reader.read();
                            }
                            temp = Translator.toBinary(temp);
                            temp = Translator.toWhitespaceEncoding(temp);
                            temp += '\n';

                        } else if (i == 13 || i == 14 || i == 15 || i == 16
                                || i == 17) {
                            reader.read(); // read the space between
                            // instruction and value
                            c = (char) reader.read();
                            while (c != ';') {
                                temp += c;
                                c = (char) reader.read();
                            }
                            temp = Translator.toBinaryLabel(temp);
                            temp = Translator.toWhitespaceEncoding(temp);
                            temp += '\n';

                        } else {
                            reader.read(); // read the semicolon at the end
                            // of instruction
                        }

                        reader.read(); // read the newline character after
                        // the instruction

                        writer.append(temp);

                        buffer = new StringBuffer("");

                        break;
                    }
                }

                in = reader.read();
            }
            reader.close();
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Conversion failed - could not process files.");
        } finally {

        }
    }

}
