package aprendevaadin.prueba06;

import java.util.Date;

class Task implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Thread: " + Thread.currentThread().getName() + " Formatted Date: " + ThreadLocalMain.threadSafeFormat(new Date()));
		}
	}
}
