# http-request
A minimal Android library to simplify http requests.

# Install
Use Gradle
```
repositories {
    maven { url "http://dl.bintray.com/erf/maven" }
}

compile 'com.apptakk.http_request:http-request:0.0.8'
```

# Usage
See app example or do
```
    String url = "http://httpbin.org/ip";
    new HttpRequestTask(new HttpRequest(url, HttpRequest.GET, null, null),
            new HttpRequestTask.OnTaskCompleted() {
                @Override
                public void onTaskCompleted(HttpResponse response) {
                    TextView textView = (TextView) findViewById(R.id.text);
                    if(response.code==200) {
                        textView.setText(response.body);
                    } else {
                        textView.setText("Request error");
                    }
                }
            }, null).execute();
```

