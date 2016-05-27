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
package org.ops4j.pax.wicket.samples.blueprint.mount.internal;

import org.apache.wicket.markup.html.WebPage;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

/**
 * This page is automatically picked up by pax-wicket mounting the page at the location defined by the "mountPoint"
 * attribute.
 *
 * @author nmw
 * @version $Id: $Id
 */
@PaxWicketMountPoint(mountPoint = "automounted")
public class AutomountedPage extends WebPage {

}
