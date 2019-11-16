package no.kristiania.Dao;

import no.kristiania.Http.HttpController;
import no.kristiania.Http.HttpServer;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public class ChangeTaskStatusHttpController implements HttpController { private TaskDao taskDao;
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(TaskHttpController.class);

    public ChangeTaskStatusHttpController(TaskDao taskDao) {

        this.taskDao = taskDao;
    }

    @Override
    public void handle(String requestAction, String requestPath, Map<String, String> requestParameters, String requestBody, OutputStream outputStream) throws IOException {
        try {
            if (requestAction.equalsIgnoreCase("POST")) {
                requestParameters = HttpServer.parseRequestParameters(requestBody);
                Task task = new Task();

                task.setName(requestParameters.get("taskName"));
                task.setStatusId(Long.parseLong("statusName"));




                taskDao.insert(task);
                return;

            }

            String statusCode = requestParameters.getOrDefault("status", "200");
            String location = requestParameters.get("location");
            String body = requestParameters.getOrDefault("body", getBody());

            outputStream.write(("HTTP/1.0 " + statusCode + " OK\r\n" +

                    "Content-length: " + body.length() + "\r\n" +
                    (location != null ? "Location: " + location + "\r\n" : "") +
                    "\r\n" +
                    body).getBytes());
        } catch (SQLException e) {
            Logger.error("While handling request{}", requestPath, e);
            String message = e.toString();
            outputStream.write(("HTTP/1.0 500 Internal server error\r\n" +
                    "Content-length: " + message.length() + "\r\n" +
                    "\r\n" +
                    message).getBytes());
        }

    }



    public String getBody() throws SQLException {
        String body = taskDao.listAll().stream()
                .map(p -> String.format("<option value='%s'>%s</option>", p.getId(), p.getName()))
                .collect(Collectors.joining(""));
        return body;
    }


}



}
