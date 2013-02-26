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
package uk.co.q3c.v7.base.ui;

import java.util.Map;

import javax.inject.Inject;

import uk.co.q3c.v7.base.guice.uiscope.UIKeyProvider;

import com.google.inject.Injector;
import com.google.inject.Provider;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.ui.UI;

/**
 * A simple, single UI class provider using {@link BasicUI}
 * 
 * @author David Sowerby 9 Jan 2013
 * 
 */
public class BasicUIProvider extends ScopedUIProvider {

	@Inject
	protected BasicUIProvider(Injector injector, Map<String, Provider<UI>> uiProMap, UIKeyProvider mainwindowKeyProvider) {
		super(injector, uiProMap, mainwindowKeyProvider);
	}

	/**
	 * Simply returns BasicUI.class
	 * 
	 * @see com.vaadin.server.UIProvider#getUIClass(com.vaadin.server.UIClassSelectionEvent)
	 */
	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		return BasicUI.class;
	}

}