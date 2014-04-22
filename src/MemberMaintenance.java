import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	public MemberMaintenance() {
		buttonCount = 0;
		labelCount = 0;
		textFieldCount = 0;
		setLayout(null);
		labelArray = new ArrayList<JLabel>();
		textFieldArray = new ArrayList<JFormattedTextField>();

		labelArray.add(new JLabel("Last Name:"));
		labelArray.add(new JLabel("First Name:"));
		labelArray.add(new JLabel("Address:"));
		labelArray.add(new JLabel("City:"));
		labelArray.add(new JLabel("State:"));
		labelArray.add(new JLabel("Zip:"));
		labelArray.add(new JLabel("Home Phone:"));
		labelArray.add(new JLabel("Cell Phone:"));
		labelArray.add(new JLabel("Fax:"));

		for (int counter = 0; counter < 9; counter++) {
			textFieldArray.add(new JFormattedTextField());
		}

		JButton test = new JButton(new AbstractAction("Test") {
			public void actionPerformed(ActionEvent e) {
				// button to test input functions
				initializeTextFields();
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
		button.setBounds(624, 308 + (buttonCount * 50), 150, 40);
		add(button);
	}

	private void printLabel(JLabel label) {
		labelCount += 1;
		label.setBounds(24, 8 + (labelCount * 30), 100, 20);
		add(label);
	}

	private void printTextField(JFormattedTextField textField) {
		textFieldCount += 1;
		textField.setBounds(104, 8 + (textFieldCount * 30), 150, 20);
		add(textField);
	}
	////////////////////////////////////////////////////////////////////////////////
}
