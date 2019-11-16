package no.kristiania.Dao;

import no.kristiania.Http.HttpController;
import no.kristiania.Http.HttpServer;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ListTaskMemberHttpController implements HttpController {
    private TaskDao taskDao;
    private ProjectMemberDao projectMemberDao;

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(ListTaskMemberHttpController.class);

    public ListTaskMemberHttpController( TaskDao taskDao, ProjectMemberDao projectMemberDao) {
        this.taskDao = taskDao;
        this.projectMemberDao = projectMemberDao;
    }


    @Override
    public void handle(String requestAction, String requestPath, Map<String, String> requestParameters, String requestBody, OutputStream outputStream) throws IOException {
        try {
            if (requestAction.equalsIgnoreCase("POST")) {


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

        String body = "";
        StringBuilder bod = new StringBuilder();

        List<ProjectMember> member = projectMemberDao.listAll();
        for(int i = 0; i < member.size(); i++){

            String memberId = ""+ member.get(i).getId();
            String sql = "select t.*, null n from taskmembers tm join tasks t on tm.task_id = t.id where tm.member_id = " + memberId;
            String taskNames="";

            List<Task> tasks = taskDao.listAll(sql);

            for(int j = 0; j < tasks.size(); j++){
                taskNames = taskNames +tasks.get(j).getName() + ", ";
            }
            System.out.println("i: " + i);
            bod.append("<article>\n" +
                    "<h4>Member: </h4>" +
                    "\n" +
                    "        <h4> "+ member.get(i).getName() +"</h4>\n" +
                    "\n" +

                    "\n" +
                            "<h4>Tasks:</h4>" +
                    "\n" +
                    "        <div>"+ taskNames + "</div>\n" +
                    "\n" +
                    "    </article>");
        }
        body = bod.toString();


        return body;
    }

}




