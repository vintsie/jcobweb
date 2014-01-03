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

import org.vintsie.jcobweb.invoke.stub.IServiceStub;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Remote service invoker
 *
 * User: dev001
 * Date: 12/26/13
 * Time: 1:16 PM
 */
public class RemoteServiceInvoker extends IServiceInvoker {


    /**
     * set the call type of service invoke to
     * <code>IServiceInvoker.callType_Remote</code>.
     */
    public RemoteServiceInvoker(){
        setCallType(callType_Remote);
    }

    @Override
    public Object instanceServiceObject(Class<?> clazz) throws Exception {
        return instanceServiceObject("");
    }

    @Override
    public Object instanceServiceObject(String className) throws Exception {

        // local rmi server
        Registry registry = LocateRegistry.getRegistry("10.11.16.60", 9090);
        IServiceStub remoteStub = (IServiceStub)registry.lookup(IServiceStub.class.getName());

        if(log.isInfoEnabled()){
            log.info("Remote Service stub will return, wrapped by " + className);
        }
        return remoteStub;
    }
}
