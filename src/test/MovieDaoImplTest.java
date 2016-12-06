package test;

import java.util.Arrays;
import java.util.List;

import dao.MovieDaoImpl;
import model.Movie;

public class MovieDaoImplTest extends AbstractTest {

	public MovieDaoImplTest() {
		MovieDaoImpl movieDaoImpl = new MovieDaoImpl();
		List<Movie> dir;
		
		////////////////INSERT, UPDATE, DELETE//////////////////////////////////////
		
//		Movie m = new Movie(26, "Seven", 7, 777, 7.7f, "7", Arrays.asList(1,3,7) );
//		movieDaoImpl.createMovie(m);										//worked						
		
//		Movie m = new Movie(26,"Eight", 8, 8888, 8.8f, "8", Arrays.asList(2,4,8) );
//		movieDaoImpl.updateMovie(m);										//worked
		
//		movieDaoImpl.deleteMovie(26);										//worked

		
		////////////////SELECT//////////////////////////////////////////////////////
		
//		dir = movieDaoImpl.getList(); 										//worked
//		dir = movieDaoImpl.getMoviesByDirector("Chaplin"); 					//worked

//		dir = movieDaoImpl.getMoviesByGenre(5); 							//worked
//		dir = movieDaoImpl.getMoviesByYear(1928); 							//worked
		
//		//result printer
//		printer(dir);
		
//		System.out.println( movieDaoImpl.getMovieInfoById(13) );			//worked
		
	}


	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new MovieDaoImplTest();
		System.out.println((System.currentTimeMillis()-start)+" millis");
	}

}
