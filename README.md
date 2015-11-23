# http-request
A minimal Android library for http requests.

# Install
Use Gradle
```
repositories {
    maven { url "http://dl.bintray.com/erf/maven" }
}

compile 'com.apptakk.http_request:http-request:0.0.10'
```

# Usage
See app example or do
```
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
```

