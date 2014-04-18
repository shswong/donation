import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *	ButtonMenu creates JButtons that will each do their own purpose.
 *
 */

public class ButtonMenu extends JPanel {
	private int buttonCount;

	public ButtonMenu() {
		buttonCount = 0;
		setLayout(null);

		JButton memberMaintenance = new JButton(new AbstractAction("Member Maintenance") {
			public void actionPerformed(ActionEvent e) {
				MemberMaintenance manager = new MemberMaintenance();
			}
		});
		printButton(memberMaintenance);

		JButton donationMaintenance = new JButton(new AbstractAction("Donation Type Maintenance") {
			public void actionPerformed(ActionEvent e) {
				// new window to open donation maintenance
			}
		});
		printButton(donationMaintenance);

		JButton enterDonation = new JButton(new AbstractAction("Enter Donation") {
			public void actionPerformed(ActionEvent e) {
				// new window to enter donations
			}
		});
		printButton(enterDonation);

		JButton weeklyCheck = new JButton(new AbstractAction("Run Weekly Check Offering Report") {
			public void actionPerformed(ActionEvent e) {
				// new window to run a weekly check offering report
			}
		});
		printButton(weeklyCheck);

		JButton weeklyCash = new JButton(new AbstractAction("Run Weekly Cash Offering Report") {
			public void actionPerformed(ActionEvent e) {
				// new window to run weekly cash offering report
			}
		});
		printButton(weeklyCash);

		JButton donationByMember = new JButton(new AbstractAction("Run Donation By Member by Date Range Report") {
			public void actionPerformed(ActionEvent e) {
				// new window to run member donation by date report
			}
		});
		printButton(donationByMember);
	}

	public void printButton(JButton button) {
		buttonCount += 1;
		button.setBounds(624, 8 + (buttonCount * 50), 350, 40);
		add(button);
	}
}