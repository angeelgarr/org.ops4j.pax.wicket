/**
 * Copyright OPS4J
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.wicket.api.support;

import org.apache.wicket.protocol.http.WebApplication;
import org.ops4j.pax.wicket.api.WebApplicationFactory;

/**
 * Most simple {@link org.ops4j.pax.wicket.api.WebApplicationFactory} which is expected to be used by blueprint or spring to register an wicket
 * webapplication for pax wicket. You only have to set a {@link org.apache.wicket.protocol.http.WebApplication} via the setter or the constructur and
 * register it as a service. Please keep in mind that you have to set at least the the "pax.wicket.mountpoint" and
 * "pax.wicket.applicationname" properties to your service to be started in pax-wicket.
 *
 * Please be aware that the {@link org.apache.wicket.protocol.http.WebApplication}, as well as your homepage class both have to be reachable via the
 * same classloader you expose this class!
 *
 * @author nmw
 * @version $Id: $Id
 */
public class SimpleWebApplicationFactory<T extends WebApplication> implements WebApplicationFactory<T> {

    private Class<T> wicketApplication;

    /**
     * <p>Constructor for SimpleWebApplicationFactory.</p>
     */
    public SimpleWebApplicationFactory() {
    }

    /**
     * <p>Constructor for SimpleWebApplicationFactory.</p>
     *
     * @param wicketApplication a {@link java.lang.Class} object.
     */
    public SimpleWebApplicationFactory(Class<T> wicketApplication) {
        this.wicketApplication = wicketApplication;
    }

    /**
     * <p>Setter for the field <code>wicketApplication</code>.</p>
     *
     * @param wicketApplication a {@link java.lang.Class} object.
     */
    public void setWicketApplication(Class<T> wicketApplication) {
        this.wicketApplication = wicketApplication;
    }

    /**
     * <p>getWebApplicationClass.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    public Class<T> getWebApplicationClass() {
        return wicketApplication;
    }

    /** {@inheritDoc} */
    public void onInstantiation(WebApplication application) {
    }
}
