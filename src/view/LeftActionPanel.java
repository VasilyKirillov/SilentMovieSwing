package view;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;

import controller.Controller;

public class LeftActionPanel extends JPanel {

	/**
	 * This is common panel for different actions representing on inner panels.
	 */
	private static final long serialVersionUID = 1L;
	public static final int READ_PANEL = 0;
	public static final int CREATE_PANEL = 1;
	public static final int UPDATE_PANEL = 2;
	public static final int DIRECTOR_PANEL = 3;

	// inner panels
	private ReadMoviePanel rdMvPanel;
	private MoviePanel crtMvPanel;
	private MoviePanel updtMvPanel;
	private DirectorPanel direcPanel;

	private Controller controller;
	private DefaultComboBoxModel<String> direcModel;
	private DefaultComboBoxModel<String> genreModel;
	private SpinnerNumberModel ynm;
	private SpinnerNumberModel rnm;

	public ReadMoviePanel getRdMvPanel() {
		return rdMvPanel;
	}
	public MoviePanel getCrtPanel() {
		return crtMvPanel;
	}
	public MoviePanel getUpdtPanel() {
		return updtMvPanel;
	}

	public LeftActionPanel() {
		controller = Controller.getInstance();

		genreModel = new DefaultComboBoxModel<String>(controller.getGenres());
		direcModel = new DefaultComboBoxModel<String>(controller.getLastNames());
		ynm = new SpinnerNumberModel(1917, 1896, 1932, 1);
		rnm = new SpinnerNumberModel(6, 1, 9.9, 0.1);

		rdMvPanel = new ReadMoviePanel(genreModel, direcModel, ynm);
		crtMvPanel = new MoviePanel(direcModel, ynm, rnm, "Insert");
		updtMvPanel = new MoviePanel(direcModel, ynm, rnm, "Update");
		direcPanel = new DirectorPanel(direcModel);
		panelChanger(0);

		setMinimumSize(new Dimension(320, 500));
		setPreferredSize(new Dimension(320, 500));
		add(rdMvPanel);
		add(crtMvPanel);
		add(direcPanel);
		add(updtMvPanel);
	}

	// method makes visible one of the inner panels.
	public void panelChanger(int n) {
		panelChanger(n, rdMvPanel, crtMvPanel, updtMvPanel, direcPanel);
	}

	private void panelChanger(int n, JPanel... panels) {
		for (JPanel p : panels)
			p.setVisible(false);
		panels[n].setVisible(true);
	}

}
