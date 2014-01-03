package org.vintsie.jcobweb.util;

import java.io.*;
import java.net.URI;

/**
 * User: dev001
 * Date: 12/24/13
 * Time: 12:50 PM
 */
public class FileReader {


    /**
     * read file content without trimming.
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
