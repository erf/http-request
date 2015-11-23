package com.apptakk.http_request;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpRequest {

    public final static String OPTIONS = "OPTIONS";
    public final static String GET = "GET";
    public final static String HEAD = "HEAD";
    public final static String POST = "POST";
    public final static String PUT = "PUT";
    public final static String DELETE = "DELETE";
    public final static String TRACE = "TRACE";

    private final String url;
    private final String method;
    private final String json;
    private final Map<String, String> requestProperties;

    public HttpRequest(String url, String method) {
        this(url, method, null, null);
    }

    public HttpRequest(String url, String method, String json) {
        this(url, method, json, null);
    }

    public HttpRequest(String url, String method, String json, Map<String, String> requestProperties) {
        this.url = url;
        this.method = method;
        this.json = json;
        this.requestProperties = requestProperties;
    }

    public static void write(OutputStream os, String body) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(body);
        writer.flush();
        writer.close();
        os.close();
    }

    public static String read(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public HttpResponse request()  {

        HttpResponse httpResponse = new HttpResponse();

        HttpURLConnection con;
        try {
            con = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return httpResponse;
        }

        try {

            if(method != null){
                con.setRequestMethod(method);
            }

            if(requestProperties != null) {
                for (Map.Entry<String, String> entry : requestProperties.entrySet()) {
                    con.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if(json != null){
                con.setDoOutput(true);
                con.setChunkedStreamingMode(0);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Content-Length", "" + json.length());
                con.setRequestProperty("Accept", "application/json");
                write(con.getOutputStream(), json);
            }

            httpResponse.code = con.getResponseCode();
            httpResponse.body = read(con.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return httpResponse;
    }
}
