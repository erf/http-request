package com.apptakk.http_request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by erlend on 01/02/16.
 */
public class IO {

    public static void write(OutputStream os, byte[] body) throws IOException {
        os.write(body);
        os.close();
    }

    public static String read(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
            sb.append(line);
        br.close();
        return sb.toString();
    }
}
