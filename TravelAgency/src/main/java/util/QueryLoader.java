/**
 * 
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author NGAL version 22.03.2012
 */
public class QueryLoader {
	public static String FILEPATH = "queries.sql";

	public static List<String> load() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				new File(FILEPATH)));
		StringBuilder veryBigQuery = new StringBuilder();
		String str;
		while ((str = bufferedReader.readLine()) != null) {
			veryBigQuery.append(str);
		}
		
		List<String> list = Arrays.asList(veryBigQuery.toString().split(";"));
		return list;
	}

}
