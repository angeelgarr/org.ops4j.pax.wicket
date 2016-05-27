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
package org.ops4j.pax.wicket.util.serialization;

import static org.ops4j.lang.NullArgumentException.validateNotNull;

import java.io.Serializable;

import org.ops4j.pax.wicket.internal.Activator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

final class ReplaceBundleContext implements Serializable {

    private static final long serialVersionUID = 1L;

    private final long bundleId;

    /**
     * Construct a new instance of {@code ReplaceBundleContext}.
     *
     * @param bundleContext The bundle context. Must not be {@code null}.
     *
     * @throws IllegalArgumentException Thrown if the specified
     * {@code aBundleContext} is {@code null}.
     * @since 0.5.4
     */
    ReplaceBundleContext(BundleContext bundleContext) throws IllegalArgumentException {
        validateNotNull(bundleContext, "bundleContext");

        Bundle bundle = bundleContext.getBundle();
        bundleId = bundle.getBundleId();
    }

    /**
     * Returns the bundle context.
     *
     * @return The bundle context.
     *
     * @since 0.5.4
     */
    final BundleContext getBundleContext() {
        return Activator.getBundleContextByBundleId(bundleId);
    }

}
