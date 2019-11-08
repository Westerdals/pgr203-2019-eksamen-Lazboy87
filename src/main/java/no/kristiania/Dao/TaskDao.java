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
        statement.setString(1, task.getName());
        statement.setInt(2, task.getStatusId());

    }

    @Override
    protected Task readObject(ResultSet resultSet) throws SQLException {

        Task task = new Task();

        task.setName(resultSet.getString(2));
        task.setId(resultSet.getInt(1));
        return task;
    }
    public void insert(Task task) throws SQLException{
        insert(task, "insert into tasks (task_name,status_id) values (?,?)");
    }


    public List<Task> listAll() throws SQLException {
        return listAll("select * from tasks");
    }
}
