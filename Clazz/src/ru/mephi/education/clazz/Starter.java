package ru.mephi.education.clazz;

public class Starter {

	public static void main(String[] args) {

		HelloWorld helloWorld = new HelloWorldImpl();
		helloWorld.sayHello();

		helloWorld = new HelloWorldImpl2("MSG:");
		HelloWorld helloWorld2 = new HelloWorldImpl2("MESSAGE:");
		helloWorld.sayHello();
		helloWorld2.sayHello();

	}

}
