package no.kristiania.Dao;

import no.kristiania.Http.HttpController;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class ProjectMemberHttpController implements HttpController {
    @Override
    public void handle(String requestPath, Map<String, String> requestParameters, OutputStream outputStream) throws IOException {

        String statusCode = requestParameters.getOrDefault("status", "200");
        String location = requestParameters.get("location");
        String body = requestParameters.getOrDefault("body", "<option>testworker<option>");

        outputStream.write(("HTTP/1.0 " + statusCode + " OK\r\n" +

                "Content-length: " + body.length() + "\r\n" +
                (location != null ? "Location: " + location + "\r\n" : "") +
                "\r\n" +
                body).getBytes());

    }
}
