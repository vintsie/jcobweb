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

package org.vintsie.jcobweb.util;

import java.io.*;
import java.net.URI;

/**
 * 文件读取工具虚类，提供文件读取最原始的操作。
 *
 * User: dev001
 * Date: 12/24/13
 * Time: 12:50 PM
 */
public abstract class FileReader {


    /**
     * 读取文件的原始内容，不做任务处理。如果文件在读取过程中出现
     * 读取错误会抛出IOException。
     *
     * @param uri       file location
     * @param encoding  file encoding
     * @return  file content String
     * @exception java.io.IOException
     */
    public static String readFile(URI uri, String encoding)
            throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(new File(uri)), encoding
        ));
        StringBuilder sb = new StringBuilder();
        String tmp;
        while ((tmp = br.readLine()) != null) {
            sb.append(tmp);
        }
        br.close();
        return sb.toString();
    }


}
