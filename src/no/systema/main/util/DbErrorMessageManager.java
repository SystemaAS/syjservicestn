package no.systema.main.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 12, 2016
 * 
 */
public class DbErrorMessageManager {
	/**
	 * 
	 * @param writer
	 * @return
	 */
	public String getJsonValidDbException(Writer writer){
		String legend = "";
		//Chop the message to comply to JSON-validation
		if(writer!=null){
			legend = writer.toString();
			int limit = legend.indexOf("\n");
			legend = legend.substring(0, limit);
		}
		return legend;
	}
	/**
	 * Externalized exception 
	 * 
	 * @param e
	 * @return
	 */
	public Writer getPrintWriter(Exception e){
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);
		return writer;
	}
}
