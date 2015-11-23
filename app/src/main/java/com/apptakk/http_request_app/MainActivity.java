package com.apptakk.http_request_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;
import com.apptakk.http_request.ITaskComplete;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);

        new HttpRequestTask(
                new HttpRequest("http://httpbin.org/ip", HttpRequest.GET),
                new ITaskComplete() {
                    @Override
                    public void handle(HttpResponse response) {
                        if (response.code == 200) {
                            textView.setText(response.body);
                        }
                    }
                }).execute();
    }
}
