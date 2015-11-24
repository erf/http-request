http-request
------------
A minimal Android library for http requests

# Usage
#### `GET` example
```
new HttpRequestTask(
    new HttpRequest("http://httpbin.org/user-agent", HttpRequest.GET),
    new ITaskComplete() {
        @Override
        public void handle(HttpResponse response) {
            if (response.code == 200) {
                textView.setText(response.body);
            }
        }
    }).execute();
```

#### `POST` example
```
new HttpRequestTask(
        new HttpRequest("http://httpbin.org/post", HttpRequest.POST,
                "{ \"post\": \"some-data\" }" ),
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
Use Gradle
```
repositories {
    jcenter()
}

compile 'com.apptakk.http_request:http-request:0.0.11'
```
