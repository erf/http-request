http-request
============

A minimal http request library for Android

**Supported Android versions**: Android 3+

# Usage

`POST` example
```java
new HttpRequestTask(
        new HttpRequest("http://httpbin.org/post", HttpRequest.POST, "{ \"post\": \"some-data-æøå\" }" ),
        new HttpRequest.Handler() {
            @Override
            public void response(HttpResponse response) {
                if (response.code == 200) {
                    textView.setText(response.body);
                }
            }
        }).execute();
```

# Install
```groovy
compile 'com.apptakk.http_request:http-request:0.0.15'
```
