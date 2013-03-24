package aprendevaadin.prueba04.demo.subject.jaas;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import aprendevaadin.prueba04.demo.subject.dao.UsersDAO;

public class DemoLogin implements LoginModule {
	
	private Subject subject;
	private CallbackHandler callbackHandler;
	@SuppressWarnings("unused")
	private Map<String, ?> sharedState;
	@SuppressWarnings("unused")
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
		userCredentials = UsersDAO.getInstance().getUserByCredentials(userName, userPassword);
		
		// Se retorna el resultado de la verificacion.
		return userCredentials != null;
	}

	@Override
	public boolean commit() throws LoginException {
		if (userCredentials == null) {
			return false;
		} else {
			subject.getPublicCredentials().add(userCredentials);
			subject.getPrincipals().addAll(UsersDAO.getInstance().getPrincipals(userCredentials));
			return true;
		}
	}

	@Override
	public boolean abort() throws LoginException {
		logout();
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		
		userCredentials = null;
		
		Set<UserGroupPrincipal> userPrincipals = subject.getPrincipals(UserGroupPrincipal.class);
		subject.getPrincipals().removeAll(userPrincipals);
		
		Set<AdminGroupPrincipal> adminPrincipals = subject.getPrincipals(AdminGroupPrincipal.class);
		subject.getPrincipals().removeAll(adminPrincipals);
		
		Set<IUserCredentials> userCredentials = subject.getPublicCredentials(IUserCredentials.class);
		subject.getPublicCredentials().removeAll(userCredentials);
		
		return true;
	}
	
	static public void dump(Subject subject) {
		
		System.out.println("-------------------------------");
		
		if (subject != null) {
		
			System.out.println(UserGroupPrincipal.class.getName() + ":");
			Set<UserGroupPrincipal> userPrincipals = subject.getPrincipals(UserGroupPrincipal.class);
			if (userPrincipals != null) {
				for (UserGroupPrincipal userGroupPrincipal : userPrincipals) {
					System.out.println(userGroupPrincipal.toString());
				}
			}
			
			System.out.println(AdminGroupPrincipal.class.getName() + ":");
			Set<AdminGroupPrincipal> adminPrincipals = subject.getPrincipals(AdminGroupPrincipal.class);
			if (adminPrincipals != null) {
				for (AdminGroupPrincipal adminGroupPrincipal : adminPrincipals) {
					System.out.println(adminGroupPrincipal.toString());
				}
			}
			
			System.out.println(IUserCredentials.class.getName() + ":");
			Set<IUserCredentials> userCredentials = subject.getPublicCredentials(IUserCredentials.class);
			if (userCredentials != null) {
				for (IUserCredentials userCredential : userCredentials) {
					System.out.println(userCredential.toString());
				}
			}
			
		} else {
			
			System.out.println("Subject is null.");
			
		}

	}

}
