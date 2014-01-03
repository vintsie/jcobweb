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
     * read xml file
     *
     * @param uri       file location
     * @param encoding  file encoding
     * @return  formatted file content
     * @throws java.io.IOException
     */
    public static String readXmlFile(URI uri, String encoding) throws IOException {
        return readFile(uri, encoding).replaceAll("(?<=>)\\s+(?=<)", "");
    }

}
