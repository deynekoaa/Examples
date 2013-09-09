package ru.mephi.education.clazz;

public abstract class HelloWorld implements GetHelloWorld, SayHello {

	@Override
	abstract public String getHello();

	@Override
	public void sayHello() {
		say(getHello() + getWorld());
	}

	@Override
	abstract public String getWorld();

	abstract protected void say(String msg);

}
