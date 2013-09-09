package ru.mephi.education.clazz;

public class HelloWorldImpl2 extends HelloWorld {

	private static String hello;
	static {
		System.out.println("Static initialization");
		hello = "Hello";
	}

	{
		System.out.println("pre-initialization");
	}

	private String msg;

	public HelloWorldImpl2(String msg) {
		super();
		this.msg = msg;
		System.out.println("Constructor. msg=" + msg);
	}

	@Override
	public String getHello() {
		return hello;
	}

	@Override
	public String getWorld() {
		return " World";
	}

	@Override
	protected void say(String msg) {
		System.out.println(msg);
	}

	@Override
	public void sayHello() {
		System.out.print(msg);
		super.sayHello();
	}

}
