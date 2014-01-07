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
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.vintsie.jcobweb.util.XmlReader;

import java.io.IOException;
import java.util.HashMap;

/**
 * Initialize system information while process starts.
 *
 * Created by dev001 on 1/4/14.
 */
public class SystemInfo{


    /**
     * Constructor's invoke is banned.
     */
    private SystemInfo(){}

    private transient static Log log = LogFactory.getLog(SystemInfo.class);
    /**
     * A container reserved to store the extra properties.
     */
    private final static HashMap<String, Object> props = new HashMap<String, Object>();

    /**
     *  Service Invoker which is configured in /jcobweb.xml file
     *  and must extend from
     *  {@link org.vintsie.jcobweb.invoke.instance.IServiceInvoker}
     */
    private static String srvInvoker = "";

    /**
     * Get property from the extra property container.
     *
     * @param key   the key referenced to the property
     * @return  property referenced to the arg {@code key}
     */
    public static Object get(String key){
        return props.get(key);
    }

    /**
     * This operation is reserved or not, which is still in determination.
     * @param key       the reference to the property
     * @param value     the property
     */
    public static void put(String key, Object value){
        synchronized (props){
            props.put(key, value);
        }
    }


    static{
        try{
            String systemInfo = XmlReader.readJarXmlFile("/jcobweb.xml", CharEncoding.UTF_8);
            Document doc = DocumentHelper.parseText(systemInfo);
            Node invoker = doc.selectSingleNode("/system/service/invoker");

            if (null == invoker) {
                throw new RuntimeException("Failed in parsing system configuration file.");
            }
        } catch (IOException ioe){



        } catch (DocumentException de){

        }

    }


}
