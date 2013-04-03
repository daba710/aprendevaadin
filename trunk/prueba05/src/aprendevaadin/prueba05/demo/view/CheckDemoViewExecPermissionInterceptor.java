package aprendevaadin.prueba05.demo.view;

import java.security.Permission;
import java.security.PrivilegedAction;

import javax.jms.IllegalStateException;
import javax.security.auth.Subject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import aprendevaadin.prueba05.demo.jaas.DemoViewExecPermission;
import aprendevaadin.prueba05.demo.subject.ISubjectService;

import com.google.inject.Provider;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

public class CheckDemoViewExecPermissionInterceptor implements MethodInterceptor {
	
	private Provider<ISubjectService> subjectServiceProvider;

	public CheckDemoViewExecPermissionInterceptor(Provider<ISubjectService> subjectServiceProvider) {
		this.subjectServiceProvider = subjectServiceProvider;
	}

	@Override
	public Object invoke(final MethodInvocation invocation) throws Throwable {
		
		// Se obtiene la instancia del ISubjectService
		ISubjectService subjectService = subjectServiceProvider.get();
		
		// Se verifica si tiene permiso
		Object[] args = invocation. getArguments();
		if (args != null && args.length == 1 && args[0] instanceof ViewChangeEvent) {
			ViewChangeEvent event = (ViewChangeEvent) args[0];
			if (event.getParameters() == null || event.getParameters().isEmpty()) {
				return invocation.proceed();
			} else {
				try {
					final String parameter = event.getParameters();
					Boolean hasPermission = Subject.doAsPrivileged(subjectService.getSubject(), new PrivilegedAction<Boolean>() {
						@Override
						public Boolean run() {
							SecurityManager sm = System.getSecurityManager();
							if (sm != null) {
								Permission perm = new DemoViewExecPermission(parameter);
								sm.checkPermission(perm);
								return true;
							} else {
								return false;
							}
						}
					}, null);
					if (hasPermission) {
						return invocation.proceed();
					} else {
						throw new IllegalStateException("No hay un SecurityManager instalado.");
					}
				} catch (SecurityException e) {
					throw new IllegalStateException("No dispone de permiso para acceder a esta vista.");
				}
			}
			
		} else {
			throw new IllegalAccessError("El interceptor no ha encontrado los parametos esperados.");
		}
		
	}
	
}
