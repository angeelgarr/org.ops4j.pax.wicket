
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
 *
 * @author nmw
 * @version $Id: $Id
 */
package org.ops4j.pax.wicket.api;

import java.util.List;

import org.apache.wicket.Page;
public interface PageMounter {

    /**
     * <p>addMountPoint.</p>
     *
     * @param path a {@link java.lang.String} object.
     * @param pageClass a {@link java.lang.Class} object.
     */
    void addMountPoint(String path, Class<? extends Page> pageClass);

    /**
     * <p>getMountPoints.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<? extends MountPointInfo> getMountPoints();
}
