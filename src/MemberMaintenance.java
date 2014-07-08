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
	private int rsCount;
	private String data[][];
	private DefaultTableModel tableModel;
	private JTable table;
	private Hashtable<Integer, Integer> tableIndex = new Hashtable<Integer, Integer>();

	public MemberMaintenance() {
		buttonCount = 0;
		labelCount = 0;
		textFieldCount = 0;
		setLayout(null);
		labelArray = new ArrayList<JLabel>();
		textFieldArray = new ArrayList<JFormattedTextField>();

		initializeSQL();

		labelArray.add(new JLabel("ID:"));
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
			}
		});

		JButton saveMember = new JButton(new AbstractAction("Save Changes") {
			public void actionPerformed(ActionEvent e) {
				// save changes function
				// update exception: string is empty or null
				// reminder: complete the actual function because right now it only updates first name and last name
				String id = getTextData(0);
				String lastname = getTextData(1);
				String firstname = getTextData(2);
				String address = getTextData(3);
				String city = getTextData(4);
				String state = getTextData(5);
				String zip = getTextData(6);
				String homephone = getTextData(7);
				String cellphone = getTextData(8);
				String fax = getTextData(9);

				if (!(id.isEmpty())) {
					String query = "UPDATE Member SET Last_name = " + dbString(lastname) + ", First_name = " + dbString(firstname) + " WHERE ID = " + id + ";";
					int row = tableIndex.get(Integer.parseInt(id));
					table.getModel().setValueAt(lastname, row, 1);
					table.getModel().setValueAt(firstname, row, 2);
					executeCommand(query);
				}
			}
		});

		JButton addMember = new JButton(new AbstractAction("Add a member") {
			public void actionPerformed(ActionEvent e) {
				// add member functions
				String lastname = getTextData(1);
				String firstname = getTextData(2);
				String address = getTextData(3);
				String city = getTextData(4);
				String state = getTextData(5);
				String zip = getTextData(6);
				String homephone = getTextData(7);
				String cellphone = getTextData(8);
				String fax = getTextData(9);

				String query = "INSERT INTO Member (Last_name, First_name, Address, City, State, Zip, [Home Phone], [Cell Phone], Fax) VALUES (" + dbString(lastname) + "," + dbString(firstname) + "," + dbString(address) + "," + dbString(city) + "," + dbString(state) + "," + dbString(zip) + "," + dbString(homephone) + "," + dbString(cellphone) + "," + dbString(fax) + ")";

				initializeTextFields();
			}
		});

		JButton deleteMember = new JButton(new AbstractAction("Delete a member") {
			public void actionPerformed(ActionEvent e) {
				// reminder: prompt confirm message and run delete sql statement upon confirm
				String id = getTextData(0);

				if (!(id.isEmpty())) {
					String query = "DELETE FROM Member WHERE ID = " + id + ";";
					int row = tableIndex.get(Integer.parseInt(id));
					tableModel.removeRow(row);
					executeCommand(query);
					initializeTextFields();
				}
			}
		});

		printFormElements();
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

	// process + format phone numbers
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

	// returns text within the text fields according to their assigned position
	// exception: if field is blank, therefore string is null, return empty string
	private String getTextData(int pos) {
		String s = textFieldArray.get(pos).getText();
		if (s == null) { return ""; }
		return s;
	}
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	// set of methods to print elements to the frame
	/*	position dictionary:
	 *	0: id
	 *	1: last name
	 *	2: first name
	 *	3: address
	 *	4: city
	 *	5: state
	 *	6: zip
	 *	7: home phone
	 *	8: cell phone
	 *	9: fax#
	 */

	private void printFormElements() {
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
		label.setBounds(24, 40 + (labelCount * 30), 100, 20);
		add(label);
	}

	private void printTextField(JFormattedTextField textField) {
		textFieldCount += 1;
		textField.setBounds(104, 40 + (textFieldCount * 30), 150, 20);
		if (textFieldCount == 1) {
			textField.setEditable(false);
		}
		add(textField);
	}

	private void printMemberTable() {
		getTableData();
		String[] columnNames = {"ID",
														"Last Name",
														"First Name",
														"Address",
														"City",
														"State",
														"Zip",
														"Home Phone",
														"Cell Phone",
														"Fax",
														""};

		tableModel = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				// only need last column to be editable
				return column == 10;
			}
		};
		table = new JTable();
		table.setModel(tableModel);
		table.setRowHeight(35);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(5).setPreferredWidth(5);
		table.getColumnModel().getColumn(6).setPreferredWidth(5);
		table.getColumnModel().getColumn(10).setPreferredWidth(20);

		Action load = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable)e.getSource();
				int row = table.getSelectedRow();
				for (int i = 0; i < labelArray.size(); i++) {
					if (i == 7 || i == 8 || i == 9) {
						textFieldArray.get(i).setText(processPhoneNumber(data[row][i]));
					} else {
						textFieldArray.get(i).setText(data[row][i]);
					}
				}
			}
		};
		ButtonColumn buttonColumn = new ButtonColumn(table, load, 10);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 381, 1100, 444);
		add(scrollPane);
	}
	////////////////////////////////////////////////////////////////////////////////
	private <E> void log(E s) {
		System.out.println(s);
	}
	////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////
	// sql methods
	// set up the jdbc connection
	private void initializeSQL() {
		String url = "jdbc:odbc:donation";

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection(url, "myLogin", "myPassword");
			con.setAutoCommit(true);
		} catch (Exception e) {
			// later: display alert that sql could not be connected due to odbc
			e.printStackTrace();
		}
	}

	// method to execute update/delete/insert statements
	private void executeCommand(String query) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			// log(query);
			stmt.close();
			getTableData();
			// printMemberTable();
		}
		catch (SQLException e) {
			System.out.println("*** SQL error encountered ***");
			e.printStackTrace();
		}
	}

	// easier than wrapping every variable with single quotes i think
	private String dbString(String s) {
		return "'" + s + "'";
	}

	private void getTableData() {
		tableIndex.clear();
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM Member ORDER BY Last_name;";
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				rsCount++;
			}
			rs.first();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int row = 0;
		data = new String[rsCount][labelArray.size() + 1];
		try {
			while (rs.next()) {
				int id = rs.getInt("ID");
				tableIndex.put(id, row);
				data[row][0] = rs.getString("ID");
				data[row][1] = rs.getString("Last_name");
				data[row][2] = rs.getString("First_name");
				data[row][3] = rs.getString("Address");
				data[row][4] = rs.getString("City");
				data[row][5] = rs.getString("State");
				data[row][6] = rs.getString("Zip");
				data[row][7] = processPhoneNumber(rs.getString("Home Phone"));
				data[row][8] = processPhoneNumber(rs.getString("Cell Phone"));
				data[row][9] = processPhoneNumber(rs.getString("Fax"));
				data[row][10] = "Load";
				row++;
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////////////////////////
}
