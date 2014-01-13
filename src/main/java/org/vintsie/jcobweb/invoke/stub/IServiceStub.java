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
package org.vintsie.jcobweb.invoke.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Exposed to client for remote call.
 * <p/>
 * User: Vin Tsie
 * Date: 13-12-31
 * Time: 下午5:02
 */
public interface IServiceStub extends Remote {

    /**
     * Client's proxy use this method to call remote object's
     * service.
     *
     * @param params all parameters prepared for remote calling,
     *               include interface, method, args.
     * @return invoke result, just like regular function execution.
     * @throws RemoteException
     * @deprecated
     */
    public Object remoteInvoke(HashMap<String, Object> params) throws RemoteException;

    /**
     * Client's proxy use this method to call remote object's
     * service.
     *
     * @param ifc      interface class name
     * @param method   method name
     * @param argTypes method arguments' type
     * @param args     arguments
     * @return invoke result, just like regular function execution.
     * @throws RemoteException
     */
    public Object remoteInvoke(String ifc, String method, Class<?>[] argTypes, Object[] args)
            throws RemoteException, Exception;
}
