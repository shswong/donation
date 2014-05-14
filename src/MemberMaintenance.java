import javax.swing.*;
import javax.swing.table.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;

/*
 * MemberMaintenance will define member management options and functions
 *
 */

public class MemberMaintenance extends JPanel {
	private int buttonCount;
	private int labelCount;
	private int textFieldCount;
	private ArrayList<JLabel> labelArray;
	private ArrayList<JFormattedTextField> textFieldArray;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private int rsCount;

	public MemberMaintenance() {
		buttonCount = 0;
		labelCount = 0;
		textFieldCount = 0;
		setLayout(null);
		labelArray = new ArrayList<JLabel>();
		textFieldArray = new ArrayList<JFormattedTextField>();

		initializeSQL();

		labelArray.add(new JLabel("Last Name:"));
		labelArray.add(new JLabel("First Name:"));
		labelArray.add(new JLabel("Address:"));
		labelArray.add(new JLabel("City:"));
		labelArray.add(new JLabel("State:"));
		labelArray.add(new JLabel("Zip:"));
		labelArray.add(new JLabel("Home Phone:"));
		labelArray.add(new JLabel("Cell Phone:"));
		labelArray.add(new JLabel("Fax:"));

		for (int counter = 0; counter < labelArray.size(); counter++) {
			textFieldArray.add(new JFormattedTextField());
		}

		JButton test = new JButton(new AbstractAction("Test") {
			public void actionPerformed(ActionEvent e) {
				// button to test input functions
				textFieldArray.get(6).setText(processPhoneNumber("abc456268519191"));

			}
		});

		JButton saveMember = new JButton(new AbstractAction("Save Changes") {
			public void actionPerformed(ActionEvent e) {
				// save changes function
				// probably run update sql statement
			}
		});

		JButton addMember = new JButton(new AbstractAction("Add a member") {
			public void actionPerformed(ActionEvent e) {
				// toggle frame to add member functions
				initializeTextFields();
			}
		});

		JButton deleteMember = new JButton(new AbstractAction("Delete a member") {
			public void actionPerformed(ActionEvent e) {
				// toggle frame to delete member functions
				// should prompt confirm message and run delete sql statement upon confirm
				initializeTextFields();
			}
		});

		printElements();
		printMemberTable();
		printButton(test);
		printButton(saveMember);
		printButton(addMember);
		printButton(deleteMember);
	}

	////////////////////////////////////////////////////////////////////////////////
	// set fields to blank
	private void initializeTextFields() {
		for (JFormattedTextField textField : textFieldArray) {
			textField.setText("");
		}
		textFieldArray.get(0).requestFocusInWindow();
	}

	// process phone numbers
	private String processPhoneNumber(String number) {
		// null string case
		if (number == null) { return ""; }
		// empty string case
		if (number.isEmpty()) { return ""; }
		// string less than 10 characters case
		if (number.length() < 10) { number = String.format("%0$10s", number).replace(" ", "0"); }

		String processedNumber = "";
		ArrayList<Character> characters = new ArrayList<Character>();

		// add only digits to characters array
		for (Character character : number.toCharArray()) {
			if (character.isDigit(character)) {
				characters.add(character);
			}
		}

		// check to allow only 10 digits
		while (characters.size() > 10) {
			characters.remove(0);
		}

		processedNumber += "(";
		for (int i = 0; i < 3; i++) {
			processedNumber += characters.get(i);
		}
		processedNumber += ") ";

		for (int i = 3; i < 6; i++) {
			processedNumber += characters.get(i);
		}
		processedNumber += "-";

		for (int i = 6; i < 10; i++) {
			processedNumber += characters.get(i);
		}

		return processedNumber;
	}
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	// set of methods to print elements to the frame
	/*	position dictionary:
	 *	0: last name
	 *	1: first name
	 *	2: address
	 *	3: city
	 *	4: state
	 *	5: zip
	 *	6: home phone
	 *	7: cell phone
	 *	8: fax#
	 */

	private void printElements() {
		for (JLabel label : labelArray) {
			printLabel(label);
		}

		for (JFormattedTextField textField : textFieldArray) {
			printTextField(textField);
		}
	}

	private void printButton(JButton button) {
		buttonCount += 1;
		button.setBounds(8 + ((buttonCount - 1) * 200), 8, 150, 40);
		add(button);
	}

	private void printLabel(JLabel label) {
		labelCount += 1;
		label.setBounds(24, 80 + (labelCount * 30), 100, 20);
		add(label);
	}

	private void printTextField(JFormattedTextField textField) {
		textFieldCount += 1;
		textField.setBounds(104, 80 + (textFieldCount * 30), 150, 20);
		add(textField);
	}

	private void printMemberTable() {
		int row = 0;
		Object[][] data = new Object[rsCount][labelArray.size() + 1];
		try {
			while (rs.next()) {
				int id = rs.getInt("ID");
				data[row][0] = rs.getString("Last_name");
				data[row][1] = rs.getString("First_name");
				data[row][2] = rs.getString("Address");
				data[row][3] = rs.getString("City");
				data[row][4] = rs.getString("State");
				data[row][5] = rs.getString("Zip");
				data[row][6] = rs.getString("Home Phone");
				data[row][7] = rs.getString("Cell Phone");
				data[row][8] = rs.getString("Fax");
				data[row][9] = "Load";
				row++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] columnNames = {"Last Name",
														"First Name",
														"Address",
														"City",
														"State",
														"Zip",
														"Home Phone",
														"Cell Phone",
														"Fax",
														""};

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				switch (column) {
					case 9:
						return true;
					default:
						return false;
				}
			}
		};
		JTable table = new JTable();
		table.setModel(tableModel);
		table.setRowHeight(35);

		Action load = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable)e.getSource();
				int row = table.getSelectedRow();
				for (int i = 0; i < labelArray.size(); i++) {
					if (i == 6 || i == 7 || i == 8) {
						textFieldArray.get(i).setText(processPhoneNumber((String) table.getModel().getValueAt(row, i)));
					} else {
						textFieldArray.get(i).setText((String) table.getModel().getValueAt(row, i));
					}
				}
			}
		};
		ButtonColumn buttonColumn = new ButtonColumn(table, load, 9);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 381, 1100, 444);
		add(scrollPane);
	}
	////////////////////////////////////////////////////////////////////////////////
	private void log(String s) {
		System.out.println(s);
	}
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	// sql methods
	private void initializeSQL() {
		String url = "jdbc:odbc:donation";
		String query = "SELECT * FROM Member ORDER BY Last_name";

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection(url, "myLogin", "myPassword");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				rsCount++;
			}
			rs.first();
		} catch (Exception e) {
			// later: display alert that sql could not be connected due to odbc
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////////////////////////
}
