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
    private OnProgress onProgress;
    private OnTaskCompleted callback;

    public HttpRequestTask(HttpRequest httpRequest, HttpRequestTask.OnProgress onProgress, OnTaskCompleted callback) {
        this.httpRequest = httpRequest;
        this.onProgress = onProgress;
        this.callback = callback;
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

        if(callback != null)
            callback.onTaskCompleted(response);
    }

    @Override
    protected void onCancelled() {

        if(onProgress != null)
            onProgress.showProgress(false);

        if(callback != null)
            callback.onTaskCompleted(new HttpResponse());
    }
}
