http-request
============

A minimal Android library for http requests

**Supported Android versions**: Android 1+

# Usage

`POST` example
```java
new HttpRequestTask(
        new HttpRequest("http://httpbin.org/post", HttpRequest.POST, "{ \"post\": \"some-data\" }" ),
        new ITaskComplete() {
            @Override
            public void handle(HttpResponse response) {
                if (response.code == 200) {
                    textView.setText(response.body);
                }
            }
        }).execute();
```

# Install
```groovy
compile 'com.apptakk.http_request:http-request:0.0.12'
```
