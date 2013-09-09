package ru.mephi.education.clazz.inner;

public class Starter {

	public static void main(String[] args) {
		Container.incrementStatic();
		Incrementor inc = new Container.Incrementor2();
		inc.increment();
		inc.print();
		Container container = new Container();
		Container container2 = new Container();
		container.increment();
		inc = container.getIncrementor();
		inc.increment();
		inc.print();
		inc = container2.getIncrementor();
		inc.increment();
		inc.print();
	}
}
