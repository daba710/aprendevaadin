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
package uk.co.q3c.v7.base.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class IniModule extends AbstractModule {

	@Override
	protected void configure() {
	}

	@Provides
	protected V7Ini provideIni() {
		V7Ini ini = new V7Ini();
		ini.loadFromPath("classpath:V7.ini");
		return ini;
	}

}
