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


import java.io.IOException;
import java.net.URI;

/**
 * User: dev001
 * Date: 12/24/13
 * Time: 12:50 PM
 */
public class XmlReader extends FileReader {

    /**
     * 读取XML文件的内容。首先使用基类{@link FileReader}的readFile
     * 方法获取最原始的文件内容，然后把对于XML没有意义的字符删掉。
     *
     * @param uri      file location
     * @param encoding file encoding
     * @return formatted file content
     * @throws java.io.IOException
     */
    public static String readXmlFile(URI uri, String encoding) throws IOException {
        return readFile(uri, encoding).replaceAll("(?<=>)\\s+(?=<)", "");
    }

    /**
     * <p>Read contents of a xml file packaged in Jar. {@link java.io.IOException}
     * will be thrown out while error occurred in file reading.</p>
     * <p/>
     * <p>
     * Param <code>contextPath</code> means the relative path. For Example,
     * reading jcobweb.xml, which is placed in the root directory of the
     * jar file, the contextPath parameter should be "/jcobweb.xml".
     * <p/>
     * </p>
     *
     * @param contextPath the file context path
     * @param charsetName charset encoding
     * @return the file content
     * @throws IOException
     */
    public static String readJarXmlFile(String contextPath, String charsetName) throws IOException {
        return readJarTextFile(contextPath, charsetName).replace("(?<=>)\\s+(?=<)", "");
    }

}
