package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class GenreCheckBox extends JPanel implements ActionListener {

	/**
	 * Panel holds table 4x4 size, which filled with 16 movie genres Class has
	 * setGenreId method to make selection of some genres.
	 */
	private static final long serialVersionUID = 1L;
	private final List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>(16);
	private List<Integer> genreIds;

	public GenreCheckBox(final String[] GENRES) {
		genreIds = new ArrayList<Integer>();
		for (String s : GENRES) {
			checkBoxes.add(new JCheckBox(s));
		}
		setBorder(BorderFactory.createTitledBorder("Movie genres:"));
		setLayout(new GridLayout(4, 4));
		for (JCheckBox jc : checkBoxes) {
			jc.setFont(new Font("Roman", Font.PLAIN, 9));
			jc.addActionListener(this);
			add(jc);
		}
	}

	public List<Integer> getGenreIds() {
		genreIds.clear();
		for (int i = 0; i < checkBoxes.size(); i++) {
			if (checkBoxes.get(i).isSelected())
				genreIds.add(i); 
		}
		return genreIds;
	}

	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
		for (int g : genreIds) {
			checkBoxes.get(g).setSelected(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		genreIds.clear();
//		JCheckBox jc = (JCheckBox) e.getSource();
//		if (jc.isSelected()) {
//			int i = 1 + checkBoxes.indexOf(jc);
//			genreIds.add(i);
//		}
	}

}
