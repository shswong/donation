import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * MemberMaintenance will define member management options and functions
 *
 */

public class MemberMaintenance extends JPanel {
	private int buttonCount;
	private int labelCount;
	private int textFieldCount;

	public MemberMaintenance() {
		buttonCount = 0;
		labelCount = 0;
		textFieldCount = 0;
		setLayout(null);

		JLabel labelLastName = new JLabel("Last Name:");
		JLabel labelFirstName = new JLabel("First Name:");
		JLabel labelAddress = new JLabel("Address:");
		JLabel labelCity = new JLabel("City:");
		JLabel labelState = new JLabel("State:");
		JLabel labelZip = new JLabel("Zip:");
		JLabel labelHome = new JLabel("Home Phone:");
		JLabel labelCell = new JLabel("Cell Phone:");
		JLabel labelFax = new JLabel("Fax:");

		JFormattedTextField textLastName = new JFormattedTextField();
		JFormattedTextField textFirstName = new JFormattedTextField();
		JFormattedTextField textAddress = new JFormattedTextField();
		JFormattedTextField textCity = new JFormattedTextField();
		JFormattedTextField textState = new JFormattedTextField();
		JFormattedTextField textZip = new JFormattedTextField();
		JFormattedTextField textHome = new JFormattedTextField();
		JFormattedTextField textCell = new JFormattedTextField();
		JFormattedTextField textFax = new JFormattedTextField();

		JButton test = new JButton(new AbstractAction("Test") {
			public void actionPerformed(ActionEvent e) {
				// button to test input functions
			}
		});

		JButton saveMember = new JButton(new AbstractAction("Save Changes") {
			public void actionPerformed(ActionEvent e) {
				// save changes function
			}
		});

		JButton addMember = new JButton(new AbstractAction("Add a member") {
			public void actionPerformed(ActionEvent e) {
				// toggle frame to add member functions
			}
		});

		JButton deleteMember = new JButton(new AbstractAction("Delete a member") {
			public void actionPerformed(ActionEvent e) {
				// toggle frame to delete member functions
			}
		});

		printLabel(labelLastName);
		printLabel(labelLastName);
		printLabel(labelFirstName);
		printLabel(labelAddress);
		printLabel(labelCity);
		printLabel(labelState);
		printLabel(labelZip);
		printLabel(labelHome);
		printLabel(labelCell);
		printLabel(labelFax);

		printTextField(textLastName);
		printTextField(textLastName);
		printTextField(textFirstName);
		printTextField(textAddress);
		printTextField(textCity);
		printTextField(textState);
		printTextField(textZip);
		printTextField(textHome);
		printTextField(textCell);
		printTextField(textFax);

		printButton(test);
		printButton(saveMember);
		printButton(addMember);
		printButton(deleteMember);
	}

	private void printButton(JButton button) {
		buttonCount += 1;
		button.setBounds(624, 8 + (buttonCount * 50), 150, 40);
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
}
