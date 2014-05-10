package importer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import importer.Importer;

public class Importer {

	private static Importer instance = null;
	private ArrayList<String> imports;
	private FileWriter out;
	private BufferedWriter writer;
	private String source;
	private String path;
	private boolean labelSet;

	private Importer() {
		imports = new ArrayList<String>();
		labelSet = false;
	}

	public static Importer getInstanceOf() {
		if (instance == null) {

			instance = new Importer();
		}

		return instance;

	}

	public void resolve(String source, String destination) {
		try {

			FileReader in = new FileReader(source);
			BufferedReader reader = new BufferedReader(in);
			String[] dest = destination.split("\\\\");
			String main = dest[dest.length - 1];

			if (out == null) {
				this.source = source;
				path = "";

				String[] address = source.split("\\\\");
				//String label = address[address.length - 1];

				for (int i = 0; i < address.length - 1; i++) {
					path = path + address[i] + "\\";
				}

				out = new FileWriter(destination.substring(0, destination.length()-1)+'i');
				writer = new BufferedWriter(out);

				writer.append("jump " + main.split("\\.")[0] + "_MAIN;");
			}

			writer.append("\n//////////////////////////////////\n");

			String line;

			line = reader.readLine();

			while (line != null) {

				if (line.startsWith("import")) {
					if (imports.contains(line)) {
						line = reader.readLine();
						continue;
					}

					imports.add(line);

					line = line.split(" ")[1];

					resolve(path+line.substring(0, line.length()-1)+".wsa", destination);

				}
				else if(!labelSet && (source).equals(this.source)){
					writer.append("\n\n\n\nlabel " + main.split("\\.")[0] + "_MAIN;\n");
					labelSet = true;
				}
				
				

				writer.append(line);
				writer.append("\n");
				line = reader.readLine();

			}
			
			

			reader.close();
			in.close();

			if (source.equals(this.source)) {

				writer.close();
				out.close();
				
				if(destination.endsWith(".wsi")){
					return;
				}
				
				in = new FileReader(destination.substring(0, destination.length()-1)+'i');
				reader = new BufferedReader(in);				
				
				File file = new File(destination.substring(0, destination.length()-1)+'a');
				
				if(file.exists()){
					file.delete();
				}
				
				out = new FileWriter(destination);
				writer = new BufferedWriter(out);
				
				String l = reader.readLine();
				
				while(l != null){
					writer.append(l);
					writer.append('\n');
					l = reader.readLine();
				}
				
				
				
				reader.close();
				in.close();
				
				writer.close();
				out.close();
				
				
				file = new File(destination.substring(0, destination.length()-1)+'i');
				if(file.exists()){
					file.delete();
				}
			}


		} catch (IOException e) {
		}
	}

}
