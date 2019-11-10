package no.kristiania.Dao;

import no.kristiania.Http.HttpController;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class ProjectMemberHttpController implements HttpController {
    @Override
    public void handle(String requestPath, Map<String, String> requestParameters, OutputStream outputStream) throws IOException {

            String statusCode = "200";
           String contentType="text/html";
            String body= "<option>Testworker<option>";
            int contentLength = body.length();

            outputStream.write(("HTTP/1.0 " + statusCode + " OK\r\n" +
                     "Content-type:"+contentType+"\r\n"+
                    "Content-length: " + contentLength + "\r\n" +
                    "Connection:close\r\n"+
                    "r\n"+
                    body).getBytes());

    }
}
