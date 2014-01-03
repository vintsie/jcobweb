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

package org.vintsie.jcobweb.framework;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vintsie.jcobweb.invoke.stub.IServiceStub;
import org.vintsie.jcobweb.invoke.stub.RmiServiceStub;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * User: dev001
 * Date: 12/26/13
 * Time: 4:03 PM
 */
public class RmiFrameWork {

    private transient static Log log = LogFactory.getLog(RmiFrameWork.class);


    /**
     * <p>
     * Server can be started by running this main function. The
     * Rmi Server's port is reset to 9090, instead of the default
     * value 1099.</p>
     *
     * <p>The Rmi Server Port should be set by the configuration
     * file. This is the continuing work rest.</p>
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        if(log.isInfoEnabled()){
            log.info("Set System Service Invoke Factory:" +
                    ServiceFactory.getServiceInvoke().getClass().getName());
            log.info("Set System Service Invoke Type:" + ServiceFactory.getServiceInvokeType());
        }
        Registry registry = LocateRegistry.createRegistry(9090);
        registry.rebind(IServiceStub.class.getName(), new RmiServiceStub());
        if(log.isInfoEnabled()){
            log.info("RMI server is ready.");
        }

    }

}
