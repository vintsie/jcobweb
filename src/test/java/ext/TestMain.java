package ext;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 *
 * Created by Vin on 14-4-9.
 */
public class TestMain {

    @Test
    public void writeAFile() throws Exception{
        //File file = new File("f:/bigFile.0001");
        //file.
        BufferedOutputStream bos =
                new BufferedOutputStream(new FileOutputStream("f:/bigFile.0001"));
        bos.write("Hello world!".getBytes("GBK"));
        bos.flush();
        bos.close();
    }
}
