package com.apptakk.http_request;

import android.os.AsyncTask;

public class HttpRequestTask extends AsyncTask<Void, Void, HttpResponse> {

    public interface OnTaskCompleted {
        void onTaskCompleted(HttpResponse response);
    }

    public interface OnProgress {
        void showProgress(boolean show);
    }

    private HttpRequest httpRequest;
    private OnTaskCompleted onTaskCompleted;
    private OnProgress onProgress;

    public HttpRequestTask(HttpRequest httpRequest, OnTaskCompleted onTaskCompleted, OnProgress onProgress) {
        this.httpRequest = httpRequest;
        this.onTaskCompleted = onTaskCompleted;
        this.onProgress = onProgress;
    }

    @Override
    protected void onPreExecute() {
        if(onProgress != null)
            onProgress.showProgress(true);
    }

    @Override
    protected HttpResponse doInBackground(Void... params) {
        return httpRequest.request();
    }

    @Override
    protected void onPostExecute(final HttpResponse response) {

        if(onProgress != null)
            onProgress.showProgress(false);

        if(onTaskCompleted != null)
            onTaskCompleted.onTaskCompleted(response);
    }

    @Override
    protected void onCancelled() {

        if(onProgress != null)
            onProgress.showProgress(false);

        if(onTaskCompleted != null)
            onTaskCompleted.onTaskCompleted(new HttpResponse());
    }
}
