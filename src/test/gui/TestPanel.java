package test.gui;

import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.MoviePanel;
import view.DetailShower;
import view.DirectorPanel;
import view.LeftActionPanel;
import view.ReadMoviePanel;
import view.TablePanel;
import view.TopButtonPanel;

public class TestPanel extends JFrame{
	
	DirectorPanel directorPanel;
	ReadMoviePanel readMoviePanel;
	MoviePanel createPanel;
	LeftActionPanel leftPanel;
	TopButtonPanel topPanel;
	TablePanel tablePanel;
	DetailShower detailShower;
	
	public TestPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
///////////////////LEFT PANEL//////////////////////////////////////////////		
//		directorPanel = new DirectorPanel();		
//		add(directorPanel);
//		
//		readMoviePanel = new ReadMoviePanel();
//		add(readMoviePanel);
		
//		leftPanel = new LeftActionPanel();
//		add(leftPanel);
		
//		createPanel = new CreateMoviePanel();
//		add(createPanel);
		
//		setSize(350, 500);
		
//////////////////////TOP PANEL////////////////////////////////////////////		
//		topPanel = new TopButtonPanel();
//		add(topPanel);
		
//		setSize(500, 100);

//////////////////////TABLE PANEL//////////////////////////////////////////
//		tablePanel = new TablePanel();
//		add(tablePanel);

//		setSize(500,500);
		
///////////////////////////////////////////////////////////////////////////
		List<String> list = Arrays.asList("1","Titanic","James Cameron","[Drama, History]","2000","9.0","Ice vs Love Story");
		detailShower = new DetailShower(list);
		detailShower.setVisible(true); 
		
//		setSize(250,500);
		
		setDefaultLookAndFeelDecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TestPanel();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TestPanel().setVisible(true);
				;
			}
		});
	}
}
