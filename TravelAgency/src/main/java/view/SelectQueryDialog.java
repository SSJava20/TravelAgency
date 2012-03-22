/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;

/**
 * @author NGAL version 22.03.2012
 */
public class SelectQueryDialog extends JDialog {
	List<String> queries;
	JRadioButton buttons[];
	JTextArea textArea;
	MainFrame frame;
	private final JPanel contentPanel = new JPanel();

	// private String show() {
	//
	// }
	/**
	 * Create the dialog.
	 */
	public SelectQueryDialog(final MainFrame frame, List<String> queries) {
		setBounds(100, 100, 774, 234);
		this.frame = frame;
		this.queries = queries;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			textArea = new JTextArea();
			textArea.setBackground(Color.LIGHT_GRAY);
			textArea.setColumns(10);
			contentPanel.add(textArea, BorderLayout.NORTH);
		}

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ButtonGroup group = new ButtonGroup();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		buttons = new JRadioButton[queries.size()];
		for (int i = 0; i < queries.size(); i++) {
			JRadioButton RadioButton = new JRadioButton(String.valueOf(i));
			RadioButton.addActionListener(new radioActionListener());
			group.add(RadioButton);
			panel.add(RadioButton);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.queryField.setText("");
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	class radioActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			int index = Integer.parseInt(((JRadioButton) e.getSource())
					.getText());
			textArea.setText(queries.get(index));
			frame.queryField.setText(queries.get(index));
		}
	}
}
