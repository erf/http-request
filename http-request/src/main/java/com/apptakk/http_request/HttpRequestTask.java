package com.apptakk.http_request;

import android.os.AsyncTask;

public class HttpRequestTask extends AsyncTask<Void, Void, HttpResponse> {

    private HttpRequest httpRequest;
    private ITaskComplete onTaskComplete;

    public HttpRequestTask(HttpRequest httpRequest, ITaskComplete onTaskComplete) {
        this.httpRequest = httpRequest;
        this.onTaskComplete = onTaskComplete;
    }

    @Override
    protected HttpResponse doInBackground(Void... params) {
        return httpRequest.request();
    }

    @Override
    protected void onPostExecute(final HttpResponse response) {
        if(onTaskComplete != null)
            onTaskComplete.handle(response);
    }

    @Override
    protected void onCancelled() {
        if(onTaskComplete != null)
            onTaskComplete.handle(new HttpResponse());
    }
}
