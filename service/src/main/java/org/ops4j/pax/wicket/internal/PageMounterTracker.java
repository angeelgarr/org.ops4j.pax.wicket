
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
package org.ops4j.pax.wicket.internal;

import static java.lang.String.format;
import static org.ops4j.lang.NullArgumentException.validateNotNull;
import static org.ops4j.pax.wicket.api.Constants.APPLICATION_NAME;
import static org.osgi.framework.Constants.OBJECTCLASS;

import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.mock.MockWebRequest;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.ops4j.pax.wicket.api.MountPointInfo;
import org.ops4j.pax.wicket.api.PageMounter;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public final class PageMounterTracker extends ServiceTracker<PageMounter, PageMounter> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageMounterTracker.class);

    private final WebApplication application;

    /**
     * <p>Constructor for PageMounterTracker.</p>
     *
     * @param context a {@link org.osgi.framework.BundleContext} object.
     * @param application a {@link org.apache.wicket.protocol.http.WebApplication} object.
     * @param applicationName a {@link java.lang.String} object.
     * @param applicationName a {@link java.lang.String} object.
     * @param applicationName a {@link java.lang.String} object.
     * @param applicationName a {@link java.lang.String} object.
     * @param applicationName a {@link java.lang.String} object.
     * @param applicationName a {@link java.lang.String} object.
     * @throws java.lang.IllegalArgumentException if any.
     */
    public PageMounterTracker(BundleContext context, WebApplication application, String applicationName)
        throws IllegalArgumentException {
        super(context, createFilter(context, applicationName), null);
        validateNotNull(application, "application");
        this.application = application;
    }

    private static Filter createFilter(BundleContext context, String applicationName)
        throws IllegalArgumentException {
        validateNotNull(context, "Context");
        validateNotNull(applicationName, "applicationName");
        String filterString =
            "(&(" + OBJECTCLASS + "=" + PageMounter.class.getName() + ")"
                    + "(" + APPLICATION_NAME + "=" + applicationName + "))";
        try {
            return context.createFilter(filterString);
        } catch (InvalidSyntaxException e) {
            throw new IllegalStateException(format(
                "Application name [%s] is not allowed to contain spaces or special chars", applicationName), e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public final PageMounter addingService(ServiceReference<PageMounter> reference) {
        PageMounter mounter = super.addingService(reference);
        List<? extends MountPointInfo> infos = mounter.getMountPoints();
        for (MountPointInfo info : infos) {
            LOGGER.trace("Make sure that path {} is clear before trying to remount", info.getPath());
            Application oldApp = ThreadContext.getApplication();
            ThreadContext.setApplication(application);
            try {
                application.unmount(info.getPath());
            } catch (IllegalArgumentException e) {
                LOGGER.trace("Unmounting not possible since nothing here by now.");
                // this could happen if wicket had not been started at all by now --> simply ignore
            }
            LOGGER.trace("Trying to mount {} with {}", info.getPath(), info.getPage().getName());
            application.mountPage(info.getPath(), info.getPage());
            ThreadContext.setApplication(oldApp);
            LOGGER.info("Mounted {} with {}", info.getPath(), info.getPage().getName());
        }
        return mounter;
    }

    /** {@inheritDoc} */
    @Override
    public final void removedService(ServiceReference<PageMounter> reference, PageMounter mounter) {
        PageMounter pageMounter = mounter;
        List<? extends MountPointInfo> infos = pageMounter.getMountPoints();
        for (MountPointInfo info : infos) {
            LOGGER.trace("Trying to mount {} with {}", info.getPath(), info.getPage().getName());
            Application oldApp = ThreadContext.getApplication();
            ThreadContext.setApplication(application);
            if (!Session.exists()) {
                Request request = new MockWebRequest(Url.parse(info.getPath()));
                ThreadContext.setSession(new WebSession(request));
            }
            application.unmount(info.getPath());
            ThreadContext.setApplication(oldApp);
            LOGGER.info("Unmounted {} with {}", info.getPath(), info.getPage().getName());
        }

        super.removedService(reference, pageMounter);
    }
}
