

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonMenu extends JPanel implements ActionListener {
	private int buttonCount;

	public ButtonMenu() {
		buttonCount = 0;
		setLayout(null);
		printButton("Donations");
		printButton("Member List");
		printButton("Hello World3!");
	}

	public void printButton(final String text) {
		buttonCount += 1;
		JButton button = new JButton(new AbstractAction(text) {
			public void actionPerformed(ActionEvent e) {
				String window = text;
				JOptionPane.showMessageDialog(null, text);
				new JFrame(text).setVisible(true);
			}
		});
		button.addActionListener(this);
		button.setBounds(824, 8 + (buttonCount * 30), 150, 25);
		add(button);
	}

	public void actionPerformed(ActionEvent e) {

	}
}