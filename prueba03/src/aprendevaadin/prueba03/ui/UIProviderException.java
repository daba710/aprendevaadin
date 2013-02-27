package aprendevaadin.prueba03.ui;

public class UIProviderException extends RuntimeException {

	private static final long serialVersionUID = 590903978735309042L;

	protected UIProviderException() {
		super();
	}

	protected UIProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	protected UIProviderException(String message, Throwable cause) {
		super(message, cause);
	}

	protected UIProviderException(String message) {
		super(message);
	}

	protected UIProviderException(Throwable cause) {
		super(cause);
	}

}
