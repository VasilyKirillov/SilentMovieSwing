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
	 * Panel holds table 4x4 size, which filled with 16 movie genres
	 * Class has setGenreId method to make selection of some genres.
	 */
	private static final long serialVersionUID = 1L;
	private final List<JCheckBox> CheckBoxes = new ArrayList<JCheckBox>(16);
	private List<Integer> genreIds;
	
	public GenreCheckBox(final String[] GENRES) {
		genreIds = new ArrayList<Integer>();
		for (String s : GENRES) {
			CheckBoxes.add(new JCheckBox(s));
		}
		setBorder(BorderFactory.createTitledBorder("Movie genres:"));
		setLayout(new GridLayout(4, 4));
		for (JCheckBox jc : CheckBoxes) {
			jc.setFont(new Font("Roman", Font.PLAIN, 9));
			jc.addActionListener(this);
			add(jc);
		}
	}

	public List<Integer> getGenreIds() {
		return genreIds;
	}
	public void setGenreIds( List<Integer> genreIds) {
		this.genreIds = genreIds;
		for (int g : genreIds){
			CheckBoxes.get(g).setSelected(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		genreIds.clear();
		JCheckBox jc = (JCheckBox) e.getSource();
		if(jc.isSelected()){
			int i = 1+CheckBoxes.indexOf(jc);
			genreIds.add(i);
		}
	}

}
