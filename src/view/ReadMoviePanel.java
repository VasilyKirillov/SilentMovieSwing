package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controller.Controller;
import view.listeners.ReadPanelListener;

public class ReadMoviePanel extends JPanel {

	/**
	 * Panel consist of different options, which help to display list of movies. 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private JRadioButton allMsRadioBtn;
	private JRadioButton direcRadioBtn;
	private JRadioButton genreRadioBtn;
	private JRadioButton yearsRadioBtn;
	private ButtonGroup buttonGroup;
	private JComboBox<String> direcCombo;
	private JComboBox<String> genreCombo;
	private JSpinner yearSpinner;
	private JButton showBtn;
	private ReadPanelListener readPanelListener;

	public void setReadPanelListener(ReadPanelListener readPanelListener) {
		this.readPanelListener = readPanelListener;
	}

	public ReadMoviePanel(DefaultComboBoxModel<String> genreModel, DefaultComboBoxModel<String> direcModel, SpinnerNumberModel ynm) {
		controller = Controller.getInstance();

		// setting radioButtonGroup
		RadioListener rlistener = new RadioListener();
		allMsRadioBtn = new JRadioButton("All movies:");
		allMsRadioBtn.setSelected(true);
		allMsRadioBtn.addActionListener(rlistener);
		direcRadioBtn = new JRadioButton("Movies derected by:");
		direcRadioBtn.addActionListener(rlistener);
		genreRadioBtn = new JRadioButton("Movies which genre are:");
		genreRadioBtn.addActionListener(rlistener);
		yearsRadioBtn = new JRadioButton("Movies released in:");
		yearsRadioBtn.addActionListener(rlistener);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(allMsRadioBtn);
		buttonGroup.add(direcRadioBtn);
		buttonGroup.add(genreRadioBtn);
		buttonGroup.add(yearsRadioBtn);

		// JComboBox & JSpinner setup
		direcCombo = new JComboBox<String>(direcModel);
		genreCombo = new JComboBox<String>(genreModel);
		yearSpinner = new JSpinner(ynm);
		direcCombo.setEnabled(false);
		genreCombo.setEnabled(false);
		yearSpinner.setEnabled(false);

		// Button
		showBtn = new JButton("Show");
		showBtn.addActionListener(new ShowButtonListener());

		// Layout
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 405));
		panel.setBorder(BorderFactory.createTitledBorder("Read from database:"));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);

		c.gridwidth = 2;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(allMsRadioBtn, c);

		c.gridwidth = 1;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy++;
		panel.add(direcRadioBtn, c);
		c.gridx = 1;
		panel.add(direcCombo, c);

		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy++;
		panel.add(genreRadioBtn, c);
		c.gridx = 1;
		panel.add(genreCombo, c);

		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy++;
		panel.add(yearsRadioBtn, c);
		c.gridx = 1;
		panel.add(yearSpinner, c);

		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy++;
		panel.add(showBtn, c);

		c.weighty = 5;
		c.gridx = 0;
		c.gridy++;
		panel.add(new JLabel(), c);

		setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		setPreferredSize(new Dimension(300, 420));
		add(panel);
	}

	private class RadioListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			direcCombo.setEnabled(direcRadioBtn.isSelected());
			genreCombo.setEnabled(genreRadioBtn.isSelected());
			yearSpinner.setEnabled(yearsRadioBtn.isSelected());
		}
	}

	private class ShowButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (direcRadioBtn.isSelected()) {
				String lastName = direcCombo.getSelectedItem().toString();
				controller.getMovieByDirector(lastName);
			}
			if (genreRadioBtn.isSelected()) {
				int id = genreIdByName(controller.getGenres());
				controller.getMovieByGenre(id);
			}
			if (yearsRadioBtn.isSelected()) {
				int year = (int) yearSpinner.getValue();
				controller.getMovieByYear(year);
			} 
			if (allMsRadioBtn.isSelected()) {
				controller.getMovieList();
			}
			if (readPanelListener != null) {
				readPanelListener.InfoRequested();
			}

		}
	}
	
	private int genreIdByName(String[] g){
		int id = 0;
		for(int i=0; i<g.length; i++)
			if (g[i].equals( genreCombo.getSelectedItem().toString() ) )
				id = i+1;
		return id;
				
	}

}
