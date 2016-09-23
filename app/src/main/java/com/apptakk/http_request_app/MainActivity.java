package com.apptakk.http_request_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);

        Log.d("http-request", "sending request");
        final String[] text = {""};

        // test handle
        new HttpRequestTask(
                new HttpRequest("http://httpbin.org/get", HttpRequest.GET),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        text[0] += "HTTP GET -> http://httpbin.org/get\n" + response.body + "\n\n";
                        textView.setText(text[0]);
                    }
                }).execute();

        new HttpRequestTask(
                new HttpRequest("http://httpbin.org/post", HttpRequest.POST, "{ \"post\": \"some-data-æøå\" }" ),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        if (response.code == 200) {
                            text[0] += "HTTP POST -> http://httpbin.org/post\n" + response.body + "\n\n";
                            textView.setText(text[0]);
                        }
                    }
                }, this).execute();
    }
}
