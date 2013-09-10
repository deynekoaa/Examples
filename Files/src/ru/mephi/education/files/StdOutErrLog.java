package ru.mephi.education.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class StdOutErrLog {

	private static PrintWriter writer;
	private static PrintWriter err;

	static {
		try {
			writer = new PrintWriter(new File("file.log"));
			err = new PrintWriter(new File("error.log"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void tieSystemOutAndErrToLog() {
		System.setOut(createLoggingProxy(System.out));
		System.setErr(createLoggingProxyErr(System.err));
	}

	public static PrintStream createLoggingProxy(
			final PrintStream realPrintStream) {
		return new PrintStream(realPrintStream) {
			public void print(final String string) {
				realPrintStream.print(string);
				writer.println(string);
				writer.flush();
			}
		};
	}

	public static PrintStream createLoggingProxyErr(
			final PrintStream realPrintStream) {
		return new PrintStream(realPrintStream) {
			public void print(final String string) {
				realPrintStream.print(string);
				err.println(string);
				err.flush();
			}
		};
	}
}