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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * User: dev001
 * Date: 12/26/13
 * Time: 2:02 PM
 */
public class RmiServiceStub extends AbtractServiceStub {

    /**
     * <p>This class implements the Serializable interface,
     * but does not define a serialVersionUID field.  A
     * change as simple as adding a reference to a .class
     * object will add synthetic fields to the class, which
     * will unfortunately change the implicit serialVersionUID
     * (e.g., adding a reference to String.class will generate
     * a static field class$java$lang$String). Also, different
     * source code to bytecode compilers may use different naming
     * conventions for synthetic variables generated for references
     * to class objects or inner classes. To ensure interoperability
     * of Serializable across versions, consider adding an
     * explicit serialVersionUID.</p>
     * <p/>
     * <p>Checked by findbugs</p>
     */
    private final static long serialVersionUID = 9779392394L;

    private transient static Log log = LogFactory.getLog(RmiServiceStub.class);

    public RmiServiceStub() throws RemoteException {
        super();
    }


    @Override
    public Object remoteInvoke(String ifc, String method, Class<?>[] argTypes, Object[] args) throws RemoteException {
        Object rtn = null;
        try {
            Class<?> _interface_class = Class.forName(ifc);
            Object obj = ServiceFactory.getService(_interface_class);
            Method _m =
                    _interface_class.getDeclaredMethod(method, argTypes);
            rtn = _m.invoke(obj, args);

            if (log.isInfoEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append("\n").append("-------------Service Call Info---------\n");
                sb.append("interface:").append(ifc).append("\n");
                sb.append("method:").append(method).append("\n");

                sb.append("parameter types:").append(
                        Arrays.toString(_m.getParameterTypes())).append("\n");

                sb.append("args:").append(Arrays.toString(args)).append("\n");

                log.info(sb.toString());
            }

        } catch (Exception e) {
            log.error("---", e);
        }
        return rtn;
    }

    @Override
    public Object remoteInvoke(HashMap<String, Object> params) throws RemoteException {
        return null;
      /*
        if(null == params || params.size() < 2){
            throw new RemoteException("Invalid request parameters. " +
                    "Service class name and method are needed at least.");
        }

        String _interface = (String)params.get("_interface");
        String _method = (String) params.get("_method");
        ArrayList args = (ArrayList) params.get("_args");
        ArrayList _args_clazz = (ArrayList)params.get("_args_clazz");
        Object rtn = null;

        try{
            Class<?> _interface_class = Class.forName(_interface);
            Object obj = ServiceFactory.getService(_interface_class);
            //Class.forName(_interface)
            Method method =
                    _interface_class.getDeclaredMethod(_method, _args_clazz.toArray(Arrays.));
            rtn = method.invoke(obj, args.toArray());

        } catch (Exception e){
            log.error("--", e);
        }
        if(log.isInfoEnabled()){
            StringBuilder sb = new StringBuilder();
            sb.append("\n").append("-------------Service Call Info---------");
            sb.append("interface:").append(_interface).append("\n");
            sb.append("method:").append(_method).append("\n");
            sb.append("args:").append(args.toString());
            log.info(sb.toString());
        }
        return rtn;
        */
    }
}
