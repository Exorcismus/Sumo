package com.traffic.sumo;

public class Timer implements Runnable {

	private long countDown;

	public Timer(long countDown) {

		this.countDown = countDown;
	}

	@Override
	public void run() {
		double seconds;
		long elapsedTime;
		long startTime;

		startTime = System.nanoTime();

		do {
			elapsedTime = System.nanoTime() - startTime;
			seconds = (double) elapsedTime / 1000000000.0;
			// System.out.println(seconds);
		} while (seconds <= countDown);
	}
}
