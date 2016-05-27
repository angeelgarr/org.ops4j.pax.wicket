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
package org.ops4j.pax.wicket.api;

import javax.inject.Inject;

import org.apache.wicket.Component;

/**
 * General PaxWicket injection abstraction. This interface takes any object and tries to inject all {@link javax.inject.Inject}
 * annotations. In that way there is no difference if its a Wicket {@link org.apache.wicket.Component} or not.
 *
 * @author nmw
 * @version $Id: $Id
 */
public interface PaxWicketInjector {

    /**
     * The object to inject can contain a dept of abstractions from various bundles. Each of the levels may be required
     * to be injected from a different bundle. Therefore it have to be possible to look at each level independent.
     *
     * @throws java.lang.IllegalStateException if any internal state prevents the injector from finishing its work successfull
     * @param toInject a {@link java.lang.Object} object.
     * @param toHandle a {@link java.lang.Class} object.
     */
    public void inject(Object toInject, Class<?> toHandle) throws IllegalStateException;

}
