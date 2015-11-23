# http-request
A minimal Android library for http requests.

# usage
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

# install
```
repositories {
    maven { url "http://dl.bintray.com/erf/maven" }
}

compile 'com.apptakk.http_request:http-request:0.0.11'
```
