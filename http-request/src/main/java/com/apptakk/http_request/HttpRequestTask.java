package com.apptakk.http_request;

import android.os.AsyncTask;

public class HttpRequestTask extends AsyncTask<Void, Void, HttpResponse> {

    private final HttpRequest httpRequest;
    private final ITaskComplete taskComplete;

    public HttpRequestTask(HttpRequest httpRequest, ITaskComplete taskComplete) {
        this.httpRequest = httpRequest;
        this.taskComplete = taskComplete;
    }

    @Override
    protected HttpResponse doInBackground(Void... params) {
        return httpRequest.request();
    }

    @Override
    protected void onPostExecute(final HttpResponse response) {
        if(taskComplete != null)
            taskComplete.handle(response);
    }

    @Override
    protected void onCancelled() {
        if(taskComplete != null)
            taskComplete.handle(new HttpResponse());
    }
}
