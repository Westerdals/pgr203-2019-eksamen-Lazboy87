package no.kristiania.Dao;

import no.kristiania.Http.HttpServer;

import java.io.IOException;

public class TaskManagementServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer(8080);
        server.setFileLocation("src/main/resources/taskmanager");
        server.addController("/api/projectMembers",new ProjectMemberHttpController());
        server.start();
    }
}
