

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonMenu extends JPanel implements ActionListener {
	private int buttonCount;

	public ButtonMenu() {
		buttonCount = 0;
		setLayout(null);
		printButton("Hello World!");
		printButton("Hello World2!");
		printButton("Hello World3!");
	}

	public void printButton(String text) {
		buttonCount += 1;
		JButton button = new JButton(text);
		button.addActionListener(this);
		button.setBounds(824, 8 + (buttonCount * 30), 150, 25);
		add(button);
	}

	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "" + buttonCount);
	}
}