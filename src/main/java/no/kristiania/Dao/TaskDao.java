package no.kristiania.Dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskDao extends AbstractDao<Task> {

    public TaskDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(Task task, PreparedStatement statement) throws SQLException {
        if(task.getName() == null){
            return;
        }
        statement.setString(1, task.getName());
        statement.setLong(2, task.getStatusId());


    }

    @Override
    protected Task readObject(ResultSet resultSet) throws SQLException {

        Task task = new Task();
        task.setStatusName(resultSet.getString(4));
        task.setStatusId(resultSet.getInt(3));
        task.setName(resultSet.getString(2));
        task.setId(resultSet.getInt(1));
        return task;
    }



    public List<Task> listAll() throws SQLException {
        return listAll("select t.id, t.task_name, t.status_id, s.status_cat\n" +
                "from tasks t left join status s on t.status_id = s.id");
    }


    public long insert(Task task) throws SQLException {
        long id = insert(task, "insert into tasks (task_name,status_id) values (?,?)");
        task.setId((int) id);
        return id;
    }

    public void updateStatus(Long statusId, Long taskId) throws SQLException{
        Task task = new Task();

        insert(task, "update tasks set status_id = "+ statusId +" where id = " + taskId);
    }
    /*
    public Task retrieve(long id) throws SQLException{
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from tasks where id = ?")) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()) {
                        return (readObject(resultSet));
                    } else {
                        return null;
                    }
                }
            }
        }
    }*/
}
