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
package org.ops4j.pax.wicket.util.proxy;

import org.apache.wicket.util.io.IClusterable;
import org.ops4j.pax.wicket.spi.ProxyTargetLocator;

/**
 * Interface the lazy init proxies implement to make identification of the proxy and retrival of
 * {@link org.ops4j.pax.wicket.spi.ProxyTargetLocator} possible.
 *
 * @author Igor Vaynberg (ivaynberg)
 * @version $Id: $Id
 */
public interface ILazyInitProxy extends IClusterable {
    /**
     * <p>getObjectLocator.</p>
     *
     * @return object locator the proxy carries
     */
    ProxyTargetLocator getObjectLocator();
}
