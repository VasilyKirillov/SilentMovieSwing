package test;

import java.util.Arrays;
import java.util.List;

import controller.Controller;
import model.Director;
import model.Movie;

public class ControllerTest extends AbstractTest {
	Controller controller;

	public ControllerTest() {
		controller = Controller.getInstance();
		List list;
		
		Movie m = new Movie(26, "Seven", 7, 777, 7.7f, "7", Arrays.asList(1,3,7) );
		Director dr = new Director("Gorge", "", "Lucas", "creator of StarWars");
		
// MOVIE
//////////////////////////////UPDATE, INSERT, DELETE////////////////////////////////////
		
//		controller.createMovie(m);												//worked						
		
//		m = new Movie(26,"Eight", 8, 8888, 8.8f, "8", Arrays.asList(2,4,8) );
//		controller.updateMovie(m);												//worked
		
//		controller.deleteMovie(26);												//worked

//////////////////////////////SELECT////////////////////////////////////////////////////
		
//		list = controller.getMovieList(); 										//worked
//		list = controller.getMovieByDirector("Murnau"); 						//worked

//		list = controller.getMovieByGenre(2); 									//worked
//		list = controller.getMovieByYear(1916); 								//worked

//		printer(list);
		
//		System.out.println( controller.getMovie(22) );							//worked

		
//DIRECTOR
////////////////////////////////////INSERT, DELETE//////////////////////////////////////
		
//		controller.createDirector(dr);											//worked
		
//		controller.deleteDirector(14);											//worked
		
//		list = controller.getDirectorList();									//worked
		
//		printer(list);
		
//		for(String s:controller.getLastNames()) System.out.print(s+" ");		//worked
		

//GENRES
//		for(String s:controller.getGenres()) System.out.print(s+" ");		//worked
		
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new ControllerTest();
		System.out.println("\n"+(System.currentTimeMillis()-start)+" millis");
	}
}
