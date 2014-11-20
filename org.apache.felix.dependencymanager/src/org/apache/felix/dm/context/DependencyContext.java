/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.felix.dm.context;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;

import org.apache.felix.dm.Dependency;

/**
 * Every DependencyManager Dependency implementations must implement this interface.
 * 
 * @see {@link AbstractDependency} which already implements most of the methods from this interface.
 */
public interface DependencyContext extends Dependency {
    /**
     * Store the Component implementation context in the Dependency Implementation. This object is the entry point to
     * the Component implementation.
     * @param component the Component implementation context
     */
    public void setComponentContext(ComponentContext component);
    
    /**
     * The Component implementation ask this dependency to invoke the component "add" callback for the given dependency service event.
     * @param e the dependency service event, that has previously been submitted to the component implementation using
     * the ComponentContext.handleAdded method.
     * @see ComponentContext#handleAdded(DependencyContext, Event)
     */
	public void invokeAdd(Event e);
	
    /**
     * The Component implementation ask this dependency to invoke the component "change" callback for the given dependency service event.
     * @param e the dependency service event, that has previously been submitted to in the component implementation using
     * the ComponentContext.handleChanged method.
     * @see ComponentContext#handleChanged(DependencyContext, Event)
     */
	public void invokeChange(Event e);
	
    /**
     * The Component implementation ask this dependency to invoke the component "remove" callback for the given dependency service event.
     * @param e the dependency service event, that has previously been submitted to in the component implementation using
     * the ComponentContext.handleRemoved method.
     * @see ComponentContext#handleRemoved(DependencyContext, Event)
     */
	public void invokeRemove(Event e);
	
    /**
     * The Component implementation ask this dependency to invoke the component "swap" callback for the given dependency service event.
     * @param e the dependency service event, that has previously been submitted to in the component implementation using
     * the ComponentContext.handleSwapped method.
     * @see ComponentContext#handleSwapped(DependencyContext, Event, Event)
     */
	public void invokeSwap(Event event, Event newEvent);
	
	/**
	 *  Invoked by the component when the dependency should start working. 
	 **/
	public void start();
	
	/** 
	 * Invoked by the component when the dependency should stop working.
	 **/
	public void stop();
	
	/**
	 * Returns true if the dependency has been started, false if not
	 * @return true if the dependency has been started, false if not
	 */
	public boolean isStarted();
	
	/**
	 * Sets this dependency as available, meaning that at least one dependency service is available.
	 * @param available true to mark this dependency as available, false to mark it as unavailable
	 */
	public void setAvailable(boolean available);
		
	/**
     * Sets this dependency as "instance bound". A dependency is "instance bound" if it is defined from the 
     * component's init method. 
     * @param true if the dependency has to be marked as "intance bound", false if not.
     */
	public void setInstanceBound(boolean instanceBound);
	
	/**
	 * Is this dependency instance bound ?
	 * @return true if this dependency is instance bound, false if not
	 */
	public boolean isInstanceBound();

	/** 
	 * Does this dependency need the component instances to determine if the dependency is available or not.
	 * @return true if the dependency need the component instances before it can be started, false if not.
	 **/
	public boolean needsInstance();
	
	/**
	 * Return the type of the field which can be injected with the dependency service.
	 * @return the type of the field which can be injected with the dependency service, or null if the dependency does not 
	 * support auto config mode.
	 */
    public Class<?> getAutoConfigType();
    
    /**
     * Returns the highest ranked available dependency service instance, or null if the dependency is unavailable. 
     * @return the highest ranked available dependency service instance, or null
     */
    public Event getService();
    
    /**
     * Copy all the dependency service instances to the given collection.
     * @param coll the collection where the dependency service instances will be copied
     */
    public void copyToCollection(Collection<Object> coll);
    
    /**
     * Copy all the dependency service instances to the given map (key = dependency service, value = dependency servie properties).
     * @param map the map where the dependency service instances (with the corresponding service properties)
     */
    public void copyToMap(Map<Object, Dictionary<String, ?>> map);
    
    /**
     * Creates a clone of this dependency.
     * @return a clone of this dependency.
     */
    public DependencyContext createCopy();
}
