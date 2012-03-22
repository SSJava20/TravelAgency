package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import table.QueryTableModel;
import javax.swing.JList;

public class MainFrame extends JFrame {

	MainFrame frame = this;
	List<String> queries = new ArrayList<String>();

	JTextField hostField;

	JTextField queryField;

	QueryTableModel qtm;
	private final JButton btnLoadQuery = new JButton("LoadQuery");

	public MainFrame(final List<String> queriesList) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		super("Database Test Frame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 400);
		if (queriesList != null) {
			queries = queriesList;
		}
		qtm = new QueryTableModel();
		JTable table = new JTable(qtm);
		JScrollPane scrollpane = new JScrollPane(table);
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(4, 2));
		p1.add(new JLabel("Enter your query: "));
		p1.add(queryField = new JTextField());
		p1.add(new JLabel("Send request: "));

		final JLabel lblStatus = new JLabel("Not connected");

		final JLabel lblError = new JLabel("No errors");

		JButton jbSend = new JButton("Send");
		jbSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!qtm.setQuery(queryField.getText().trim())) {
					lblStatus.setText("Some error");
					lblError.setText(qtm.lastErrorMessage);
				}
			}
		});

		JButton jbConnect = new JButton("Connect");
		jbConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (qtm.initDB()) {
					lblStatus.setText("Connected");
				}
			}
		});

		p1.add(jbSend);
		p1.add(lblStatus);
		p1.add(jbConnect);
		getContentPane().add(lblError, BorderLayout.SOUTH);
		getContentPane().add(p1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		p1.add(lblNewLabel);
		btnLoadQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectQueryDialog dialog = new SelectQueryDialog(frame, queries);
				dialog.setVisible(true);
			}
		});
		p1.add(btnLoadQuery);

		getContentPane().add(scrollpane, BorderLayout.CENTER);
	}
}