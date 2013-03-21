package aprendevaadin.prueba04.demo.jaas;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import aprendevaadin.prueba04.demo.jaas.dao.UsersDAO;

public class DemoLogin implements LoginModule {
	
	private Subject subject;
	private CallbackHandler callbackHandler;
	private Map<String, ?> sharedState;
	private Map<String, ?> options;
	
	private IUserCredentials userCredentials;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
	}

	@Override
	public boolean login() throws LoginException {
		
		// Se utiliza el callback handler para recolectar las credenciales.
		NameCallback nameCallback = new NameCallback("user");
		PasswordCallback passwordCallback = new PasswordCallback("password", false);
		Callback[] callbacks = new Callback[] { nameCallback, passwordCallback };
		try {
			callbackHandler.handle(callbacks);
		} catch (IOException ioEx) {
			LoginException loginEx = new LoginException("IOException logging in.");
			loginEx.initCause(ioEx);
			throw loginEx;
		} catch (UnsupportedCallbackException ucbEx) {
			String className = ucbEx.getCallback().getClass().getName();
			LoginException loginEx = new LoginException(className + " is not a supported Callback");
			loginEx.initCause(ucbEx);
			throw loginEx;
		}
		String userName = nameCallback.getName();
		String userPassword = String.valueOf(passwordCallback.getPassword());
		
		// Se verifican las credenciales
		userCredentials = UsersDAO.getUser(userName, userPassword);
		
		// Se retorna el resultado de la verificacion.
		return userCredentials != null;
	}

	@Override
	public boolean commit() throws LoginException {
		return false;
	}

	@Override
	public boolean abort() throws LoginException {
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		return false;
	}

}
