package no.kristiania.Dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskMemberDao extends AbstractDao<TaskMember> {

    public TaskMemberDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(TaskMember taskMember, PreparedStatement statement) throws SQLException {
        statement.setLong(1, taskMember.getMemberId());
        statement.setLong(2, taskMember.getTaskId());
    }

    @Override
    protected TaskMember readObject(ResultSet resultSet) throws SQLException {
        TaskMember taskMember = new TaskMember();
        taskMember.setTaskId(resultSet.getLong(2));
        taskMember.setMemberId(resultSet.getLong(1));
        return taskMember;
    }

    public List<TaskMember> listAll() throws SQLException {
        return listAll("select * from taskmembers");
    }

    public void insert(TaskMember taskMember) throws SQLException {
        //checks if object already exist in database before inserting
        if (this.listAll().contains(taskMember)) {
            System.out.println("FINNES ALEREDE!");
            return;
        }
        insert(taskMember, "insert into taskmembers (member_id,task_id) values (?,?)");
    }

}
