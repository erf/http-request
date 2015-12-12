package com.apptakk.http_request;

import android.os.AsyncTask;

public class HttpRequestTask extends AsyncTask<Void, Void, HttpResponse> {

    private final HttpRequest httpRequest;
    private final HttpRequest.Handler handler;

    public HttpRequestTask(HttpRequest httpRequest, HttpRequest.Handler taskComplete) {
        this.httpRequest = httpRequest;
        this.handler = taskComplete;
    }

    @Override
    protected HttpResponse doInBackground(Void... params) {
        return httpRequest.request();
    }

    @Override
    protected void onPostExecute(final HttpResponse response) {
        if(handler != null)
            handler.response(response);
    }

    @Override
    protected void onCancelled() {
        if(handler != null)
            handler.response(new HttpResponse());
    }
}
