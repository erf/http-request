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
compile 'com.apptakk.http_request:http-request:0.0.7'
```

# Example
See app example or copy code below
```
String url = "https://httpbin.org/user-agent";
new HttpRequestTask(new HttpRequest(url, HttpRequest.GET, null, null),
                null,
                new HttpRequestTask.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(HttpResponse response) {
                TextView textView = (TextView) findViewById(R.id.hello);
                textView.setText(response.body);
            }
        }).execute();
```

