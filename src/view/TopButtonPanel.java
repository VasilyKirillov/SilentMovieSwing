package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import view.listeners.TopPanelListener;

public class TopButtonPanel extends JPanel {

	/**
	 * Button control set.
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton crtMvBtn;
	private JButton rdMvBtn;
	private JButton direcBtn;
	private JButton hideBtn;
	private TopPanelListener topListener;
	
	public void setTopListener(TopPanelListener topListener) {
		this.topListener = topListener;
	}

	public TopButtonPanel() {

		rdMvBtn = new JButton("Movies");
		crtMvBtn = new JButton("Insert movie");
		direcBtn = new JButton("Directors");
		hideBtn =  new JButton("Hide");
		
		ButtonListener buttonListener = new ButtonListener();
		rdMvBtn.addActionListener(buttonListener);
		crtMvBtn.addActionListener(buttonListener);
		direcBtn.addActionListener(buttonListener);
		hideBtn.addActionListener(buttonListener);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(rdMvBtn);
		add(crtMvBtn);
		add(new JSeparator());
		add(direcBtn);
		add(new JSeparator());
		add(hideBtn);

	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if ( hideBtn.equals((JButton)e.getSource()) ){
				if (topListener != null) topListener.hidePressed();
			}
			if ( rdMvBtn.equals((JButton)e.getSource()) ){
				if (topListener != null) topListener.readPressed();
			}
			if ( crtMvBtn.equals((JButton)e.getSource()) ){
				if (topListener != null) topListener.createPressed();
			}
			if ( direcBtn.equals((JButton)e.getSource()) ){
				if (topListener != null) topListener.direcPressed();
			}
		}
	}



}
