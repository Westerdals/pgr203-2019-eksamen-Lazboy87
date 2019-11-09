package no.kristiania.Http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public interface HttpController {
    void handle(String requestPath, Map<String, String> requestParameters, OutputStream outputStream) throws IOException;
}
