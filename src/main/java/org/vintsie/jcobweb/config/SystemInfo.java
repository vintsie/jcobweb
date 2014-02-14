/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vintsie.jcobweb.config;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.vintsie.jcobweb.util.XmlReader;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Initialize system information while process starts.
 * <p/>
 * Created by dev001 on 1/4/14.
 */
public class SystemInfo {


    /**
     * Constructor's invoke is banned.
     */
    private SystemInfo() {
    }

    private transient static Log log = LogFactory.getLog(SystemInfo.class);
    /**
     * A container reserved to store the extra properties.
     */
    private final static HashMap<String, Object> props = new HashMap<String, Object>();

    /**
     * System language environment mark.
     */
    private static String language = "";

    /**
     * Service Invoker which is configured in /jcobweb.xml file
     * and must extend from
     * {@link org.vintsie.jcobweb.invoke.instance.IServiceInvoker}
     */
    private static String srvInvoker = "";

    /**
     * Get property from the extra property container.
     *
     * @param key the key referenced to the property
     * @return property referenced to the arg {@code key}
     */
    public static Object get(String key) {
        return props.get(key);
    }

    /**
     * get system language environment mark.
     *
     * @return system language
     */
    public static String getLanguage() {
        return language;
    }

    /**
     * get service invoke class name.
     *
     * @return Service Invoker class name
     */
    public static String getSrvInvoker() {
        return srvInvoker;
    }

    /**
     * This operation is reserved or not, which is still in determination.
     *
     * @param key   the reference to the property
     * @param value the property
     */
    public static void put(String key, Object value) {
        synchronized (props) {
            props.put(key, value);
        }
    }


    static {
        try {

            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build(SystemInfo.class.getResourceAsStream("/jcobweb.xml"));

            XPathFactory xPathFactory = XPathFactory.instance();

            XPathExpression<Element> xp = xPathFactory.compile("/system/service/invoker", Filters.element());
            Element invoker =  xp.evaluateFirst(doc);
            //List<Attribute> list = xp.evaluate(doc);
            //log.error(list);

            if (null == invoker) {
                throw new RuntimeException(
                        I18nFactory.getI18nInfo("failed_in_parsing_sys_srv_invoker"));
            }
            srvInvoker = invoker.getValue();

            xp = xPathFactory.compile("/system/language", Filters.element());
            Element lan = xp.evaluateFirst(doc);
            if (null == lan) {
                throw new RuntimeException(I18nFactory.getI18nInfo("failed_in_parsing_sys_lan"));
            }
            language = lan.getValue();


        } catch (IOException ioe) {
            log.error(I18nFactory.getI18nInfo("reading_sys_info_error_io"), ioe);
        } catch (JDOMException jdome) {
            log.error(I18nFactory.getI18nInfo("reading_sys_info_error_doc"), jdome);
        }

    }


}
