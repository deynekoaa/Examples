package ru.mephi.education.threads;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class StartAndJoinThread {

	// Change to HashMap to view ConcurentModification Exception
	private static ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();

	/**
	 * Print mesage
	 * 
	 * @param msg
	 */
	private static void log(String msg) {
		System.out.println(msg);
	}

	/**
	 * format message by adding Thread name
	 * 
	 * @param msg
	 * @return formatted message
	 */
	private static String formatMsg(String msg) {
		return "[" + Thread.currentThread().getName() + "]:" + msg;
	}

	/**
	 * @author Bazar Thread for writing into HashMap
	 */
	public static class MyThread extends Thread {

		private static int seqNum = 0;

		public MyThread() {
			this.setName("MyThread-" + ++seqNum);
		}

		// volatile flag for sychronization
		private volatile boolean active = true;

		/**
		 * deactivating thread cicle
		 */
		public void deactivate() {
			active = false;
		}

		@Override
		public void run() {
			int i = 0;
			// do job while active
			while (active) {
				String msg = "MSG=" + ++i;
				// add msg to map
				map.put(i % 10, formatMsg(msg));
			}
		}

	}

	/**
	 * @author Bazar runnable for writing into conslole
	 */
	public static class MyRunnable implements Runnable {

		/**
		 * volatile flag for sychronization
		 */
		private volatile boolean active = true;

		/**
		 * deactivating thread cicle
		 */
		public void deactivate() {
			active = false;
		}

		@Override
		public void run() {
			Thread.currentThread().setName("MyThreadReader");
			while (active) {
				// iterate over map
				for (Entry<Integer, String> entry : map.entrySet()) {
					log(entry.getValue());
				}
			}
		}

	}

	public static void main(String[] args) {
		// Creating and starting new Thread via runnable
		MyRunnable myRunnable = new MyRunnable();
		Thread myThreadReader = new Thread(myRunnable);
		myThreadReader.start();

		// Creating and starting new thread
		MyThread myThread = new MyThread();
		myThread.start();
		for (int i = 0; i < 10000; ++i) {
			String msg = "MSG=" + i;
			map.put(i % 10, formatMsg(msg));
		}

		try {
			// deactivating and joining thread
			myThread.deactivate();
			myThread.join();

			// deactivating and joining thread created via runnable
			myRunnable.deactivate();
			myThreadReader.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
