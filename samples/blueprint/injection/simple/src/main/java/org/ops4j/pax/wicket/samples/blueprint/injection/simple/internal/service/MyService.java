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
package org.ops4j.pax.wicket.samples.blueprint.injection.simple.internal.service;

import java.io.Serializable;

/**
 * This class presents a VERY simple interface which can be located in any package or bundle wished. For simplicity it
 * should be kept in one bundle for now.
 *
 * @author nmw
 * @version $Id: $Id
 */
public interface MyService extends Serializable {

    /**
     * Very simple method returning the toEcho value.
     *
     * @param toEcho a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    String someEchoMethod(String toEcho);

}
