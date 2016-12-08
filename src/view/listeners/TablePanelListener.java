package view.listeners;

import java.util.List;

import model.Movie;

public interface TablePanelListener {

	public void rowDeleted(int row);
	
	public void updateClicked(Movie m);
	
	public void detailsClicked(List<String> movieInfo);
	
	public void rowModified(Movie movie, String command);
}
