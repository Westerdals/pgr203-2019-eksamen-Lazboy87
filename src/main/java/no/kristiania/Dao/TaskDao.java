package no.kristiania.Dao;

import javax.sql.DataSource;
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
        statement.setString(2, task.getTaskName());
        statement.setInt(1, task.getStatusId());
    }

    @Override
    protected Task readObject(ResultSet resultSet) throws SQLException {

        Task task = new Task();

        task.setTaskName(resultSet.getString(2));
        task.setStatusId(resultSet.getInt(1));
        return task;
    }
    public void insert(Task task) throws SQLException{
        insert(task, "insert into tasks (task_id,task_name) values (?,?)");
    }


    public List<Task> listAll() throws SQLException {
        return listAll("select * from tasks");
    }
}
