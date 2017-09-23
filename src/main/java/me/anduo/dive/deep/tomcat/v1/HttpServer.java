package me.anduo.dive.deep.tomcat.v1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A very sample http server implements accept http request then response a html page.
 */
public class HttpServer {

    public static void main(String[] args) {
        int port = 9999;
        ServerSocket serverSocket = null;
        Socket client = null;
        try {
            // Initialize the ServerSocket which can accept the tcp request.
            serverSocket = new ServerSocket(port);
            System.out.println(String.format("HttpServer initialize finished, port:[%d]", port));
            while (!Thread.interrupted()) {// Continue to receive client links.
                client = serverSocket.accept(); // The server-side block waits for a Socket link from the client.
                // start to parse the http request //
                // A processing of request information inside the client
                InputStream is = client.getInputStream();
                // Defining a read buffer pool is mainly to read byte byte arrays in the InputStream stream to take these bytes.
                byte[] buff = new byte[1024];
                int len = is.read(buff);
                if (len <= 0) {
                    closeClientInputStream(client, is);
                    continue;
                }
                // Converts the read byte information into plaintext information
                String msg = new String(buff, 0, len);
                System.out.println(String.format("Request Message from Client:\n%s", msg));

                // You immediately need to respond to the information
                // that you need to export the stream so that you get the output stream from our client.

                // response http request //
                OutputStream os = client.getOutputStream(); // get output stream from client.
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // build a html page response msg
                StringBuffer sb = new StringBuffer();
                // append the html header
                sb.append("HTTP/1.1 200 OK\n");
                sb.append("Content-Type: text/html;charset=UTF-8\n");
                sb.append("\r\n");
                // append the html body
                sb.append("<html><head><title>HttpServer Version 1.0</title></head><body>Current DateTime：");
                sb.append("<font size='14' color='blue'>");
                sb.append(sdf.format(new Date()));
                sb.append("</font>");
                sb.append("<br/>Server Response：<font size='14' color='blue'>Hello HttpServer.</font></body></html>");

                // write the html page to output stream.
                os.write(sb.toString().getBytes(StandardCharsets.UTF_8));
                //===================================================
                flushCloseOutputStream(os);
                closeClientInputStream(client, is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            shutdown(serverSocket, client);
        }
    }

    private static void flushCloseOutputStream(OutputStream os) throws IOException {
        os.flush();
        os.close();
    }

    private static void closeClientInputStream(Socket client, InputStream is) throws IOException {
        is.close();
        client.close();
    }

    /**
     * Shutdown
     *
     * @param serverSocket
     * @param client
     */
    private static void shutdown(ServerSocket serverSocket, Socket client) {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
