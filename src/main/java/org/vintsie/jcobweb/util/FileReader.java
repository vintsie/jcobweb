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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URI;

/**
 * <p>Read the original content of the file, without any extra operations,
 * such as trimming the blank content.</p>
 *
 * User: dev001
 * Date: 12/24/13
 * Time: 12:50 PM
 */
public abstract class FileReader {

    private transient static Log log = LogFactory.getLog(FileReader.class);

    /**
     * Get the original content of
     *
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

    /**
     * <p>
     * Read contents of a text file packaged in Jar. {@link java.io.IOException}
     * will be thrown out while error occurred in file reading.</p>
     *
     * <p>
     *     Param <code>contextPath</code> means the relative path. For Example,
     *     reading jcobweb.xml, which is placed in the root directory of the
     *     jar file, the contextPath parameter should be "/jcobweb.xml".
     *
     * </p>
     *
     * @param contextPath context path of file
     * @return the content of file.
     * @throws IOException
     */
    public static String readJarTextFile(String contextPath) throws IOException {

        InputStream is = FileReader.class.getResourceAsStream(contextPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuilder sb = new StringBuilder();
        String s;
        try {
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException ioe) {
            log.error("Failed in reading file " + contextPath, ioe);
            throw ioe;
        }
        try{
            br.close();
            is.close();
        } catch (IOException ioe){
            log.error("Failed in closing the InputStream after reading.", ioe);
        }
        return sb.toString();
    }


}
