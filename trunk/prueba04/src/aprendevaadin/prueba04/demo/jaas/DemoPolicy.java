package aprendevaadin.prueba04.demo.jaas;

import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.Principal;
import java.security.ProtectionDomain;
import java.util.List;

public class DemoPolicy extends Policy {

	@Override
	public PermissionCollection getPermissions(CodeSource codesource) {
		final Permissions persmissions = new Permissions();
		persmissions.add(new AllPermission());
		return persmissions;
	}
	
	@Override
	public PermissionCollection getPermissions(ProtectionDomain domain) {
		
		final Permissions persmissions = new Permissions();
		
		Principal[] principals = domain.getPrincipals();
		if (principals != null && principals.length > 0) {
			
			////////////////////////////////////////////////////////////////////////
			//
			////////////////////////////////////////////////////////////////////////
			for (Principal principal : principals) {
				if (principal instanceof DemoGroupPrincipal) {
					DemoGroupPrincipal demoGroupPrincipal = (DemoGroupPrincipal) principal;
					
				}
			}
			
		} else {
			
			////////////////////////////////////////////////////////////////////////
			//
			////////////////////////////////////////////////////////////////////////
		}
	}
	
	private List<Permission> getDemoGroupPermission() {
		
	}
	
	@Override
	public boolean implies(ProtectionDomain domain, Permission permission) {
		// TODO Auto-generated method stub
		return super.implies(domain, permission);
	}
	
}
