package no.kristiania.Http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

class FileHttpController implements HttpController {
    private HttpServer httpServer;

    public FileHttpController(HttpServer httpServer) {
        this.httpServer = httpServer;
    }

    @Override
    public void handle(String requestPath, Socket socket) throws IOException {
        File file = new File(httpServer.fileLocation + requestPath);
        if (file.exists()) {
            socket.getOutputStream().write(("HTTP/1.1 200 OK\r\n" +
                    "Content-length: " + file.length() + "\r\n" +
                    "Connection: close\r\n" +
                    "\r\n").getBytes());
            new FileInputStream(file).transferTo(socket.getOutputStream());

        } else {
            socket.getOutputStream().write(("HTTP/1.1 404 OK\r\n" +
                    "Connection: close\r\n" +
                    "\r\n").getBytes());

        }
    }
}
