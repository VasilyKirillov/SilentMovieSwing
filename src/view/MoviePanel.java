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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.Controller;
import model.Movie;
import view.listeners.TablePanelListener;

public class MoviePanel extends JPanel {

	/**
	 * This panel used both for insert and for update one movie record in the database.
	 * String "command" responsible for insert or update behavior;
	 * Method dataForUpdate fills components with data.
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JTextField title;
	private JComboBox<String> direcCombo;
	private JSpinner yearSpinner;
	private GenreCheckBox genreCheck;
	private JSpinner ratingSpinner;
	private JTextArea about;
	private JButton insertBtn;
	private Movie movie;
	private String command;
	private TablePanelListener tableListener;
	
	public void setTableListener(TablePanelListener tableListener){
		this.tableListener = tableListener;
	}

	public void setMovie(Movie m) {
		movie = m;
		dataForUpdate();
	}

	public MoviePanel(DefaultComboBoxModel<String> direcModel, SpinnerNumberModel ynm, SpinnerNumberModel rnm,
			String command) {

		controller = Controller.getInstance();
		this.command = command;
		movie = new Movie();
		title = new JTextField(15);
		title.setToolTipText("Movie original title");
		direcCombo = new JComboBox<String>(direcModel);
		yearSpinner = new JSpinner(ynm);
		ratingSpinner = new JSpinner(rnm);
		genreCheck = new GenreCheckBox(controller.getGenres());
		about = new JTextArea();
		about.setBorder(BorderFactory.createTitledBorder("Movie description:"));
		about.setLineWrap(true);
		about.setWrapStyleWord(true);
		insertBtn = new JButton(command);
		insertBtn.addActionListener(new InsertButtonListener());

		// add all components to layout
		GridBagConstraints c = new GridBagConstraints();
		JPanel insPanel = new JPanel();
		insPanel.setLayout(new GridBagLayout());
		insPanel.setBorder(BorderFactory.createTitledBorder(command + " movie:"));
		insPanel.setPreferredSize(new Dimension(300, 405));
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		insPanel.add(new JLabel("Movie original title:"), c);
		c.gridx = 1;
		insPanel.add(title, c);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		insPanel.add(new JLabel("Movie directed by:"), c);
		c.gridx = 1;
		insPanel.add(direcCombo, c);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		insPanel.add(new JLabel("Released in:"), c);
		c.gridx = 1;
		insPanel.add(yearSpinner, c);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		insPanel.add(new JLabel("IMDB's rating:"), c);
		c.gridx = 1;
		insPanel.add(ratingSpinner, c);

		c.gridwidth = 2;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		insPanel.add(genreCheck, c);

		c.gridwidth = 2;
		c.weighty = 1;
		c.gridy++;
		c.gridx = 0;
		insPanel.add(new JScrollPane(about), c);

		c.gridwidth = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		insPanel.add(insertBtn, c);

		setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		setPreferredSize(new Dimension(300, 460));
		add(insPanel);

	}

	private class InsertButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			movie.setTitle(title.getText());
			movie.setDirectorId(direcCombo.getSelectedIndex());
			movie.setReleaseYear((int) yearSpinner.getValue());
			movie.setRating( (double) ratingSpinner.getValue() );
			movie.setAbout(about.getText());
			movie.setGenreIds(genreCheck.getGenreIds());
			if(tableListener != null) {
				tableListener.rowModified(movie, command);
			}
		}
	}

	public void dataForUpdate() {
		title.setText(movie.getTitle());
		direcCombo.setSelectedIndex(movie.getDirectorId() );
		yearSpinner.setValue(movie.getReleaseYear());
		genreCheck.setGenreIds(movie.getGenreIds());
		ratingSpinner.setValue(movie.getRating());
		about.setText(movie.getAbout());
	}

}
