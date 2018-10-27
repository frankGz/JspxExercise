package tests;

import model.Engine;
import model.StudentDAO;

public class Test {

	public static void main(String[] args) throws Exception {
		Engine engine = Engine.getInstance();
		System.out.println(engine.doPrime(5, 1));
		System.out.println(engine.doGps(10.0, 10.0, 20.0, 20.0));
		//System.out.println(engine.doRide("7440 BATHURST ST", "104-18643 52 Ave Surrey, Canada"));
		
		StudentDAO dao = new StudentDAO();
		dao.retrieve("Jack","1", "GPA");


	}

}
