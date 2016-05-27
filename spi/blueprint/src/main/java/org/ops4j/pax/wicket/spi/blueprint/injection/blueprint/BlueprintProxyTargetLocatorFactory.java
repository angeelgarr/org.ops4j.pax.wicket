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
package org.ops4j.pax.wicket.spi.blueprint.injection.blueprint;

import java.lang.reflect.Field;
import java.util.Map;

import javax.inject.Named;

import org.ops4j.pax.wicket.api.PaxWicketBeanInjectionSource;
import org.ops4j.pax.wicket.spi.ProxyTargetLocator;
import org.ops4j.pax.wicket.spi.ProxyTargetLocatorFactory;
import org.osgi.framework.BundleContext;

/**
 * creates the actual instance
 *
 * @author nmw
 * @version $Id: $Id
 */
public class BlueprintProxyTargetLocatorFactory implements ProxyTargetLocatorFactory {

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName() {
        return PaxWicketBeanInjectionSource.INJECTION_SOURCE_BLUEPRINT;
    }

    /** {@inheritDoc} */
    public ProxyTargetLocator createProxyTargetLocator(BundleContext context, Field field, Class<?> page,
            Map<String, String> overwrites) {
        Named annotation = field.getAnnotation(Named.class);
        if (annotation.value().isEmpty()) {
            // We require a name!
            return null;
        }
        BlueprintBeanProxyTargetLocator locator = new BlueprintBeanProxyTargetLocator(context, annotation.value(),
            field.getType(), page, overwrites);
        if (locator.hasApplicationContext()) {
            return locator;
        } else {
            return null;
        }
    }

}
