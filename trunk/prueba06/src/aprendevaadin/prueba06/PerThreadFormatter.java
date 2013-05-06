package aprendevaadin.prueba06;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

class PerThreadFormatter {
	
	static int counter = 0;
	
	public static int getCounter() {
		synchronized (PerThreadFormatter.class) {
			return counter++;
		}
	}

	private static final ThreadLocal<SimpleDateFormat> dateFormatHolder = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			System.out.println("Creating SimpleDateFormat for Thread : " + Thread.currentThread().getName());
			return new SimpleDateFormat(String.format("[%d] dd/MM/yyyy", getCounter()));
		}
	};

	public static DateFormat getDateFormatter() {
		return dateFormatHolder.get();
	}

}
