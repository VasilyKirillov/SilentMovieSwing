package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.listeners.TablePanelListener;

public class TablePanel extends JPanel {
	/**
	 * JTable show the movie records.
	 * It has menu, which became visible after right mouse is clicked,
	 * There is 3 items in it: view movie details, update movie data or delete movie records.
	 */
	private static final long serialVersionUID = 1L;

	private MovieTableModel tableModel;
	private JTable table;
	private JPopupMenu popup;
	private JMenuItem detailItem;
	private JMenuItem deleteItem;
	private JMenuItem updateItem;
	private TablePanelListener tableListener;

	public MovieTableModel getTableModel() {
		return tableModel;
	}

	public void setTableListener(TablePanelListener tableListener) {
		this.tableListener = tableListener;
	}

	public TablePanel() {

		// PopUp menu setup
		detailItem = new JMenuItem("details");
		updateItem = new JMenuItem("update");
		deleteItem = new JMenuItem("delete");
		MenuItemsListener menuListener = new MenuItemsListener();
		detailItem.addActionListener(menuListener);
		updateItem.addActionListener(menuListener);
		deleteItem.addActionListener(menuListener);
		popup = new JPopupMenu();
		popup.add(detailItem);
		popup.add(updateItem);
		popup.add(deleteItem);

		tableModel = new MovieTableModel();
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					int row = table.rowAtPoint(e.getPoint());
					table.getSelectionModel().setSelectionInterval(row, row);
					popup.show(table, e.getX(), e.getY());
				}
			}
		});

		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public List<String> getColumnInfo(int row) {
		List<String> colInfo = new ArrayList<String>(7);
		for (int column = 0; column < 7; column++) {
			colInfo.add(table.getValueAt(row, column).toString());
		}
		return colInfo;
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	private class MenuItemsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();

			if (detailItem.equals(e.getSource())) {
				if (tableListener != null)
					tableListener.rowDetails(getColumnInfo(row));
			}
			if (updateItem.equals(e.getSource())) {
				if (tableListener != null)
					tableListener.rowUpdated(tableModel.getMovies().get(row));
			}
			if (deleteItem.equals(e.getSource())) {
				if (tableListener != null)
					tableListener.rowDeleted(row);
			}
		}
	}

}


