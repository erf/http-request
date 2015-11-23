# http-request

A minimal Android library to simplify http requests.

# How to

Until library is on jcenter use
```
repositories {
    maven { url "http://dl.bintray.com/erf/maven" }
}
```

Add gradle dependency
```
compile 'com.apptakk.http_request:http-request:0.0.8'
```

# Example
See app example or copy code below
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

