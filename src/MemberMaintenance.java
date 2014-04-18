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

	public MemberMaintenance() {
		buttonCount = 0;
		createAndShowGUI();

		JButton addMember = new JButton(new AbstractAction("Add a member") {
			public void actionPerformed(ActionEvent e) {
				// new window to add member
			}
		});
		printButton(addMember);

		JButton deleteMember = new JButton(new AbstractAction("Delete a member") {
			public void actionPerformed(ActionEvent e) {
				// new window to delete member
			}
		});
		printButton(deleteMember);
	}

	private void createAndShowGUI() {
		JFrame frame = new JFrame("Member Maintenance");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JMenuBar menu = new JMenuBar();

		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void printButton(JButton button) {
		buttonCount += 1;
		button.setBounds(424, 8 + (buttonCount * 50), 350, 40);
		add(button);
	}
}
