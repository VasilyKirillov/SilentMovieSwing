package test;

import java.util.List;

import dao.DirectorDaoImpl;
import model.Director;

public class DirectorDaoImplTest extends AbstractTest {

	private DirectorDaoImpl dirDaoImpl;

	public DirectorDaoImplTest() {
		dirDaoImpl = new DirectorDaoImpl();
		Director dr = new Director("Gorge", "", "Lucas", "creator of StarWars");
		List<Director> list;

		// dirDaoImpl.createDirector(dr); 		//worked

		// dirDaoImpl.deleteDirector(14);		//worked

		// list = dirDaoImpl.getList(); 		//worked

		// printer(list);

	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new DirectorDaoImplTest();
		System.out.println((System.currentTimeMillis() - start) + " millis");
	}

}
