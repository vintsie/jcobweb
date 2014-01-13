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

package org.vintsie.jcobweb.invoke;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vintsie.jcobweb.config.I18nFactory;
import org.vintsie.jcobweb.invoke.instance.IServiceInvoker;
import org.vintsie.jcobweb.invoke.stub.IServiceStub;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: dev001
 * Date: 12/23/13
 * Time: 5:20 PM
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private transient static Log log = LogFactory.getLog(ProxyInvocationHandler.class);

    private Object instance;
    private Class iClass;

    public ProxyInvocationHandler(Object instance, Class<?> iClass) {
        this.instance = instance;
        this.iClass = iClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("toString".equals(method.getName())) {
            return "Proxy@" + iClass.getName();
        }

        Object rtn = null;

        if (IServiceInvoker.callType_Local.equals(ServiceFactory.getServiceInvokeType())) {
            if (log.isDebugEnabled()) {
                log.debug(I18nFactory.getI18nInfo("invoke_information", method.getName(),
                        Arrays.toString(method.getParameterTypes()),
                        this.iClass.getName(), Arrays.toString(args)));
            }
            rtn = method.invoke(this.instance, args);
        }
        if (IServiceInvoker.callType_Remote.equals(ServiceFactory.getServiceInvokeType())) {
            IServiceStub remoteInstance = (IServiceStub) instance;
            rtn = remoteInstance.remoteInvoke(
                    this.iClass.getName(), method.getName(), method.getParameterTypes(), args);
        }

        return rtn;
    }
}
