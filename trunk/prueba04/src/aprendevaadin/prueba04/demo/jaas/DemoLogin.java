package aprendevaadin.prueba04.demo.jaas;

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

import aprendevaadin.prueba04.demo.jaas.dao.IDemoGroupPrincipalData;
import aprendevaadin.prueba04.demo.jaas.dao.IDemoGroupPrincipalIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectData;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.JassDao;

public class DemoLogin implements LoginModule {
	
	private Subject subject;
	private CallbackHandler callbackHandler;
	@SuppressWarnings("unused")
	private Map<String, ?> sharedState;
	@SuppressWarnings("unused")
	private Map<String, ?> options;
	
	private UserNameCredentials userNameCredentials = null;
	private ISubjectIdentifier subjectIdentifier = null;
	
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
		ISubjectIdentifier tmpSubjectIdentifier = JassDao.getInstance().getSubjectByName(userName);
		if (tmpSubjectIdentifier == null) {
			throw new LoginException("No se puede obtener un identificador de usuario para el nombre indicado.");
		} else {
			ISubjectData subjectData = JassDao.getInstance().getSubject(tmpSubjectIdentifier);
			if (subjectData.getPassword().equals(userPassword)) {
				this.userNameCredentials = new UserNameCredentials(userName);
				this.subjectIdentifier = tmpSubjectIdentifier;
			} else {
				throw new LoginException("El password proporcinado no coincide con el del usuario.");
			}
		}
		
		// Se indica si el Login ha resultado satisfactorio..
		return subjectIdentifier != null;
	}

	@Override
	public boolean commit() throws LoginException {
		if (subjectIdentifier == null) {
			return false;
		} else {
			// se incorporan las credenciales al Subject
			subject.getPublicCredentials().add(userNameCredentials);
			// Se incorporan los principales de tipo DemoGroup al Subject
			Set<IDemoGroupPrincipalIdentifier> demoGroupPrincipalIdentifiers = JassDao.getInstance().getDemoGroupPrincipalIdentifiers(subjectIdentifier);
			for (IDemoGroupPrincipalIdentifier identifier : demoGroupPrincipalIdentifiers) {
				IDemoGroupPrincipalData data = JassDao.getInstance().getDemoGroupPrincipalData(identifier);
				DemoGroupPrincipal demoGroupPrincipal = new DemoGroupPrincipal(data.getGroup());
				subject.getPrincipals().add(demoGroupPrincipal);
			}
			// Ya se han cargado todos los principales.
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
		
		// Se inicializan las propiedades que arrastran el estado del Modulo de Login.
		userNameCredentials = null;
		subjectIdentifier = null;
		
		// Se eliminan los principales de tipo DemoGroup
		Set<DemoGroupPrincipal> demoGroupPrincipals = subject.getPrincipals(DemoGroupPrincipal.class);
		subject.getPrincipals().removeAll(demoGroupPrincipals);
		
		// Se eliminan las credenciales.
		Set<UserNameCredentials> userCredentials = subject.getPublicCredentials(UserNameCredentials.class);
		subject.getPublicCredentials().removeAll(userCredentials);
		
		return true;
	}
	
	static public void dump(Subject subject) {
		
		System.out.println("-------------------------------");
		
		if (subject != null) {
		
			System.out.println(DemoGroupPrincipal.class.getName() + ":");
			Set<DemoGroupPrincipal> adminPrincipals = subject.getPrincipals(DemoGroupPrincipal.class);
			if (adminPrincipals != null) {
				for (DemoGroupPrincipal adminGroupPrincipal : adminPrincipals) {
					System.out.println(adminGroupPrincipal.toString());
				}
			}
			
			System.out.println(UserNameCredentials.class.getName() + ":");
			Set<UserNameCredentials> userCredentials = subject.getPublicCredentials(UserNameCredentials.class);
			if (userCredentials != null) {
				for (UserNameCredentials userCredential : userCredentials) {
					System.out.println(userCredential.toString());
				}
			}
			
		} else {
			
			System.out.println("Subject is null.");
			
		}

	}

}
