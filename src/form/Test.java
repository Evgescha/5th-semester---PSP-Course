package form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test {

public static void main(String[] args) {
	File file = new File("../file.bat");
	PrintWriter pw = null;
	try {
		pw = new PrintWriter(file);
		
	}
	catch (FileNotFoundException ex){
		System.out.println(ex.toString());
	}
	
	//pw.write("JOJOJOJ");
	pw.write("echo jojops");
	pw.flush();
	
	pw.close();
}
}
