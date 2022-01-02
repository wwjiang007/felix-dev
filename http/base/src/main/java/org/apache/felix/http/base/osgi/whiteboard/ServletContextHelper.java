/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.felix.http.base.osgi.whiteboard;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import org.osgi.annotation.versioning.ConsumerType;
import org.osgi.framework.Bundle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * THIS IS NOT AN OFFICIAL OSGi API - IT HAS BEEN CREATED TO EVALUATE
 * DIFFERENT APPROACHES FOR A FUTURE OSGI SPECIFICATION
 */
@ConsumerType
public abstract class ServletContextHelper {

    public static final String	REMOTE_USER			= "org.osgi.service.http.authentication.remote.user";

    public static final String	AUTHENTICATION_TYPE	= "org.osgi.service.http.authentication.type";

    public static final String	AUTHORIZATION		= "org.osgi.service.useradmin.authorization";

	private final Bundle		bundle;

	public ServletContextHelper() {
		this(null);
	}

	public ServletContextHelper(final Bundle bundle) {
		this.bundle = bundle;
	}

	public boolean handleSecurity(final HttpServletRequest request,
			final HttpServletResponse response)
			throws IOException {
		return true;
	}

	public void finishSecurity(final HttpServletRequest request,
			final HttpServletResponse response) {
		// do nothing
	}

	public URL getResource(String name) {
		if ((name != null) && (bundle != null)) {
			if (name.startsWith("/")) {
				name = name.substring(1);
			}

			return bundle.getEntry(name);
		}
		return null;
	}

	public String getMimeType(final String name) {
		return null;
	}

	public Set<String> getResourcePaths(final String path) {
		if ((path != null) && (bundle != null)) {
			final Enumeration<URL> e = bundle.findEntries(path, null, false);
			if (e != null) {
				final Set<String> result = new LinkedHashSet<String>();
				while (e.hasMoreElements()) {
					result.add(e.nextElement().getPath());
				}
				return result;
			}
		}
		return null;
	}

	public String getRealPath(final String path) {
		return null;
	}
}
