
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
package org.ops4j.pax.wicket.spi.springdm.injection.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;
public abstract class AbstractSpringBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    /** {@inheritDoc} */
    @Override
    public void doParse(Element element, BeanDefinitionBuilder builder) {
        builder.addPropertyReference("bundleContext", "bundleContext");
        prepareInjection(element, builder);
        builder.setLazyInit(false);
        builder.setInitMethodName("start");
        builder.setDestroyMethodName("stop");
        super.doParse(element, builder);
    }

    /**
     * <p>prepareInjection.</p>
     *
     * @param element a {@link org.w3c.dom.Element} object.
     * @param bean a {@link org.springframework.beans.factory.support.BeanDefinitionBuilder} object.
     */
    protected abstract void prepareInjection(Element element, BeanDefinitionBuilder bean);

    /**
     * <p>addPropertyValueFromElement.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param element a {@link org.w3c.dom.Element} object.
     * @param bean a {@link org.springframework.beans.factory.support.BeanDefinitionBuilder} object.
     */
    protected void addPropertyValueFromElement(String id, Element element, BeanDefinitionBuilder bean) {
        String beanElement = element.getAttribute(id);
        bean.addPropertyValue(id, beanElement);
    }

    /**
     * <p>addPropertyValueFromElement.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param injectionId a {@link java.lang.String} object.
     * @param element a {@link org.w3c.dom.Element} object.
     * @param bean a {@link org.springframework.beans.factory.support.BeanDefinitionBuilder} object.
     */
    protected void addPropertyValueFromElement(String id, String injectionId, Element element,
            BeanDefinitionBuilder bean) {
        String beanElement = element.getAttribute(id);
        bean.addPropertyValue(injectionId, beanElement);
    }

    /**
     * <p>addPropertyReferenceFromElement.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param element a {@link org.w3c.dom.Element} object.
     * @param bean a {@link org.springframework.beans.factory.support.BeanDefinitionBuilder} object.
     */
    protected void addPropertyReferenceFromElement(String id, Element element, BeanDefinitionBuilder bean) {
        String beanElement = element.getAttribute(id);
        bean.addPropertyReference(id, beanElement);
    }

}
