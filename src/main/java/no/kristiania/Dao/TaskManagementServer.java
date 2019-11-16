package no.kristiania.Dao;


import no.kristiania.Http.HttpServer;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TaskManagementServer {
    public static void main(String[] args) throws IOException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();


        Properties properties = new Properties();
        try(FileReader reader = new FileReader("task-manager.properties")){
            properties.load(reader);
        }
        dataSource.setUrl(properties.getProperty("dataSource.url"));
        dataSource.setUser(properties.getProperty("dataSource.username"));
        dataSource.setPassword(properties.getProperty("dataSource.password"));

        Flyway.configure().dataSource(dataSource).load().migrate();

        HttpServer server = new HttpServer(8080);
        server.setFileLocation("src/main/resources/taskmanager");
        server.addController("/api/projectMembers", new ProjectMemberHttpController(new ProjectMemberDao(dataSource)));
        server.addController("/api/addTaskMember", new AddTaskMemberHttpController(new TaskMemberDao(dataSource), new TaskDao(dataSource), new ProjectMemberDao(dataSource)));
        server.addController("/api/status", new StatusHttpController(new StatusDao(dataSource)));
        server.addController("/api/tasks", new TaskHttpController(new TaskDao(dataSource)));
        server.addController("/api/changeTaskStatus",new ChangeTaskStatusHttpController(new TaskDao(dataSource)));
        server.addController("/api/ListTasksMember",new ListTaskMemberHttpController(new TaskDao(dataSource),new ProjectMemberDao(dataSource)));


        server.start();
    }
}
