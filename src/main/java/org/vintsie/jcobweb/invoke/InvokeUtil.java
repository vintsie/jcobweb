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

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: dev001
 * Date: 12/23/13
 * Time: 3:11 PM
 */
public class InvokeUtil {


    /**
     * get impl-class name from interface class name.
     *
     * @param itfClsName interface class name
     * @return implementation class name
     */
    public static String getImplClassName(String itfClsName) {
        String[] parts = itfClsName.split(Pattern.quote("."));

        parts[parts.length - 2] = "impl";
        parts[parts.length - 1] += "Impl";

        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (sb.length() != 0) sb.append(".");
            sb.append(part);
        }

        return sb.toString();
    }

    /**
     * Get object type of an object array. This method is deprecated.
     * In my case, this method can be replaced by {@code Arrays.toString(Object[] args)}
     * , the args is an array of the object types.
     *
     * @param args Object[]
     * @return arg1Type, arg2Type...
     * @deprecated this method can be replaced by {@code Arrays.toString(Object[] args)}
     */
    public static String getMethodParamsTypes(Object[] args){
        if(null == args || args.length < 1)
            return "void";
        StringBuilder sb = new StringBuilder();
        for(Object arg : args){
            if(sb.length() > 0)
                sb.append(", ");
            sb.append(arg.getClass().getName());
        }
        return sb.toString();
    }

}
