package aprendevaadin.prueba04.demo.subject;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import aprendevaadin.prueba04.demo.subject.jaas.DemoCallbackHandler;
import aprendevaadin.prueba04.demo.subject.jaas.DemoConfiguration;

class SubjectImpl implements ISubjectService {
	
	private List<ISubjectListener> listeners = new ArrayList<ISubjectListener>();
	
	private Subject subject;

	public Subject getSubject() {
		return subject;
	}
	
	private void setSubject(Subject subject) {
		this.subject = subject;
		fireSubjectUpdated();
	}
	
	public void login(String user, String password) throws SubjectSeriviceException {
        DemoCallbackHandler callbackHandler = new DemoCallbackHandler(user, password);
        try {
        	LoginContext loginContext = new LoginContext(DemoConfiguration.APP_NAME, callbackHandler);
			loginContext.login();
			setSubject(loginContext.getSubject());
		} catch (LoginException e) {
			throw new SubjectSeriviceException("No se puede obtener acceso al sistema", e);
		}
	}
	
	public void logout() throws SubjectSeriviceException {
        try {
			LoginContext loginContext = new LoginContext(DemoConfiguration.APP_NAME, getSubject());
			loginContext.logout();
			setSubject(null);
		} catch (LoginException e) {
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
