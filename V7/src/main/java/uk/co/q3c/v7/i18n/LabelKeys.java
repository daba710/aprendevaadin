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
package uk.co.q3c.v7.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LabelKeys implements I18NKeys<Labels> {
	ok,
	cancel,
	_notdefined_;

	@Override
	public Labels getBundle(Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(Labels.class.getName(), locale);
		return (Labels) bundle;
	}

	@Override
	public String getValue(Locale locale) {
		return getBundle(locale).getValue(this);
	}
}
