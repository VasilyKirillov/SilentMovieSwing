package view;

import javax.swing.SwingUtilities;

public class View {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new MainFrame().setVisible(true);
			}
		});
	}
}
