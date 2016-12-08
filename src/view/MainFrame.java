/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import controller.Controller;
import model.Movie;
import view.listeners.ReadPanelListener;
import view.listeners.TablePanelListener;
import view.listeners.TopPanelListener;

/**
 * @author Kirillov Vasily
 *
 */
public class MainFrame extends JFrame {

	/**
	 * Class represents the main window of the application. It Consist of three
	 * main parts: TopButtonPanel, LeftActionPanel and the expandable TablePanel
	 * on the right. TopButtonPanel holds the buttons, each of which activate
	 * the some of the action panel from the left. LeftActionPanel holds the
	 * following panels: ReadMoviesPanel, InsertMoviePanel, UpdateMoviePanel and
	 * DirectorPanel, which contain both insert and delete options. TablePanel
	 * need to display query results from database. Also this class provide
	 * connection between separate panels, using setListener method.
	 */

	private static final long serialVersionUID = 1L;

	private TablePanel tablePanel;
	private TopButtonPanel topPanel;
	private LeftActionPanel leftPanel;
	private Controller controller;

	public MainFrame() {
		TablePanelListener tableListener = new TableListener();

				tablePanel = new TablePanel();
		topPanel = new TopButtonPanel();
		leftPanel = new LeftActionPanel();
		controller = Controller.getInstance();
		topPanel.setTopListener(new TopListener());
		tablePanel.setTableListener(tableListener);
		leftPanel.getUpdtPanel().setTableListener(tableListener);
		leftPanel.getCrtPanel().setTableListener(tableListener);
		leftPanel.getRdMvPanel().setReadPanelListener(new ReadPanelListener() {
			public void InfoRequested() {
				tablePanel.getTableModel().setMovies(controller.getMovieReference());
				tablePanel.getTableModel().setDirectors(controller.getDirectorReference());
				tablePanel.refresh();
			}
		});
		

		// Setting up Layout, size and location of main window
		setLayout(new BorderLayout());
		add(topPanel, BorderLayout.PAGE_START);
		add(leftPanel, BorderLayout.LINE_START);
		add(new JScrollPane(tablePanel), BorderLayout.CENTER);

		setDefaultLookAndFeelDecorated(true);
		setSize(800, 600);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainFrame.this.dispose();
				System.gc();
			}
		});		
	}

	// Setting actions for the top panel's buttons
	private class TopListener implements TopPanelListener {
		public void hidePressed() {
			if (leftPanel.isVisible())
				leftPanel.setVisible(false);
			else
				leftPanel.setVisible(true);
		}
		public void readPressed() {
			leftPanel.panelChanger(LeftActionPanel.READ_PANEL);
		}
		public void createPressed() {
			leftPanel.panelChanger(LeftActionPanel.CREATE_PANEL);
		}
		public void direcPressed() {
			leftPanel.panelChanger(LeftActionPanel.DIRECTOR_PANEL);
		}
	}

	private class TableListener implements TablePanelListener {
		public void rowDeleted(int row) {
			System.out.println("====MF======row selected "+row);
			int ans = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure?", "Really",
					JOptionPane.OK_CANCEL_OPTION);
			if (ans == JOptionPane.OK_OPTION) {
				controller.deleteMovie(row);
				controller.getMovieReference().remove(row);
				tablePanel.getTableModel().setMovies(controller.getMovieReference());
				tablePanel.refresh();
			}
		}

		public void updateClicked(Movie m) {
			leftPanel.getUpdtPanel().setMovie(m);
			leftPanel.panelChanger(LeftActionPanel.UPDATE_PANEL);
		}

		public void detailsClicked(List<String> movieInfo) {
			new DetailShower(movieInfo).setVisible(true);
		}
		
		public void rowModified(Movie movie, String command){
			if ("Insert".equals(command)) {
				controller.createMovie(movie);
			} else if ("Update".equals(command)) {
				controller.updateMovie(movie); 
			}
			JOptionPane.showMessageDialog(MainFrame.this, "Movie "+command+"ed.");
			tablePanel.getTableModel().setMovies(controller.getMovieReference());
			tablePanel.refresh();
		}
	}

}
