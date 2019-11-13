package no.kristiania.Http;

import java.io.IOException;

import java.net.Socket;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpClient{

    private final String hostname;
    private final int port;
    private final String requestTarget;
    private String body;
    private Map<String, String> headers = new HashMap<>();

    public HttpClient(String hostname, int port, String requestTarget) {

        this.hostname = hostname;
        this.port = port;
        this.requestTarget = requestTarget;
        setRequestHeader("Host",hostname );
        setRequestHeader("Connetciton","close");
    }


    public HttpClientResponse execute()throws IOException {
        Socket socket = new Socket(hostname,port);

        String headerString = headers.entrySet().stream()
                .map(e -> e.getKey() + ":" + e.getValue())
                .collect(Collectors.joining("\r\n"));


        socket.getOutputStream().write(("GET " + requestTarget + " HTTP/1.1\r\n" +
                headerString+
                "\r\r\n\r\n").getBytes());
        socket.getOutputStream().flush();


        return new HttpClientResponse(socket.getInputStream());
    }

    public void setRequestHeader(String headerName, String headerValue) {
        headers.put(headerName,headerValue);
    }


    public void setBody(String body) {
        this.body = body;
    }
}
