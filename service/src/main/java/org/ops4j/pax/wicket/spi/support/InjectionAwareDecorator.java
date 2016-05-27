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
package org.ops4j.pax.wicket.spi.support;

import org.osgi.framework.BundleContext;

/**
 * This interface describes a set of methods each injection parser and every decorator should provide to unify the
 * readability and handling of those classes.
 *
 * @author nmw
 * @version $Id: $Id
 */
public interface InjectionAwareDecorator {

    /**
     * <p>setBundleContext.</p>
     *
     * @param bundleContext a {@link org.osgi.framework.BundleContext} object.
     */
    void setBundleContext(BundleContext bundleContext);

    /**
     * <p>start.</p>
     *
     * @throws java.lang.Exception if any.
     */
    void start() throws Exception;

    /**
     * <p>stop.</p>
     *
     * @throws java.lang.Exception if any.
     */
    void stop() throws Exception;

}
