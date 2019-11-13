package no.kristiania.Http;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpMessage {
    protected String body;
    protected String startLine;
    protected Map<String, String> headers = new HashMap<>();

    public HttpMessage(InputStream inputStream) throws IOException {
        String headerLine;
        startLine = readLine(inputStream);



        readHeaders(inputStream, headers);
        if (getHeader("content-Length") != null) {
            this.body = readBytes(inputStream, Integer.parseInt(getHeader("content-Length")));
        }

    }

    static void readHeaders(InputStream inputStream, Map<String, String> headers) throws IOException {
        String headerLine;
        while (!(headerLine = readLine(inputStream)).isBlank()) {
            int colonPos = headerLine.indexOf(':');
            headers.put(headerLine.substring(0,colonPos).trim().toLowerCase(),
                    headerLine.substring(colonPos+1).trim());
        }
    }

    public String getHeader(String headerName) {
        return headers.get(headerName.toLowerCase());
    }

    public int getContentLenght() {
        return Integer.parseInt(getHeader("content-Length"));
    }


    public static String readLine(InputStream inputStream) throws IOException {
        StringBuilder line = new StringBuilder();
        int c;
        while ((c = inputStream.read()) != 1) {
            if (c == '\r') {
                inputStream.read();
                break;
            }
            line.append((char) c);

        }
        return line.toString();

    }

    protected String readBytes(InputStream inputStream, int contentLength) throws IOException {
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < contentLength; i++) {
            body.append((char) inputStream.read());
        }

        return body.toString();
    }

    public String getStartLine() {
        return startLine;
    }


}
