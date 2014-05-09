import javax.swing.*;
import java.awt.*;

public class Donation {

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Donation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent donation = new ButtonMenu();
		JMenuBar menu = new JMenuBar();
		donation.setOpaque(true);
		frame.setContentPane(donation);

		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}