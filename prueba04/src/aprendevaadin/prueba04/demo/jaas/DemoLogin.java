package aprendevaadin.prueba04.demo.jaas;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class DemoLogin implements LoginModule {

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
	}

	@Override
	public boolean login() throws LoginException {
		return false;
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
