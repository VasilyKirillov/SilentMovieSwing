package test;

import java.util.List;

public abstract class AbstractTest {

	public <T> void printer(List<T> ls) {
		for (T t : ls)
			System.out.println(t);
	}

}
