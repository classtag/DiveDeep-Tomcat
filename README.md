Dive Deep Tomcat
================
> This repository is a part of **Dive Deep Open Source Series**.

Step by step to implement an HTTP server that functions like tomcat, starting with the simplest implementation.

# Version 3.0

# Version 2.0

# Version 1.0
The simplest HttpServer implementation. [Code at Here](https://github.com/classtag/DiveDeep-Tomcat/tree/master/src/main/java/me/anduo/dive/deep/tomcat/v1)

Just implement a HttpServer with a `SocketServer` accept http request and response a html page.
When you tape and finish the site address, then the browser will show a html page.

Main process steps:
1. Create a `SocketServer` with a specific port (like 9999 or 8080).
2. Continuously accept requests from client in a while loop.
    ```java
    client = serverSocket.accept();
    ```
3. Read the request message from ```client.getInputStream()```
4. Do some logic process use the request message.
5. Get the ```client.getOutputStream()``` waiting to read response message for client.
6. Write and flush the OutputStream, it means let the client can see the response page.
7. close the OutputStream, InputStream and client.



> Wish you will learn more from this repository.

Please contract me(anduo@qq.com) if you want to discuss more about these code.
