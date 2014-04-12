

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *	ButtonMenu creates JButtons that will each do their own purpose.
 */
public class ButtonMenu extends JPanel implements ActionListener {
	private int buttonCount;

	public ButtonMenu() {
		buttonCount = 0;
		setLayout(null);

		JButton memberMaintenance = new JButton(new AbstractAction("Member Maintenance") {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Member Maintenance");
				new JFrame("Member Maintenance").setVisible(true);
			}
		});
		printButton(memberMaintenance);

		JButton donationMaintenance = new JButton(new AbstractAction("Donation Type Maintenance") {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Donation Type Maintenance");
				new JFrame("Donation Type Maintenance").setVisible(true);
			}
		});
		printButton(donationMaintenance);

		JButton enterDonation = new JButton(new AbstractAction("Enter Donation") {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Enter Donation");
				new JFrame("Enter Donation").setVisible(true);
			}
		});
		printButton(enterDonation);

		JButton weeklyCheck = new JButton(new AbstractAction("Run Weekly Check Offering Report") {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Run Weekly Check Offering Report");
				new JFrame("Run Weekly Check Offering Report").setVisible(true);
			}
		});
		printButton(weeklyCheck);

		JButton weeklyCash = new JButton(new AbstractAction("Run Weekly Cash Offering Report") {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Run Weekly Cash Offering Report");
				new JFrame("Run Weekly Cash Offering Report").setVisible(true);
			}
		});
		printButton(weeklyCash);

		JButton donationByMember = new JButton(new AbstractAction("Run Donation By Member by Date Range Report") {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Run Donation By Member by Date Range Report");
				new JFrame("Run Donation By Member by Date Range Report").setVisible(true);
			}
		});
		printButton(donationByMember);
	}

	public void printButton(JButton button) {
		buttonCount += 1;
		button.setBounds(624, 8 + (buttonCount * 50), 350, 40);
		add(button);
	}

	public void actionPerformed(ActionEvent e) {

	}
}