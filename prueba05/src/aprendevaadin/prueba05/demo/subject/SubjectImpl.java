package aprendevaadin.prueba05.demo.subject;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import aprendevaadin.prueba05.demo.jaas.DemoCallbackHandler;
import aprendevaadin.prueba05.demo.jaas.DemoConfiguration;
import aprendevaadin.prueba05.demo.jaas.DemoLogin;

class SubjectImpl implements ISubjectService {
	
	private List<ISubjectListener> listeners = new ArrayList<ISubjectListener>();
	
	private Subject subject;
	
	public SubjectImpl() {
		this.subject = new Subject();
	}

	public Subject getSubject() {
		return subject;
	}
	
	private void setSubject(Subject subject) {
		this.subject = subject;
		fireSubjectUpdated();
	}

	private void dump(String msg) {
		DemoLogin.dump(msg, getSubject());
	}
	
	public void login(String user, String password) throws SubjectSeriviceException {
		dump("pre-login");
        DemoCallbackHandler callbackHandler = new DemoCallbackHandler(user, password);
        try {
        	LoginContext loginContext = new LoginContext(DemoConfiguration.APP_NAME, callbackHandler);
			loginContext.login();
			setSubject(loginContext.getSubject());
			dump("post-valid-login");
		} catch (LoginException e) {
			dump("post-invalid-login");
			throw new SubjectSeriviceException("No se puede obtener acceso al sistema", e);
		}
	}
	
	public void logout() throws SubjectSeriviceException {
        try {
			dump("pre-logout");
			LoginContext loginContext = new LoginContext(DemoConfiguration.APP_NAME, getSubject());
			loginContext.logout();
//			setSubject(null);
			dump("port-valid-logout");
		} catch (LoginException e) {
			dump("port-invalid-logout");
			throw new SubjectSeriviceException("No se puede abandonar el acceso al sistema de forma ordenada", e);
		}
	}

	@Override
	public void addListener(ISubjectListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(ISubjectListener listener) {
		listeners.remove(listener);
	}
	
	private void fireSubjectUpdated() {
		for (ISubjectListener listener : listeners.toArray(new ISubjectListener[] {})) {
			listener.subjectUpdated(new SubjectEventImpl(getSubject()));
		}
	}
	
	private class SubjectEventImpl implements ISubjectEvent {
		
		final private Subject subject;
		
		private SubjectEventImpl(Subject subject) {
			this.subject = subject;
		}

		@Override
		public Subject getSubject() {
			return subject;
		}
		
	}

}
