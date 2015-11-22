package com.apptakk.http_request_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://httpbin.org/ip";
        new HttpRequestTask(new HttpRequest(url, HttpRequest.GET, null, null),
                null,
                new HttpRequestTask.OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(HttpResponse response) {
                        TextView textView = (TextView) findViewById(R.id.text);
                        if(response.code==200) {
                            Log.d("http-request", response.body);
                            textView.setText(response.body);
                        } else {
                            Log.d("http-request", "error with code: " + response.code);
                            textView.setText("error with code: " + response.code);
                        }
                    }
                }).execute();

    }
}
