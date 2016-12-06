package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Controller;
import model.Director;

public class DirectorPanel extends JPanel {

	/**
	 * 	This panel is part of LeftActionPanel of MainFrame of the application.
	 *  It consist of two parts: "Insert new film director"
	 *  and "Delete existing film director".
	 *  If director has at least 1 movie record in database, deleting won't happen. 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JTextField fstName;
	private JTextField midName;
	private JTextField lstName;
	private JTextArea about;
	private JButton insDirecBtn;
	private JComboBox<String> direcCombo;
	private JButton delDirecBtn;
	private DefaultComboBoxModel<String> direcModel;

	public DirectorPanel(DefaultComboBoxModel<String> dm) {
		direcModel = dm;
		controller = Controller.getInstance();
		fstName = new JTextField(15);
		midName = new JTextField(15);
		lstName = new JTextField(15);
		about = new JTextArea();
		about.setPreferredSize(new Dimension(260, 100));
		about.setBorder(BorderFactory.createTitledBorder("Brief info"));
		about.setLineWrap(true);
		about.setWrapStyleWord(true);
		insDirecBtn = new JButton("Insert");
		direcCombo = new JComboBox<String>(direcModel);
		delDirecBtn = new JButton("Delete");

		DirectorButtonsListener directorButtonsListener = new DirectorButtonsListener();
		insDirecBtn.addActionListener(directorButtonsListener);
		delDirecBtn.addActionListener(directorButtonsListener);

		GridBagConstraints c = new GridBagConstraints();
		JPanel insPanel = new JPanel();
		insPanel.setLayout(new GridBagLayout());
		insPanel.setBorder(BorderFactory.createTitledBorder("New director:"));
		insPanel.setPreferredSize(new Dimension(300, 250));
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		insPanel.add(new JLabel("Firstname:"), c);
		c.gridx = 1;
		insPanel.add(fstName, c);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		insPanel.add(new JLabel("Middle name:"), c);
		c.gridx = 1;
		insPanel.add(midName, c);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		insPanel.add(new JLabel("Lastname:"), c);
		c.gridx = 1;
		insPanel.add(lstName, c);

		c.gridwidth = 2;
		c.weighty = 2;
		c.gridx = 0;
		c.gridy++;
		insPanel.add(about, c);

		c.gridwidth = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		insPanel.add(insDirecBtn, c);

		JPanel delPanel = new JPanel();
		delPanel.setBorder(BorderFactory.createTitledBorder("Delete director:"));
		delPanel.setLayout(new GridBagLayout());
		delPanel.setPreferredSize(new Dimension(300, 100));

		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy++;
		delPanel.add(new JLabel("Choose to delete:"), c);
		c.gridx = 1;
		delPanel.add(direcCombo, c);

		c.weighty = 1.5;
		c.gridx = 0;
		c.gridy++;
		delPanel.add(delDirecBtn, c);

		setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		setPreferredSize(new Dimension(300, 460));
		add(insPanel);
		add(delPanel);

	}

	private class DirectorButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (insDirecBtn.equals((JButton) e.getSource())) {
				insertDierector();
			}

			if (delDirecBtn.equals((JButton) e.getSource())) {
				deleteDirector();
			}
		}

		public void insertDierector() {
			if (!fstName.getText().isEmpty() && !lstName.getText().isEmpty()) {
				Director d = new Director(fstName.getText(), midName.getText(), lstName.getText(), about.getText());
				controller.createDirector(d);
				direcModel.addElement(lstName.getText());
				JOptionPane.showMessageDialog(DirectorPanel.this, "New director inserted");
			} else {
				JOptionPane.showMessageDialog(DirectorPanel.this, "Fill the Firstname and Lastname, please", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		public void deleteDirector() {
			int index = direcCombo.getSelectedIndex();
			int ans = JOptionPane.showConfirmDialog(DirectorPanel.this, "Are you sure?", "Really",
					JOptionPane.OK_CANCEL_OPTION);
			if (ans == JOptionPane.OK_OPTION) {
				controller.deleteDirector(index);
				direcModel.removeElementAt(index);
			}

		}
	}

}
