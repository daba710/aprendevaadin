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
package uk.co.q3c.v7.base.guice;

import org.apache.shiro.guice.web.ShiroWebModule;

import com.google.inject.servlet.ServletModule;

public class BaseModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/*").with(BaseServlet.class);
		ShiroWebModule.bindGuiceFilter(binder());
	}
	
}