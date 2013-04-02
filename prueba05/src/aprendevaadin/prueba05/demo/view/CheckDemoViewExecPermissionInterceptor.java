package aprendevaadin.prueba05.demo.view;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CheckDemoViewExecPermissionInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object retObject = invocation.proceed();
		System.out.println("================> INTERCEPTOR");
		return retObject;
	}

}
