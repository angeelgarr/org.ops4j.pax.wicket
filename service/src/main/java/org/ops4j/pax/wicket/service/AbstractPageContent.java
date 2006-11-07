/*
 * Copyright 2006 Niclas Hedhman.
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.ops4j.pax.wicket.service;

import java.util.Dictionary;
import java.util.Hashtable;
import org.ops4j.lang.NullArgumentException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

import wicket.Page;


public abstract class AbstractPageContent<T extends Page> 
    implements PageContent<T>, ManagedService
{

    private BundleContext m_bundleContext;
    private ServiceRegistration m_serviceRegistration;
    private Hashtable<String, String> m_properties;

    protected AbstractPageContent( BundleContext bundleContext, 
            String pageId, String applicationName, String pageName )
        throws IllegalArgumentException
    {
        NullArgumentException.validateNotNull( bundleContext, "bundleContext" );
        NullArgumentException.validateNotEmpty( pageId, "pageId" );
        NullArgumentException.validateNotEmpty( applicationName, "applicationName" );
        NullArgumentException.validateNotEmpty( pageName, "pageName" );

        m_properties = new Hashtable<String, String>();
        m_bundleContext = bundleContext;
        setApplicationName( applicationName );
        setPageName( pageName );
        
        m_properties.put( Constants.SERVICE_PID, Content.PAGE_ID + "/" + pageId );
    }

    public final void register()
        throws IllegalStateException
    {
        String[] classes = { PageContent.class.getName(), ManagedService.class.getName() };
        
        synchronized ( this )
        {
            if( m_serviceRegistration != null )
            {
                Class< ? extends AbstractPageContent> clazz = getClass();
                String className = clazz.getSimpleName();
                throw new IllegalArgumentException( className + "[" + this + "] has been registered." );
            }
            
            m_serviceRegistration = m_bundleContext.registerService( classes, this, m_properties );
        }
    }

    public final void dispose()
        throws IllegalStateException
    {
        synchronized ( this )
        {
            if( m_serviceRegistration == null )
            {
                Class< ? extends AbstractPageContent> clazz = getClass();
                String className = clazz.getSimpleName();
                throw new IllegalStateException( className + "[" + this + "] has not been registered." );
            }
            
            m_serviceRegistration.unregister();
            m_serviceRegistration = null;
        }
    }

    /**
     * Returns the application name.
     * 
     * @return The application name.
     * 
     * @since 1.0.0
     */
    public final String getApplicationName()
    {
        synchronized ( this )
        {
            return m_properties.get( Content.APPLICATION_NAME );
        }
    }

    /**
     * Returns the page name.
     * 
     * @return The page name.
     * 
     * @since 1.0.0
     */
    public final String getPageName()
    {
        synchronized ( this )
        {
            return m_properties.get( Content.PAGE_NAME );
        }
    }

    public void updated( Dictionary config ) throws ConfigurationException
    {
        if ( config == null )
        {
            synchronized ( this )
            {
                m_serviceRegistration.setProperties( m_properties );
            }
            
            return;
        }
        
        String pagename = ( String ) config.get( Content.PAGE_NAME );
        String appname = ( String ) config.get( Content.APPLICATION_NAME );
        setPageName( pagename );
        setApplicationName( appname );
        synchronized ( this )
        {
            m_serviceRegistration.setProperties( config );
        }
    }

    /**
     * Sets the application name.
     * 
     * @param applicationName The application name. This argument must not be {@code null} or empty.
     * 
     * @throws IllegalArgumentException Thrown if the specified {@code applicationName} is {@code null}.
     * 
     * @since 1.0.0
     */
    protected final void setApplicationName( String applicationName )
        throws IllegalArgumentException
    {
        NullArgumentException.validateNotEmpty( applicationName, "applicationName" );

        synchronized ( this )
        {
            m_properties.put( Content.APPLICATION_NAME, applicationName );
        }
    }

    /**
     * Set the page name.
     * 
     * @param pageName The page name. This argument must not be {@code null} or empty.
     * 
     * @throws IllegalArgumentException Thrown if the specified {@code pageName} arguments are {@code null}.
     * 
     * @since 1.0.0
     */
    protected final void setPageName( String pageName )
        throws IllegalArgumentException
    {
        NullArgumentException.validateNotEmpty( pageName, "pageName" );

        synchronized ( this )
        {
            m_properties.put( Content.PAGE_NAME, pageName );
        }
    }
}
