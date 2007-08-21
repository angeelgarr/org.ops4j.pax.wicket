/*
 * Copyright 2006 Niclas Hedhman.
 * Copyright 2007 David Leangen
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
package org.ops4j.pax.wicket.toolkit.menus;

import org.apache.wicket.markup.html.panel.Panel;
import org.ops4j.pax.wicket.toolkit.actions.ActionContainer;
import org.ops4j.pax.wicket.util.AbstractAggregatedSource;
import org.osgi.framework.BundleContext;

public class PaxWicketMenu extends AbstractAggregatedSource<Panel>
    implements ActionContainer
{

    public static final String MENU_PREFIX = "menu:";

    private String m_menuName;

    public PaxWicketMenu( BundleContext context, String application, String menuName, String defaultDestination )
    {
        super( context, application, menuName, defaultDestination );
        m_menuName = menuName;
    }

    @Override
    protected Panel createComponent( String wicketId )
        throws IllegalArgumentException
    {
        return new PaxWicketMenuPanel( this, wicketId );
    }

    public String getMenuName()
    {
        return m_menuName;
    }

    public String getIdentifier()
    {
        return MENU_PREFIX + m_menuName;
    }
}
