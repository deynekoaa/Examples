package ru.mephi.education.clazz.inner;

public class Container {

	private static final Integer increment = 3;

	private static Integer staticValue = 0;

	private Integer value = 10;

	private static class Incrementor1 implements Incrementor {

		@Override
		public void increment() {
			staticValue += increment;
		}

		@Override
		public void print() {
			System.out.println("After Private Static Incrementor:"
					+ staticValue.toString());
		}

	}

	public static class Incrementor2 implements Incrementor {

		@Override
		public void increment() {
			staticValue += increment;
		}

		@Override
		public void print() {
			System.out.println("After public Static Incrementor:"
					+ staticValue.toString());
		}

	}

	private class Incrementor3 implements Incrementor {

		@Override
		public void increment() {
			value += increment;
		}

		@Override
		public void print() {
			System.out.println("After private Incrementor:" + value.toString());
		}

	}

	public class Incrementor4 implements Incrementor {

		@Override
		public void increment() {
			value += increment;
		}

		@Override
		public void print() {
			System.out.println("After public Incrementor:" + value.toString());
		}

	}

	public static void incrementStatic() {
		Incrementor inc = new Incrementor1();
		inc.increment();
		inc.print();
	}

	public void increment() {
		Incrementor inc = new Incrementor3();
		inc.increment();
		inc.print();
	}

	public Incrementor4 getIncrementor() {
		return new Incrementor4();
	}

}
