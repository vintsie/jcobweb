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

package org.vintsie.jcobweb.proxy;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.vintsie.jcobweb.invoke.InvokeUtil;
import org.vintsie.jcobweb.invoke.ProxyInvocationHandler;
import org.vintsie.jcobweb.invoke.instance.IServiceInvoker;
import org.vintsie.jcobweb.invoke.instance.LocalServiceInvoker;
import org.vintsie.jcobweb.util.XmlReader;

import java.lang.reflect.Proxy;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: dev001
 * Date: 12/23/13
 * Time: 3:14 PM
 */
public class ServiceFactory {
    private transient static Log log = LogFactory.getLog(ServiceFactory.class);
    /**
     * Service Implementation will be instanced by this.
     */
    private static IServiceInvoker I_SERVICE_INVOKE;

    /**
     * New this factory is banned.
     */
    private ServiceFactory() {
    }

    /**
     * get service instance
     *
     * @param serviceInterface  service interface
     * @param <T>   a proxy instance of interface implementation
     * @return Type of the interface, a proxy instance of interface implementation
     */
    public static <T> T getService(Class<T> serviceInterface) throws Exception {

        String implClassName = InvokeUtil.getImplClassName(serviceInterface.getName());

        // create a new invocation handler
        ProxyInvocationHandler proxyInvokeHandler =
                new ProxyInvocationHandler(
                        I_SERVICE_INVOKE.instanceServiceObject(implClassName), serviceInterface);

        // create a proxy instance
        Object object = Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface}, proxyInvokeHandler);

        if (null == object)
            throw new RuntimeException("Error in creating proxy instance.");

        return serviceInterface.cast(object);
    }

    /**
     * 暴露服务调用实现类
     * @return IServiceInvoker
     * @throws Exception
     */
    public static IServiceInvoker getServiceInvoke() throws Exception{
        return I_SERVICE_INVOKE;
    }

    /**
     * 直接返回服务调用方式
     * @return  call type of service invoker
     * @throws Exception
     */
    public static String getServiceInvokeType() throws Exception{
        return getServiceInvoke().callType;
    }

    static {
        try {
            //URL fileLoc = ServiceFactory.class.getResource("/jcobweb.xml");
            String systemInfo = XmlReader.readJarXmlFile("/jcobweb.xml");
            Document doc = DocumentHelper.parseText(systemInfo);
            Node invoker = doc.selectSingleNode("/system/service/invoker");

            if (null == invoker) {
                throw new RuntimeException("Failed in parsing system configuration file.");
            }

            Class invokerClazz = Class.forName(invoker.getText());
            I_SERVICE_INVOKE = (IServiceInvoker) invokerClazz.newInstance();

        } catch (Exception e) {
            log.error("Failed in reading system configuration file, " +
                    "service invoker will work on local mode.", e);
            I_SERVICE_INVOKE = new LocalServiceInvoker();
        }

    }
}
