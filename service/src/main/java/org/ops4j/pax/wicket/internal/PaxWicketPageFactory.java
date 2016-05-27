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
package org.ops4j.pax.wicket.internal;

import static org.ops4j.lang.NullArgumentException.validateNotNull;

import java.util.HashMap;

import org.apache.wicket.IPageFactory;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.session.DefaultPageFactory;
import org.ops4j.pax.wicket.api.PageFactory;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Wrapper around the original wicket {@link org.apache.wicket.session.DefaultPageFactory} adding lookup possiblities for own page loaders. In
 * case non are provided the original wicket algorithm for loading of pages is used.
 *
 * @author nmw
 * @version $Id: $Id
 */
public final class PaxWicketPageFactory implements IPageFactory {

    private final BundleContext bundleContext;
    private final String applicationName;
    private final HashMap<Class<? extends IRequestablePage>, PageFactory<? extends IRequestablePage>> contents;

    private ServiceTracker<PageFactory<? extends IRequestablePage>, PageFactory<? extends IRequestablePage>> m_pageTracker;

    /**
     * <p>Constructor for PaxWicketPageFactory.</p>
     *
     * @param context a {@link org.osgi.framework.BundleContext} object.
     * @param applicationName a {@link java.lang.String} object.
     * @throws java.lang.IllegalArgumentException if any.
     */
    public PaxWicketPageFactory(BundleContext context, String applicationName) throws IllegalArgumentException {
        validateNotNull(context, "context");
        validateNotNull(applicationName, "applicationName");
        contents = new HashMap<Class<? extends IRequestablePage>, PageFactory<? extends IRequestablePage>>();
        bundleContext = context;
        this.applicationName = applicationName;
    }

    /**
     * <p>initialize.</p>
     */
    public final void initialize() {
        m_pageTracker = new PaxWicketPageTracker(bundleContext, applicationName, this);
        m_pageTracker.open();
    }

    /**
     * <p>dispose.</p>
     */
    public final void dispose() {
        synchronized (this) {
            contents.clear();
            m_pageTracker.close();
        }
    }

    /**
     * {@inheritDoc}
     *
     * Creates a new page using a page class.
     */
    public final <C extends IRequestablePage> C newPage(final Class<C> pageClass) {
        PageFactory<C> content = getFactory(pageClass);
        if (content != null) {
            return content.createPage(new PageParameters());
        }
        return new DefaultPageFactory().newPage(pageClass);
    }

    /**
     * {@inheritDoc}
     *
     * Creates a new Page, passing PageParameters to the Page constructor if such a constructor exists. If no such
     * constructor exists and the parameters argument is null or empty, then any available default constructor will be
     * used.
     */
    public final <C extends IRequestablePage> C newPage(final Class<C> pageClass, final PageParameters parameters) {
        PageFactory<C> content = getFactory(pageClass);
        if (content != null) {
            return content.createPage(parameters);
        }
        return new DefaultPageFactory().newPage(pageClass, parameters);
    }

    @SuppressWarnings("unchecked")
    private <C extends IRequestablePage> PageFactory<C> getFactory(final Class<C> pageClass) {
        PageFactory<C> content;
        synchronized (this) {
            content = (PageFactory<C>) contents.get(pageClass);
        }
        return content;
    }

    /** {@inheritDoc} */
    public <C extends IRequestablePage> boolean isBookmarkable(Class<C> pageClass) {
        return new DefaultPageFactory().isBookmarkable(pageClass);
    }

    /**
     * <p>add.</p>
     *
     * @param pageSource a {@link org.ops4j.pax.wicket.api.PageFactory} object.
     * @throws java.lang.IllegalArgumentException if any.
     */
    public void add(PageFactory<? extends IRequestablePage> pageSource)
        throws IllegalArgumentException {
        validateNotNull(pageSource, "pageSource");
        Class<? extends IRequestablePage> pageClass = pageSource.getPageClass();
        validateNotNull(pageSource, "pageClass");
        synchronized (this) {
            contents.put(pageClass, pageSource);
        }
    }

    /**
     * <p>remove.</p>
     *
     * @param pageSource a {@link org.ops4j.pax.wicket.api.PageFactory} object.
     * @throws java.lang.IllegalArgumentException if any.
     */
    public final void remove(PageFactory<? extends IRequestablePage> pageSource) throws IllegalArgumentException {
        validateNotNull(pageSource, "pageSource");

        synchronized (this) {
            contents.remove(pageSource.getPageClass());
        }
    }

}
