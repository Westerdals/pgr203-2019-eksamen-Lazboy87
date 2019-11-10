package no.kristiania.Dao;

import no.kristiania.Http.HttpController;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectMemberHttpController implements HttpController {
    private ProjectMemberDao memberDao;

    public ProjectMemberHttpController(ProjectMemberDao memberDao) {

        this.memberDao = memberDao;
    }

    @Override
    public void handle(String requestPath, Map<String, String> requestParameters, OutputStream outputStream) throws IOException {

        try {
            String statusCode = requestParameters.getOrDefault("status", "200");
            String location = requestParameters.get("location");
            String body = requestParameters.getOrDefault("body", getBody());

            outputStream.write(("HTTP/1.0 " + statusCode + " OK\r\n" +

                    "Content-length: " + body.length() + "\r\n" +
                    (location != null ? "Location: " + location + "\r\n" : "") +
                    "\r\n" +
                    body).getBytes());
        } catch (SQLException e) {
            String message = e.toString();
            outputStream.write(("HTTP/1.0 500 Internal server error\r\n" +
                    "Content-length: " + message.length() + "\r\n" +
                    "\r\n" +
                    message).getBytes());
        }

    }

    public String getBody() throws SQLException {
        String body = memberDao.listAll().stream()
                .map(p -> String.format("<option value='%s'>%s</option>", p.getId(), p.getName()))
                .collect(Collectors.joining(""));
        return body;
    }


}
