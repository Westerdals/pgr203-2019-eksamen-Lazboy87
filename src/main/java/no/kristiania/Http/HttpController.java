package no.kristiania.Http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public interface HttpController {
    void handle(String requestPath, OutputStream outputStream) throws IOException;
}
