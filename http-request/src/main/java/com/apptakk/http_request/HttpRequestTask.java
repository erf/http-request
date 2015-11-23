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
    private OnTaskCompleted callback;
    private OnProgress onProgress;

    public HttpRequestTask(HttpRequest httpRequest, OnTaskCompleted callback, HttpRequestTask.OnProgress onProgress) {
        this.httpRequest = httpRequest;
        this.callback = callback;
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
