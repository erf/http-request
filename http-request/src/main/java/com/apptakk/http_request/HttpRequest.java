package com.apptakk.http_request;

import java.io.IOException;
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

    public HttpResponse request()  {

        HttpResponse response = new HttpResponse();

        HttpURLConnection con;
        try {
            con = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return response;
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
                IO.write(con.getOutputStream(), json);
            }

            response.code = con.getResponseCode();
            response.body = IO.read(con.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return response;
    }
}
