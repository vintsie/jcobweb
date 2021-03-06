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

/**
 * User: dev001
 * Date: 12/24/13
 * Time: 12:34 PM
 */
public class LocalServiceInvoker extends IServiceInvoker {

    //private transient static Log log = LogFactory.getLog(LocalServiceInvoker.class);

    public LocalServiceInvoker() {
        setCallType(callType_Local);
    }

    @Override
    public Object instanceServiceObject(Class<?> clazz) throws Exception {
        return clazz.newInstance();
    }

    @Override
    public Object instanceServiceObject(String className) throws Exception {
        return this.instanceServiceObject(Class.forName(className));
    }


}
