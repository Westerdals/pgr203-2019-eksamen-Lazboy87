package no.kristiania.Dao;

import no.kristiania.Http.HttpController;
import no.kristiania.Http.HttpServer;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AddTaskMemberHttpController implements HttpController {
    private TaskMemberDao taskMemberDao;
    private TaskDao taskDao;
    private ProjectMemberDao projectMemberDao;

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(ListTaskMemberHttpController.class);

    public AddTaskMemberHttpController(TaskMemberDao taskMemberDao, TaskDao taskDao, ProjectMemberDao projectMemberDao) {
        this.taskMemberDao = taskMemberDao;
        this.taskDao = taskDao;
        this.projectMemberDao = projectMemberDao;
    }


    @Override
    public void handle(String requestAction, String requestPath, Map<String, String> requestParameters, String requestBody, OutputStream outputStream) throws IOException {
        try {
            if (requestAction.equalsIgnoreCase("POST")) {
                requestParameters = HttpServer.parseRequestParameters(requestBody);
                TaskMember taskMember = new TaskMember();
                taskMember.setMemberId(Long.parseLong(requestParameters.get("memberName")));
                taskMember.setTaskId(Long.parseLong(requestParameters.get("taskName")));

                taskMemberDao.insert(taskMember);


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

        List<Task> tasks = taskDao.listAll();
        for (int i = 0; i < tasks.size(); i++) {

            String taskId = "" + tasks.get(i).getId();
            String sql = "select m.* from taskmembers tm join projectmembers m on tm.member_id = m.id where tm.task_id = " + taskId;
            String memberName = "";
            System.out.println(i);

            List<ProjectMember> projectMembers = projectMemberDao.listAll(sql);
            System.out.println("size: " + projectMembers.size());
            for (int j = 0; j < projectMembers.size(); j++) {
                memberName = memberName + projectMembers.get(j).getName() + ", ";

            }

            bod.append("<article>\n" +
                    "        <h1>" + tasks.get(i).getName() + "</h1>\n" +
                    "\n" +
                    "        <h4> Status: </h4>\n" +
                    "\n" +
                    "        <p>" + tasks.get(i).getStatusName() + "</p>\n" +
                    "\n" +
                    "        <h4> Members: </h4>\n" +
                    "\n" +
                    "        <div>" + memberName + "</div>\n" +
                    "\n" +
                    "    </article>");
        }
        body = bod.toString();


        return body;
    }

}




