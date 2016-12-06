package view.listeners;

import java.util.List;

import model.Movie;

public interface TablePanelListener {

	public void rowDeleted(int row);
	
	public void rowUpdated(Movie m);
	
	public void rowDetails(List<String> movieInfo);
}
