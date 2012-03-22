/**
 * 
 */
package main;

import java.io.IOException;

import util.QueryLoader;
import view.MainFrame;

/**
 * @author NGAL version 22.03.2012
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String args[]) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, IOException {
		 MainFrame tt = new MainFrame(QueryLoader.load());
		 tt.setVisible(true);
	}
}
