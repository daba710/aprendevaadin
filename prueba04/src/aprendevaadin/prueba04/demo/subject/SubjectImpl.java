package aprendevaadin.prueba04.demo.subject;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

class SubjectImpl implements ISubjectService {
	
	private List<ISubjectListener> listeners = new ArrayList<ISubjectListener>();

	public Subject getSubject() {
		return null;
	}
	
	public void login(String user, String password) {
		
	}
	
	public void logout() {
		
	}

	@Override
	public void addListener(ISubjectListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(ISubjectListener listener) {
		listeners.remove(listener);
	}
	
	private void firePrincipalUpdated(Subject subject) {
		for (ISubjectListener listener : listeners.toArray(new ISubjectListener[] {})) {
			listener.subjectUpdated(new SubjectEventImpl(subject));
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
