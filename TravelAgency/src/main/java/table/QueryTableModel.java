package table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

// QueryTableModel.java
// A basic implementation of the TableModel interface that fills out a Vector of
// String[] structures from a query's result set.
//

public class QueryTableModel extends AbstractTableModel {

	public String lastErrorMessage;

	Vector cache; // will hold String[] objects . . .

	int colCount;

	String[] headers;

	Connection db;

	Statement statement;

	String currentURL = "jdbc:mysql://localhost/person_db";
	String user = "root";
	String pass = "";

	public QueryTableModel() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		cache = new Vector();
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	}

	public String getColumnName(int i) {
		return headers[i];
	}

	public int getColumnCount() {
		return colCount;
	}

	public int getRowCount() {
		return cache.size();
	}

	public Object getValueAt(int row, int col) {
		return ((String[]) cache.elementAt(row))[col];
	}

	// All the real work happens here; in a real application,
	// we'd probably perform the query in a separate thread.
	public boolean setQuery(String q) {
		cache = new Vector();
		try {
			// Execute the query and store the result set and its metadata
			if (statement.execute(q)) {
				ResultSet rs = statement.getResultSet();
				ResultSetMetaData meta = rs.getMetaData();
				colCount = meta.getColumnCount();

				// Now we must rebuild the headers array with the new column
				// names
				headers = new String[colCount];
				for (int h = 1; h <= colCount; h++) {
					headers[h - 1] = meta.getColumnName(h);
				}

				// and file the cache with the records from our query. This
				// would
				// not be
				// practical if we were expecting a few million records in
				// response
				// to our
				// query, but we aren't, so we can do this.
				while (rs.next()) {
					String[] record = new String[colCount];
					for (int i = 0; i < colCount; i++) {
						record[i] = rs.getString(i + 1);
					}
					cache.addElement(record);
				}
				fireTableChanged(null); // notify everyone that we have a new
										// table.
				return true;
			} else {
				lastErrorMessage = "You have updated "
						+ statement.getUpdateCount() + " rows";
			}

		} catch (Exception e) {
			cache = new Vector(); // blank it out and keep going.
			e.printStackTrace();
			lastErrorMessage = e.getMessage();
		}
		return false;
	}

	public boolean initDB() {
		try {
			db = DriverManager.getConnection(currentURL, user, pass);
			statement = db.createStatement();
			return true;
		} catch (Exception e) {
			System.out.println("Could not initialize the database.");
			e.printStackTrace();
		}
		return false;
	}

	public boolean closeDB() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (db != null) {
				db.close();
			}
			return true;
		} catch (Exception e) {
			System.out.println("Could not close the current connection.");
			e.printStackTrace();
		}
		return false;
	}
}