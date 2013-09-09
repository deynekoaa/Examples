package ru.mephi.education.clazz;

public class HelloWorldImpl extends HelloWorld {

	@Override
	public String getHello() {
		return "Hello";
	}

	@Override
	public String getWorld() {
		return "World";
	}

	@Override
	protected void say(String msg) {
		System.out.println(msg);
	}

}
