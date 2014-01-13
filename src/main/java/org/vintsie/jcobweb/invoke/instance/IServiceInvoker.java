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
package org.vintsie.jcobweb.invoke.instance;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vintsie.jcobweb.config.I18nFactory;

/**
 * <p>The IServiceInvoker interface is designed to designate a class
 * as a <em>IServiceInvoker</em> object.</p>
 * <p/>
 * User: dev001
 * Date: 12/23/13
 * Time: 5:19 PM
 */
public abstract class IServiceInvoker {

    public final static String callType_Local = "_L";
    public final static String callType_Remote = "_R";

    // set call type do default value
    public String callType = callType_Local;

    protected final Log log = LogFactory.getLog(getClass());

    /**
     * get service invoke type, local or remote
     *
     * @return _L:local _R:remote
     */
    //protected String getCallType() {
    //    return this.callType;
    //}

    /**
     * set service call type, the param defined
     * must be _L or _R,
     *
     * @param callType service call type
     */
    protected void setCallType(String callType) {
        if (StringUtils.equals(callType, callType_Local) ||
                StringUtils.equals(callType, callType_Remote)) {
            this.callType = callType;
        } else
            throw new RuntimeException(
                    I18nFactory.getI18nInfo("unknown_call_type", callType));
    }

    /**
     * @param clazz Class.forName(className)
     * @return new instance of clazz
     */
    public abstract Object instanceServiceObject(Class<?> clazz) throws Exception;

    /**
     * @param className class name
     * @return new instance of className
     */
    public abstract Object instanceServiceObject(String className) throws Exception;


}
