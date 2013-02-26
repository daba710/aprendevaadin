/*
 * Copyright (C) 2013 David Sowerby
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package uk.co.q3c.v7.base.shiro;

import javax.inject.Inject;

import org.apache.shiro.authc.UsernamePasswordToken;

import uk.co.q3c.v7.base.config.V7Ini.StandardPageKey;
import uk.co.q3c.v7.base.navigate.V7Navigator;
import uk.co.q3c.v7.base.view.LoginView;

public class DefaultLoginExceptionHandler implements LoginExceptionHandler {
	// TODO i18N
	private final V7Navigator navigator;
	public static final String invalidLogin = "That username or password was not recognised";
	public static final String concurrent = "This account is already in use.  You must log out of that session before you can log in again.";

	@Inject
	protected DefaultLoginExceptionHandler(V7Navigator navigator) {
		this.navigator = navigator;
	}

	@Override
	public void unknownAccount(LoginView loginView, UsernamePasswordToken token) {
		loginView.setStatusMessage(invalidLogin);
	}

	@Override
	public void incorrectCredentials(LoginView loginView, UsernamePasswordToken token) {
		loginView.setStatusMessage(invalidLogin);
	}

	@Override
	public void expiredCredentials(LoginView loginView, UsernamePasswordToken token) {
		navigator.navigateTo(StandardPageKey.refreshAccount);
	}

	@Override
	public void accountLocked(LoginView loginView, UsernamePasswordToken token) {
		navigator.navigateTo(StandardPageKey.unlockAccount);
	}

	@Override
	public void excessiveAttempts(LoginView loginView, UsernamePasswordToken token) {
		navigator.navigateTo(StandardPageKey.resetAccount);
	}

	@Override
	public void concurrentAccess(LoginView loginView, UsernamePasswordToken token) {
		loginView.setStatusMessage(concurrent);
	}

	@Override
	public void disabledAccount(LoginView loginView, UsernamePasswordToken token) {
		navigator.navigateTo(StandardPageKey.enableAccount);
	}

}
