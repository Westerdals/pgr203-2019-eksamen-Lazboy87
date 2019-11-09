package no.kristiania.Http;

import java.io.IOException;
import java.net.Socket;

public interface HttpController {
    void handle(String requestPath, Socket socket) throws IOException;
}
