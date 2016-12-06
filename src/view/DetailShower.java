package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DetailShower extends JFrame {

	/**
	 * Frame which display Movie Details
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel title, director, genres, release, rating;
	private JTextArea about;
	private JButton ok;

	public DetailShower(List<String> movieInfo) {
		super("Movie: " + movieInfo.get(1));

		title = new JLabel(movieInfo.get(1));
		director = new JLabel(movieInfo.get(2));
		genres = new JLabel(movieInfo.get(3));
		release = new JLabel(movieInfo.get(4));
		rating = new JLabel(movieInfo.get(5));
		setLabelFont(title, director, genres, release, rating);
		about = new JTextArea(movieInfo.get(6));
		about.setLineWrap(true);
		about.setWrapStyleWord(true);
		about.setBorder(BorderFactory.createTitledBorder("Description:"));
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});		
		

		// Main Panel layout and border setup
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Details"));

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Original title:"), c);
		c.gridx = 1;
		panel.add(title, c);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		panel.add(new JLabel("Directed by:"), c);
		c.gridx = 1;
		panel.add(director, c);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		panel.add(new JLabel("genres:"), c);
		c.gridx = 1;
		panel.add(genres, c);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		panel.add(new JLabel("Release year:"), c);
		c.gridx = 1;
		panel.add(release, c);

		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		panel.add(new JLabel("IMDB's rating:"), c);
		c.gridx = 1;
		panel.add(rating, c);

		c.gridwidth = 2;
		c.weighty = 2;
		c.gridx = 0;
		c.gridy++;
		panel.add(new JScrollPane(about), c);
		
		c.gridwidth = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy++;
		panel.add(ok, c);
		
		// Frame setup
		setMinimumSize(new Dimension(300, 450));
		setPreferredSize(new Dimension(300, 450));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		add(panel);
	}

	private void setLabelFont(JLabel... labels) {
		Font font = new Font("Serif", Font.PLAIN, 14);
		for (JLabel lb : labels)
			lb.setFont(font);
	}

}
