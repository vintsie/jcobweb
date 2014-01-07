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

import org.apache.commons.lang3.StringUtils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * User: dev001
 * Date: 12/26/13
 * Time: 2:38 PM
 */
public abstract class AbtractServiceStub extends UnicastRemoteObject implements IServiceStub {
    //public static String REST_out_empty = "";

    /**
     * Handle RemoteException when service stub is constructed.
     *
     * @throws java.rmi.RemoteException
     */
    public AbtractServiceStub() throws RemoteException{
        super();
    }

    /**
     * add REST interface support to make life easier.
     *
     * @param REST_in   RESTful param
     * @param REST_type define RESTful type, xml or json
     *
     * @return RESTful return
     * @throws java.rmi.RemoteException
     */
    public String remoteInvoke(String REST_in, String REST_type) throws RemoteException{
        if(StringUtils.equals(REST_type, "xml")){
            return remoteInvokeXmlRestfully(REST_in);
        }

        if(StringUtils.equals(REST_type, "json")){
            return remoteInvokeJsonRestfully(REST_in);
        }
        return  StringUtils.EMPTY;
    }

    /**
     * call Xml-RESTful handler
     *
     * @param xml   REST_in
     * @return  RESTful result
     *
     * @throws java.rmi.RemoteException
     */
    public String remoteInvokeXmlRestfully(String xml) throws RemoteException{
        return StringUtils.EMPTY;
    }

    /**
     * call Json-RESTful handler
     *
     * @param jsonStr   REST_in
     * @return  REST result
     *
     * @throws java.rmi.RemoteException
     */
    public String remoteInvokeJsonRestfully(String jsonStr) throws RemoteException{
        return StringUtils.EMPTY;
    }

}
