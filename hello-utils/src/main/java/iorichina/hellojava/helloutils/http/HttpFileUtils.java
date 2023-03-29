package iorichina.hellojava.helloutils.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by iorihuang on 2017/1/14.
 */
public class HttpFileUtils {
    /**
     * The default buffer size to use.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static long downloadTo(String url, String filename) throws IOException {
        URL httpUrl = new URL(url);

        File file = new File(filename);
        file.deleteOnExit();

        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && parent.exists() == false) {
                if (parent.mkdirs() == false) {
                    throw new IOException("File '" + file + "' could not be created");
                }
            }
        }


        FileOutputStream output = null;
        try {
            output = new FileOutputStream(file);
            InputStream input = httpUrl.openStream();

            try {
                byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                long count = 0;
                int n = 0;
                while (-1 != (n = input.read(buffer))) {
                    output.write(buffer, 0, n);
                    count += n;
                }
                return count;
            } finally {
                if (input != null) {
                    input.close();
                }
            }
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}
