package aprendevaadin.prueba05.demo.view;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import aprendevaadin.prueba05.demo.subject.ISubjectService;

import com.google.inject.Provider;

public class CheckDemoViewExecPermissionInterceptor implements MethodInterceptor {
	
	private Provider<ISubjectService> subjectServiceProvider;

	public CheckDemoViewExecPermissionInterceptor(Provider<ISubjectService> subjectServiceProvider) {
		this.subjectServiceProvider = subjectServiceProvider;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object retObject = invocation.proceed();
		System.out.println("================> INTERCEPTOR " +  (this.subjectServiceProvider == null ? "NO_INYECTADO" : "SI_INYECTADO"));
		return retObject;
	}

}
